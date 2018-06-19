package by.epam.java.training.web.command.impl.l10n;

import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Localization extends AbstractCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession(true).setAttribute("local", request.getParameter("command"));
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
