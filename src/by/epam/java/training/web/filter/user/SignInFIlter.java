package by.epam.java.training.web.filter.user;

import javax.servlet.*;
import java.io.IOException;

public class SignInFIlter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

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
