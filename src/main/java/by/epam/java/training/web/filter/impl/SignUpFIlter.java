package by.epam.java.training.web.filter.impl;

import by.epam.java.training.model.user.form.SignUpForm;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.filter.AbstractFilter;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstant.SIGN_UP;
import static by.epam.java.training.web.command.util.FieldNameConstant.*;
import static by.epam.java.training.web.command.util.FieldNameConstant.LAST_NAME;

public class SignUpFIlter extends AbstractFilter {
    private static final Logger logger = Logger.getLogger(SignUpFIlter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            UserService userService = ServiceFactory.getUserService();
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            SignUpForm signUpForm = new SignUpForm();
            signUpForm.setLogin(request.getParameter(LOGIN));
            signUpForm.setEmail(request.getParameter(EMAIL));
            signUpForm.setFirstName(request.getParameter(FIRST_NAME));
            signUpForm.setLastName(request.getParameter(LAST_NAME));

            if (!userService.isFreeLogin(signUpForm.getLogin())) {
                request.setAttribute(SIGN_UP_FORM, signUpForm);
                request.setAttribute(ERROR_EXIST, true);
                forward(request, response, SIGN_UP);
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }
    }
}
