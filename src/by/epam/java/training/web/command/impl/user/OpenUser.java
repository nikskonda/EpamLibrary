package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Pages.ADMINISTRATION_USER;

public class OpenUser extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(OpenUser.class);

    private static final String USER_ID = "user_id";
    private static final String USER_PROFILE = "profile";
    private static final String ROLE_LIST = "roles";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            Integer userId = Integer.parseInt(request.getParameter(USER_ID));

            UserService userService = ServiceFactory.getInstance().getUserService();

            User user = userService.getUser(userId);

            request.setAttribute(USER_PROFILE, user);

            request.setAttribute(ROLE_LIST, userService.getRoles());

            forward(request, response, ADMINISTRATION_USER);

        } catch (IOException ex){

        } catch (Exception ex) {

        }



    }
}
