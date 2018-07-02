package by.epam.java.training.web.command.impl.l10n;

import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.Command;
import by.epam.java.training.web.command.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Localization extends AbstractCommand {

    private static final String LOCAL = "local";
    private static final String COMMAND = "command";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);
        session.setAttribute(LOCAL, request.getParameter(COMMAND));

        redirect(response, "/catalog?command=open_news");
    }
}
