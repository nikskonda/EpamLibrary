package by.epam.training.web.filter.impl;

import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.filter.AbstractFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * The filter is redirected on home page.
 *
 * @author Nikita Shkonda
 *
 * @see Filter
 */
public class MainFilter extends AbstractFilter {

    /**
     * The method redirects to home page.
     *
     * @param request  {@link ServletRequest}.
     * @param response {@link ServletResponse}.
     * @param filterChain {@link FilterChain}
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        redirect(httpResponse, FieldNameConstant.GO_TO_HOME_PAGE);
        return;
    }
}
