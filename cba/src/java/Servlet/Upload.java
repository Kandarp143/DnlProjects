package Servlet;

import Bean.FileBean;
import Dao.FileDao;
import Logic.GetMethod;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

public class Upload extends HttpServlet {

    static final Logger logger = Logger.getLogger(Upload.class.getName());
    private static final long serialVersionUID = 1L;

    // location to store file attachmented
    private static final String attachment_DIRECTORY = "attachment";

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
                        // Process regular form field 
                        //(input type="text|radio|checkbox|etc", select, etc).
                        // ... (do your job here)
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
                        File storeFile = new File(filePath);
                        // saves the file on disk
                        item.write(storeFile);
                        //   request.setAttribute("cmterr", "File :" + fileName + "Attached successfully");
                        status = "Y";
                    }
                }
                //add attachment record to database
                String uname = session.getAttribute("uid").toString();
                Logic.GetMethod g = new GetMethod();
                fb.setATT_ID(g.Get_Seq("ATT_ID", "CBA_ATT_MST"));
                fb.setPO_NO(pono);
                fb.setATT_TYPE("ATT");
                fb.setBILL_NO(bill);
                fb.setF_PATH("attachment" + "\\" + fileName);
                fb.setF_NAME(fileName);
                fb.setUSER_ID(uname);
                FileDao fdo = new FileDao();
                fdo.addFileRecord(fb);

            }
        } catch (Exception ex) {
            out.print(
                    "There was an error: " + ex.getMessage());
            status = "N";
        }
        // redirects client to message page
//        getServletContext().getRequestDispatcher("/include/F_Upload.jsp?pono=" + pono).forward(
//                request, response);
        response.sendRedirect("include/F_Upload.jsp?pono=" + pono + "&status=" + status);
    }
}
