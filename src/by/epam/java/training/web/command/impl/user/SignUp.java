package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.model.*;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.impl.UserServiceImpl;
import by.epam.java.training.web.command.AbstractCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Pages.*;

public class SignUp extends AbstractCommand {

    private static final String USER = "user";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email";
    private static final String ERROR_MSG = "errorMsg";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RegistrationForm regForm = new RegistrationForm();

        regForm.setLogin(request.getParameter(LOGIN));
        regForm.setPassword(request.getParameter(PASSWORD));
        regForm.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD));
        regForm.setEmail(request.getParameter(EMAIL));
        regForm.setFirstName(request.getParameter(FIRST_NAME));
        regForm.setLastName(request.getParameter(LAST_NAME));

        UserService userService = new UserServiceImpl();

        if (!userService.isFreeLogin(regForm.getLogin()) || !regForm.comparePasswords()){
            request.setAttribute(ERROR_MSG, "zanyt login");
            forward(request, response, SIGN_UP.getPage());
            return;
        }

        ActiveUser user = userService.getActiveUser(regForm.getLogin());
        HttpSession session = request.getSession(true);
        session.setAttribute(USER, user);

        redirect(response, PROFILE.getPage());
    }

}
