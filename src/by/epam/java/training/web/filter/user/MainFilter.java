package by.epam.java.training.web.filter.user;

import by.epam.java.training.web.command.Page;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        httpResponse.sendRedirect(Page.START_PAGE);
        filterChain.doFilter(request, response);
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
