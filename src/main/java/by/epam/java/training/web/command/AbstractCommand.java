package by.epam.java.training.web.command;

import by.epam.java.training.web.command.util.Pagination;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandConstants.TAKE_LIST_OF_NEWS;

public abstract class AbstractCommand extends Pagination implements Command{

    protected static final String LAST_ACTION = "last_action";
    private static final char QUESTION = '?';


    @Override
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;


    public void forward(HttpServletRequest request, HttpServletResponse response, String jspFileName) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspFileName);
        dispatcher.forward(request, response);
        return;
    }


    public void redirect(HttpServletResponse response, String destination) throws IOException {
        response.sendRedirect(destination);
        return;
    }

    public void rememberLastAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString == null) {
            session.setAttribute(LAST_ACTION, requestURL.toString());
        } else {
            session.setAttribute(LAST_ACTION, requestURL.append(QUESTION).append(queryString).toString());
        }
    }

    public void executeLastAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String url = (String)session.getAttribute(LAST_ACTION);
        if (url == null || url.isEmpty()){
            CommandFactory.getCommand(TAKE_LIST_OF_NEWS).execute(request, response);
        } else {
            this.redirect(response, url);
        }
    }
}
