package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.ProfileForm;
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

import static by.epam.java.training.web.command.Pages2.PROFILE;
import static by.epam.java.training.web.command.Pages2.SIGN_IN;

public class OpenProfile extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(OpenProfile.class);

    private static final String USER = "user";
    private static final String USER_PROFILE = "profile";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ActiveUser activeUser = (ActiveUser) session.getAttribute(USER);

            if (activeUser == null){
                redirect(response, SIGN_IN.getPage());
                return;
            }


            UserService userService = ServiceFactory.getInstance().getUserService();

            User user = userService.getUser(activeUser.getId());

            ProfileForm profile = new ProfileForm(user.getLogin(),
                    user.getFirstName(), user.getLastName(), user.getEmail());

            request.setAttribute(USER_PROFILE, profile);

            forward(request, response, PROFILE.getPage());

        } catch (IOException ex){

        } catch (Exception ex) {

        }



    }
}
