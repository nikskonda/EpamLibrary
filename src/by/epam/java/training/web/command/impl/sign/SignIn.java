package by.epam.java.training.web.command.impl.sign;

import by.epam.java.training.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements Command {

    private String EMAIL = 

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
    }

    @Override
    public void forward(HttpServletRequest request, HttpServletResponse response, String jspFileName) {

    }

    @Override
    public void redirect(HttpServletRequest request, HttpServletResponse response, String destination) {

    }
}
