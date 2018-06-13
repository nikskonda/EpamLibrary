package by.epam.java.training.web.command.impl.sign;

import by.epam.java.training.web.command.Command;
import by.epam.java.training.web.servlet.FrontController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenSignIn implements Command {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/sign/SignIn.jsp");
        dispatcher.forward(request, response);
    }


}
