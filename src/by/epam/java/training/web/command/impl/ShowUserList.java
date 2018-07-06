package by.epam.java.training.web.command.impl;

import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java.training.web.command.Pages2.USER_LIST;

public class ShowUserList extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ShowUserList.class);

    private static final String USERS = "user_list";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            UserService service = ServiceFactory.getUserService();

            request.setAttribute(USERS, service.getUsers());
            forward(request, response, USER_LIST.getPage());

        } catch (IOException ex){

        } catch (Exception ex) {

        }

    }
}
