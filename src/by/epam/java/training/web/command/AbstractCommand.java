package by.epam.java.training.web.command;

import by.epam.java.training.model.user.ActiveUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.SHOW_NEWS_LIST;

public abstract class AbstractCommand implements Command{

    protected static final String LAST_ACTION = "last_action";
    private static final String URL_PATTERN = "/library?";

    @Override
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    @Override
    public void forward(HttpServletRequest request, HttpServletResponse response, String jspFileName) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspFileName);
        dispatcher.forward(request, response);
        return;
    }

    @Override
    public void redirect(HttpServletResponse response, String destination) throws ServletException, IOException {
        response.sendRedirect(destination);
        return;
    }

    @Override
    public void rememberLastAction(HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession(true);
        String lastAction = request.getQueryString();
        if (lastAction!=null && !lastAction.isEmpty()) {
            session.setAttribute(LAST_ACTION, URL_PATTERN+lastAction);
        }
    }

    @Override
    public void executeLastAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String url = (String)session.getAttribute(LAST_ACTION);
        if (url == null || url.isEmpty()){
            CommandFactory.getCommand(SHOW_NEWS_LIST).execute(request, response);
        } else {
            this.redirect(response, url);
        }

    }
}
