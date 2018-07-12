package by.epam.java.training.web.servlet;

import by.epam.java.training.web.command.CommandFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class FrontController extends HttpServlet {

    private static final String COMMAND = "command";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            process(request, response);
    }


    private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String command = request.getParameter(COMMAND);
        CommandFactory.getCommand(command).execute(request, response);
    }


}
