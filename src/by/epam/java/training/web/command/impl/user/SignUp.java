package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.SignUpForm;
import by.epam.java.training.servise.ServiceFactory;
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

    private static final String ERROR_EXIST = "error_exist";
    private static final String SIGN_UP_FORM = "signUpForm";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SignUpForm signUpForm = new SignUpForm();

        signUpForm.setLogin(request.getParameter(LOGIN));
        signUpForm.setPassword(request.getParameter(PASSWORD));
        signUpForm.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD));
        signUpForm.setEmail(request.getParameter(EMAIL));
        signUpForm.setFirstName(request.getParameter(FIRST_NAME));
        signUpForm.setLastName(request.getParameter(LAST_NAME));

        UserService userService = ServiceFactory.getInstance().getUserService();

        if (!userService.isFreeLogin(signUpForm.getLogin()) || !signUpForm.comparePasswords()){
            request.setAttribute(SIGN_UP_FORM, signUpForm);
            request.setAttribute(ERROR_EXIST, true);
            forward(request, response, SIGN_UP.getPage());
            return;
        }
        request.setAttribute(ERROR_EXIST, false);
        ActiveUser user = userService.addUser(signUpForm);
        if (user == null) {
            redirect(response, ERROR.getPage());
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(USER, user);
        redirect(response, PROFILE.getPage());
    }

}
