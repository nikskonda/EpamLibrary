package by.epam.java.training.web.filter.impl;

import by.epam.java.training.web.command.util.FieldNameConstant;
import by.epam.java.training.web.filter.AbstractFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalizationFilter extends AbstractFilter {

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
