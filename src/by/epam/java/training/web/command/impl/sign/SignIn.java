package by.epam.java.training.web.command.impl.sign;

import by.epam.java.training.model.AuthorizationUser;
import by.epam.java.training.model.User;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.impl.UserServiceImpl;
import by.epam.java.training.web.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn implements Command {

    private String LOGIN = "login";
    private String PASSWORD = "password";

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        AuthorizationUser authUser = new AuthorizationUser();

        authUser.setLogin(request.getParameter(LOGIN));
        authUser.setPassword(request.getParameter(PASSWORD));

        UserService userService = new UserServiceImpl();

        if (!userService.isExistLoginAndPassword(authUser)){
            forward(request, response, "jsp/sign/SignIn.jsp");
            return;
        }

        User user = userService.getUserByLogin(authUser.getLogin());
        HttpSession session = request.getSession();
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
