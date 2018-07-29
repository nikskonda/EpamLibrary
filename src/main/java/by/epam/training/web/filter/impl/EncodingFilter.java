package by.epam.training.web.filter.impl;

import by.epam.training.web.filter.AbstractFilter;

import javax.servlet.*;
import java.io.IOException;

/**
 * The filter is designed to encoding parameters.
 *
 * @author Nikita Shkonda
 *
 * @see Filter
 */
public class EncodingFilter extends AbstractFilter {

    private static final String CHARACTER_ENCODING = "characterEncoding";

    /**
     * The method encoding request.
     *
     * @param servletRequest  {@link ServletRequest}.
     * @param servletResponse {@link ServletResponse}.
     * @param filterChain {@link FilterChain}
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String encoding = filterConfig.getInitParameter(CHARACTER_ENCODING);
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
