package by.epam.java.training.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {

    protected FilterConfig filterConfig = null;
    protected static final String LAST_ACTION = "last_action";
    private static final char QUESTION = '?';

    @Override
    public abstract void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

    public void forward(HttpServletRequest request, HttpServletResponse response, String jspFileName) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspFileName);
        dispatcher.forward(request, response);
        return;
    }

    public void redirect(HttpServletResponse response, String destination) throws IOException {
        response.sendRedirect(destination);
        return;
    }

    public void executeLastAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        if (queryString == null) {
            session.setAttribute(LAST_ACTION, requestURL.toString());
        } else {
            session.setAttribute(LAST_ACTION, requestURL.append(QUESTION).append(queryString).toString());
        }
    }

    public void rememberLastAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String lastAction = request.getRequestURI();
        if (lastAction!=null && !lastAction.isEmpty()) {
            session.setAttribute(LAST_ACTION, lastAction);
        }
    }
}
