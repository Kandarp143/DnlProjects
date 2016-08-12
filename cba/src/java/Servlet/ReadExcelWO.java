package Servlet;

import Bean.WorkItemBean;
import Dao.WorkDao;
import Logic.Variables;
import Dao.XlsWoDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ReadExcelWO extends HttpServlet {

    static final Logger logger = Logger.getLogger(Upload.class.getName());
    private static final long serialVersionUID = 1L;
    // location to store file attachmented
    private static final String attachment_DIRECTORY = "uxls";
    // attachment settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; 	// 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
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
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        String filePath = attachmentPath + File.separator + fileName;
                        storeFile = new File(filePath);
                        // saves the file on disk
                        item.write(storeFile);
                    }
                }
            }
            //File Validation and Reading
            if (fileName.substring(fileName.indexOf(".") + 1).equals("xlsx")) {
                Dao.XlsWoDao xls = new XlsWoDao();
                ArrayList<WorkItemBean> ansitm = new ArrayList<WorkItemBean>();
                ansitm = xls.ReadXLS(storeFile);
                WorkDao wdao = new WorkDao();
                Variables.xlsitmwo.clear();
                Logic.Variables.xlsitmwo
                        = xls.getValidatedXls(ansitm);
                status = "4";
                response.sendRedirect("wocreate.jsp?status=" + status + "&xls=true");
            } else {
                status = "3";
                response.sendRedirect("wocreate_data.jsp?status=" + status);
            }
        } catch (Exception ex) {
            out.print(
                    "There was an error: " + ex.getMessage());
            status = "E";
        }
    }
}
