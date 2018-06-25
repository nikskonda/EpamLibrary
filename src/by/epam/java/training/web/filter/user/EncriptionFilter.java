package by.epam.java.training.web.filter.user;

import by.epam.java.training.web.util.EncriptionMD5;

import javax.servlet.*;
import java.io.IOException;

public class EncriptionFilter implements Filter {

    private FilterConfig filterConfig = null;

    private static final String PASSWORD = "password";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String password = servletRequest.getParameter(PASSWORD);
        if (password != null) {
            servletRequest.setAttribute(PASSWORD, EncriptionMD5.encrypt(password));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
