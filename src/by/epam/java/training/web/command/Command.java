package by.epam.java.training.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}
