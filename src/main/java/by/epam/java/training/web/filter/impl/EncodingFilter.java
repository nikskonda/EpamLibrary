package by.epam.java.training.web.filter.impl;

import by.epam.java.training.web.filter.AbstractFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class EncodingFilter extends AbstractFilter {

    private static final String CHARACTER_ENCODING = "characterEncoding";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String encoding = filterConfig.getInitParameter(CHARACTER_ENCODING);
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
