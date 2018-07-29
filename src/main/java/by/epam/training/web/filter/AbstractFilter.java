package by.epam.training.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * An abstract class describes the basic filter methods.
 *
 * @author Nikita Shkonda
 *
 * @see Filter
 */
public abstract class AbstractFilter implements Filter {

    protected FilterConfig filterConfig = null;
    protected static final String LAST_ACTION = "last_action";
    private static final char QUESTION = '?';

    /**
     * The body of the filter.
     *
     * @param servletRequest  {@link ServletRequest}.
     * @param servletResponse {@link ServletResponse}.
     * @param filterChain {@link FilterChain}
     *
     * @throws ServletException if any inner exception in servlet occurs
     */
    @Override
    public abstract void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException;

    /**
     * Initializing the filter.
     *
     * @param filterConfig {@link FilterConfig}
     *
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Kill filter.
     *
     */
    @Override
    public void destroy() {
        filterConfig = null;
    }

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
        String lastAction = request.getRequestURI();
        if (lastAction!=null && !lastAction.isEmpty()) {
            session.setAttribute(LAST_ACTION, lastAction);
        }
    }

    /**
     * Executing the last user request.
     *
     * @param request {@link HttpServletRequest}.
     *
     */
    protected void executeLastAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        if (queryString == null) {
            session.setAttribute(LAST_ACTION, requestURL.toString());
        } else {
            session.setAttribute(LAST_ACTION, requestURL.append(QUESTION).append(queryString).toString());
        }
    }
}
