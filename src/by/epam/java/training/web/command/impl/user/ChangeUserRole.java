package by.epam.java.training.web.command.impl.user;

import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.SignInForm;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.command.Commandos;
import by.epam.java.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeUserRole extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ChangeUserRole.class);

    private static final String USER = "user";
    private static final String USER_ID = "user_id";
    private static final String ROLE = "role";
    private static final String PASSWORD = "password";
    private static final String ERROR_EXIST = "error_exist";



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser) session.getAttribute(USER);
            String password = EncriptionMD5.encrypt(request.getParameter(PASSWORD));
            Integer userId = Integer.parseInt(request.getParameter(USER_ID));
            String roleName = request.getParameter(ROLE);
            UserService userService = ServiceFactory.getUserService();

            if (userService.isAdministrator(new SignInForm(user.getLogin(), password))){
                userService.changeRole(userId, roleName);
                CommandFactory.getInstance().getCommand(Commandos.OPEN_USER).execute(request, response);
            }else{
                request.setAttribute(ERROR_EXIST, true);
                CommandFactory.getInstance().getCommand(Commandos.OPEN_USER).execute(request, response);
            }

        } catch (IOException ex){

        } catch (Exception ex) {

        }



    }
}
