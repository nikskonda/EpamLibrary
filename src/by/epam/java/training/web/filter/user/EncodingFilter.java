package by.epam.java.training.web.filter.user;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final String CHARACTER_ENCODING = "characterEncoding";

    private FilterConfig filterConfig = null;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String encoding = filterConfig.getInitParameter(CHARACTER_ENCODING);
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
