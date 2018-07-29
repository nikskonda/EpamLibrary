package by.epam.training.web.filter.impl;

import by.epam.training.model.user.form.SignUpForm;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserService;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.filter.AbstractFilter;
import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The filter checks if the login is free.
 *
 * @author Nikita Shkonda
 *
 * @see Filter
 */
public class SignUpFilter extends AbstractFilter {
    private static final Logger logger = Logger.getLogger(SignUpFilter.class);

    /**
     * The method checks if the login is free.
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
        try{
            UserService userService = ServiceFactory.getUserService();
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            SignUpForm signUpForm = new SignUpForm();
            signUpForm.setLogin(request.getParameter(FieldNameConstant.LOGIN));
            signUpForm.setEmail(request.getParameter(FieldNameConstant.EMAIL));
            signUpForm.setFirstName(request.getParameter(FieldNameConstant.FIRST_NAME));
            signUpForm.setLastName(request.getParameter(FieldNameConstant.LAST_NAME));

            if (!userService.isFreeLogin(signUpForm.getLogin())) {
                request.setAttribute(FieldNameConstant.SIGN_UP_FORM, signUpForm);
                request.setAttribute(FieldNameConstant.ERROR_EXIST, true);
                forward(request, response, PageConstant.SIGN_UP);
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (ServiceException ex){
            throw new ServletException(ex);
        } catch (IOException ex) {
            throw new IOException("Error in pages path", ex);
        }
    }
}
