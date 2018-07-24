package by.epam.java.training.web.filter.impl;

import by.epam.java.training.model.user.form.SignUpForm;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.filter.AbstractFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstants.SIGN_UP;
import static by.epam.java.training.web.command.util.FieldNameConstants.*;
import static by.epam.java.training.web.command.util.FieldNameConstants.LAST_NAME;

public class SignUpFIlter extends AbstractFilter {

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
        } catch (Exception ex){

        }
    }
}
