package by.epam.java.training.web.filter;

import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.CommandFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.SHOW_NEWS_LIST;
import static by.epam.java.training.web.command.util.FieldNames.USER;

public abstract class AbstractFilter implements Filter {

    protected FilterConfig filterConfig = null;
    protected static final String LAST_ACTION = "last_action";

    @Override
    public abstract void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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

    public void executeLastAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        if (queryString == null) {
            session.setAttribute(LAST_ACTION, requestURL.toString());
        } else {
            session.setAttribute(LAST_ACTION, requestURL.append('?').append(queryString).toString());
        }
    }

    public void rememberLastAction(HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession(true);
        String lastAction = request.getRequestURI();
        if (lastAction!=null && !lastAction.isEmpty()) {
            session.setAttribute(LAST_ACTION, lastAction);
        }
    }
}
