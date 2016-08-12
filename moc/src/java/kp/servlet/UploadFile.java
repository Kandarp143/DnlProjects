package kp.servlet;

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
import kp.beans.user.pojo.MocUserMst;
import kp.beans.wf.pojo.MocWfAtt;
import kp.dao.data.AttDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFile extends HttpServlet {

    static final Logger logger = Logger.getLogger(UploadFile.class.getName());
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
        String cid = "";
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
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (item.isFormField()) {
                        // Process regular form field 
                        //(input type="text|radio|checkbox|etc", select, etc).
                        // ... (do your job here)
                        if ("cid".equals(item.getFieldName())) {
                            cid = item.getString();
                            logger.log(Level.SEVERE, cid);
                        }
                    } else {
                        fileName = new File(item.getName()).getName();
                        String filePath = attachmentPath + File.separator + fileName;
                        out.print(filePath);
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        status = "Y";
                    }
                }
//                DATABASE TRANACTION
                MocWfAtt fb = new MocWfAtt();
                MocUserMst usr = new MocUserMst();
                usr.setUserId(session.getAttribute("usr").toString());
                fb.setAttType("ATT");
                fb.setCaseId(Integer.parseInt(cid));
                fb.setFName(fileName);
                fb.setFPath("attachment" + "\\" + fileName);
                fb.setMocUserMst(usr);
                AttDao attdao = new AttDao();
                attdao.saveAtt(fb);
            }
        } catch (Exception ex) {
            out.print(
                    "There was an error: " + ex.getMessage());
            status = "N";
        }
        response.sendRedirect("include/fileupload.jsp?cid=" + cid + "&status=" + status);
    }
}
