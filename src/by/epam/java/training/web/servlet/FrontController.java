package by.epam.java.training.web.servlet;

import by.epam.java.training.web.command.CommandFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static by.epam.java.training.web.command.Pages.START_PAGE;

public class FrontController extends HttpServlet {

    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter(COMMAND));
        if (request.getParameter(COMMAND)!=null)
            process(request, response);
        else{
            try{
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                if (!isMultipart) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }

                DiskFileItemFactory factory = new DiskFileItemFactory();

                factory.setSizeThreshold(1024*1024);
                File tempDir = (File)request.getServletContext().getAttribute("javax.servlet.context.tempdir");
                factory.setRepository(tempDir);

                ServletFileUpload upload = new ServletFileUpload(factory);

                upload.setSizeMax(1024 * 1024 * 10);

                List items = upload.parseRequest(request);

                Iterator iter = items.iterator();

                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();

                    if (item.isFormField()) {
                        //если принимаемая часть данных является полем формы
                        System.out.println(item.getFieldName()+"="+item.getString());
                    } else {
                        //в противном случае рассматриваем как файл
                        processUploadedFile(item);
                    }
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String command = request.getParameter(COMMAND);
        CommandFactory.getInstance().getCommand(command).execute(request, response);
    }

    private void processUploadedFile(FileItem item) throws Exception {
        File uploadetFile = null;
        do{
            String path = getServletContext().getRealPath("/images/news/"+123456 + item.getName());
            uploadetFile = new File(path);
        }while(uploadetFile.exists());
        uploadetFile.createNewFile();
        item.write(uploadetFile);
    }
}
