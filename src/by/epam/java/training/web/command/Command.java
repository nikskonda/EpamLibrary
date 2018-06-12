package by.epam.java.training.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    void process(HttpServletRequest request, HttpServletResponse response);

    void forward(HttpServletRequest request, HttpServletResponse response, String jspFileName);

    void redirect(HttpServletRequest request, HttpServletResponse response, String destination);

}
