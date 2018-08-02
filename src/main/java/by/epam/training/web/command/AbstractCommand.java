package by.epam.training.web.command;

import by.epam.training.web.command.util.PageConstant;
import by.epam.training.web.command.util.Pagination;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.training.web.command.CommandConstants.TAKE_LIST_OF_NEWS;

/**
 * An abstract class describes the basic Command methods.
 *
 * @author Nikita Shkonda
 *
 * @see Command
 */
public abstract class AbstractCommand extends Pagination implements Command{

    private static final String LAST_ACTION = "last_action";
    private static final char QUESTION = '?';

    /**
     * Executes the request.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Sending request processing to another resource of the current server.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     * @param jspFileName name of the jsp file.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    protected void forward(HttpServletRequest request, HttpServletResponse response, String jspFileName) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspFileName);
        dispatcher.forward(request, response);
        return;
    }

    /**
     * Sending request processing to another resource or another server.
     *
     * @param response {@link HttpServletResponse}.
     * @param destination url of the redirect.
     *
     * @throws IOException      if I/O error occurs.
     */
    protected void redirect(HttpServletResponse response, String destination) throws IOException {
        response.sendRedirect(destination);
        return;
    }

    /**
     * Saving the last user request in a session.
     *
     * @param request {@link HttpServletRequest}.
     *
     */
    protected void rememberLastAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString == null) {
            session.setAttribute(LAST_ACTION, requestURL.toString());
        } else {
            session.setAttribute(LAST_ACTION, requestURL.append(QUESTION).append(queryString).toString());
        }
    }

    /**
     * Executing the last user request.
     *
     * @param request {@link HttpServletRequest}.
     *
     */
    protected void executeLastAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String url = (String)session.getAttribute(LAST_ACTION);
        if (url == null || url.isEmpty()){
            redirect(response, PageConstant.START_PAGE);
        } else {
            this.redirect(response, url);
        }
    }
}
