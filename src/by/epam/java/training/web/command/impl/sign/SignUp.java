package by.epam.java.training.web.command.impl.sign;

import by.epam.java.training.model.*;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.impl.UserServiceImpl;
import by.epam.java.training.web.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignUp implements Command {
    private static String LOGIN = "login";
    private static String PASSWORD = "password";
    private static String CONFIRM_PASSWORD = "confirmPasword";
    private static String FIRST_NAME = "firstName";
    private static String LAST_NAME = "lastName";
    private static String EMAIL = "email";


    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("singUp");
        RegistrationUser regUser = new RegistrationUser();
        regUser.setLogin(request.getParameter(LOGIN));
        regUser.setPassword(request.getParameter(PASSWORD));
        regUser.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD));
        regUser.setAddress(new Address());
        regUser.setEmail(request.getParameter(EMAIL));
        regUser.setFirstName(request.getParameter(FIRST_NAME));
        regUser.setLastName(request.getParameter(LAST_NAME));
        regUser.setRole(new Role());

        UserService userService = new UserServiceImpl();

        if (!userService.isFreeLogin(regUser.getLogin()) || !regUser.comparePasswords()){
            forward(request, response, "jsp/sign/SignUp.jsp");
            return;
        }

        User user = userService.getUserByLogin(regUser.getLogin());
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        redirect(request,response, "jsp/user/Profile.jsp");

    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String jspFileName) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspFileName);
        dispatcher.forward(request, response);

    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String destination) throws IOException {
        response.sendRedirect(destination);
    }

}
