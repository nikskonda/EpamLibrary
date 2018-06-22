package by.epam.java.training.web.command.impl;

import by.epam.java.training.web.command.AbstractCommand;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static by.epam.java.training.web.command.Pages.START_PAGE;

public class NewsConstructor extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(NewsConstructor.class);

    private final Random random = new Random();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{

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
                    processUploadedFile(item, request);
                }
            }

            forward(request, response, START_PAGE.getPage());
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void processUploadedFile(FileItem item, HttpServletRequest request) throws Exception {
        File uploadetFile = null;
        do{
            String path = request.getServletContext().getRealPath("/upload/"+random.nextInt() + item.getName());
            uploadetFile = new File(path);
        }while(uploadetFile.exists());
        uploadetFile.createNewFile();
        item.write(uploadetFile);
    }
}
