package by.epam.java.training.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    void forward(HttpServletRequest request, HttpServletResponse response, String jspFileName)
            throws ServletException, IOException;

    void redirect(HttpServletResponse response, String destination)
            throws ServletException, IOException;

}
