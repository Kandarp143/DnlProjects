package Servlet;

import Bean.FileBean;
import Bean.WorkItemBean;
import Dao.WorkDao;
import Logic.LineNOCompatator;
import static Logic.Variables.xlsitm;
import Dao.XlsBillDao;
import Logic.Dropdown;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ReadExcel extends HttpServlet {

    static final Logger logger = Logger.getLogger(Upload.class.getName());
    private static final long serialVersionUID = 1L;
    // location to store file attachmented
    private static final String attachment_DIRECTORY = "uxls";
    // attachment settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; 	// 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * Upon receiving file attachment submission, parses the request to read
     * attachment data and saves the file on disk.
     *
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String pono = "";
        String bill = "0";
        String fileName = "";
        String status = "";
        File storeFile = null;
        // checks if the request actually contains attachment file
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        // configures attachment settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk 
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload attachment = new ServletFileUpload(factory);

        // sets maximum size of attachment file
        attachment.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        attachment.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store attachment file
        // this path is relative to application's directory
        String attachmentPath = getServletContext().getRealPath("")
                + File.separator + attachment_DIRECTORY;

        // creates the directory if it does not exist
        File attachmentDir = new File(attachmentPath);
        if (!attachmentDir.exists()) {
            attachmentDir.mkdir();
        }

        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = attachment.parseRequest(request);
            FileBean fb = new FileBean();
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (item.isFormField()) {
                        if ("ponoh".equals(item.getFieldName())) {
                            pono = item.getString();
                            logger.log(Level.SEVERE, pono);
                        }
                        if ("billh".equals(item.getFieldName())) {
                            bill = item.getString();
                            if (bill.isEmpty() || bill == null) {
                                bill = "0";
                            }
                            logger.log(Level.SEVERE, bill);
                        }
                    } else {
                        fileName = new File(item.getName()).getName();
                        String filePath = attachmentPath + File.separator + fileName;
                        storeFile = new File(filePath);
                        // saves the file on disk
                        item.write(storeFile);

                    }
                }
            }
            //File Validation and Reading
            ArrayList<WorkItemBean> wi1 = Dropdown.LoadProjTaskMst("123");
            ArrayList<String> proj = new ArrayList<String>();
            ArrayList<String> task = new ArrayList<String>();
            for (WorkItemBean b : wi1) {
                proj.add(b.getPROJ());
                task.add(b.getTASK());
            }
            String o1 = fileName.substring(0, 12);
            String o2 = pono.replace("/", "-");
            if (fileName.substring(fileName.indexOf(".") + 1).equals("xlsx")) {
                if (o1.equals(o2)) {
                    Dao.XlsBillDao xls = new XlsBillDao();
                    ArrayList<WorkItemBean> ansitm = new ArrayList<WorkItemBean>();
                    ansitm = xls.ReadXLS(storeFile);
                    WorkDao wdao = new WorkDao();
                    ArrayList<WorkItemBean> org = wdao.getWOItem(pono);
                    if (xls.ValidateXLS(org, ansitm)) {
                        ArrayList<WorkItemBean> Finalitm = new ArrayList<WorkItemBean>();
                        Logic.Variables.xlsitm.clear();
                        for (WorkItemBean inp : ansitm) {
                            WorkItemBean b = new WorkItemBean();
                            for (WorkItemBean opp : org) {
                                if (inp.getITEM_ID().equals(opp.getITEM_ID()) && inp.getQTY() != 0) {
                                    b.setPO_NO(opp.getPO_NO());
                                    b.setITEM_ID(opp.getITEM_ID());
                                    b.setLINE_NO(opp.getLINE_NO());
                                    b.setITEM_DESC(opp.getITEM_DESC());
                                    b.setUOM(opp.getUOM());
                                    b.setQTY(inp.getQTY());
                                    b.setRATE(opp.getRATE());
                                    b.setVAL(inp.getQTY() * opp.getRATE());
                                    b.setCMT(inp.getCMT());
                                    b.setPLANT(inp.getPLANT());
                                    b.setCC(inp.getCC());
                                    if (proj.contains(inp.getPROJ())) {
                                        b.setPROJ(inp.getPROJ());
                                    } else {
                                        b.setPROJ("-");
                                    }
                                    if (task.contains(inp.getTASK())) {
                                        b.setTASK(inp.getTASK());
                                    } else {
                                        b.setTASK("-");
                                    }

                                    Finalitm.add(b);
                                    xlsitm.add(b);
                                }
                            }
                        }
//                    for (WorkItemBean i : xlsitm) {
//                        if (i.getQTY() != 0) {
//                            Logger.getLogger(XlsBillDao.class.getName()).log(Level.INFO, "FINAL ITEM_ID : " + i.getITEM_ID() + " QTY:" + i.getQTY());
//                        }
//                    }
//                      
//                    for (WorkItemBean a : ansitm) {
//                        System.out.println("org");
//                        System.out.println(a.getQTY());
//                    }
//                    for (WorkItemBean a : xlsitm) {
//                        System.out.println("global");
//                        System.out.println(a.getQTY());
//                    }
//                    for (WorkItemBean a : Finalitm) {
//                        System.out.println("finaL");
//                        System.out.println(a.getQTY());
//                    }
                        Collections.sort(Finalitm, new LineNOCompatator());
                        request.setAttribute("fitmlist", Finalitm);
                        status = "4";
//                        response.sendRedirect("billcreate2.jsp?pono=" + pono + "&status=" + status + "&sid=6");
                        getServletConfig().getServletContext().getRequestDispatcher("/billcreate2.jsp?pono=" + pono + "&status=" + status + "&sid=6").forward(request, response);

                    } else {
                        status = "3";
                        response.sendRedirect("billcreate_data.jsp?pono=" + pono + "&status=" + status);
                    }
                } else {
                    status = "2";
                    response.sendRedirect("billcreate_data.jsp?pono=" + pono + "&status=" + status);
                }
            } else {
                status = "1";
                response.sendRedirect("billcreate_data.jsp?pono=" + pono + "&status=" + status);
            }
        } catch (Exception ex) {
            out.print(
                    "There was an error: " + ex.getMessage() + "Please upload correct file");
            status = "E";
            response.sendRedirect("billcreate_data.jsp?pono=" + pono + "&status=" + status + "&err=" + ex.getMessage());
        }
        // redirects client to message page
//        getServletContext().getRequestDispatcher("/include/F_Upload.jsp?pono=" + pono).forward(
//                request, response);
//        response.sendRedirect("include/F_Upload.jsp?pono=" + pono + "&status=" + status);
    }
}
