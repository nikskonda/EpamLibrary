package by.epam.training.web.filter.impl;

import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.filter.AbstractFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The filter is designed to set default value of localization.
 *
 * @author Nikita Shkonda
 *
 * @see Filter
 */
public class LocalizationFilter extends AbstractFilter {

    /**
     * The method sets default localization.
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
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpSession session = request.getSession(true);

        if (session.getAttribute(FieldNameConstant.LOCALE) == null){
            session.setAttribute(FieldNameConstant.LOCALE, FieldNameConstant.ENGLISH);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
