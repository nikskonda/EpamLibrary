package by.epam.java.training.web.command.impl.admin;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import static by.epam.java.training.web.command.CommandName.*;
import static by.epam.java.training.web.command.util.FieldNames.*;
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
            AdministratorService userService = ServiceFactory.getAdministratorService();

            if (userService.isAdministrator(new SignInForm(user.getLogin(), password))){
                userService.changeRole(userId, roleName);
                CommandFactory.getCommand(SHOW_USER).execute(request, response);
            }else{
                request.setAttribute(ERROR_EXIST, true);
                CommandFactory.getCommand(SHOW_USER).execute(request, response);
            }

        } catch (DAOException ex){
            logger.warn("Problem with database", ex);
            request.setAttribute(ERROR_DATABASE, true);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
            request.setAttribute(ERROR_PATH, true);
        } catch (Exception ex){
            logger.warn(ex);
            request.setAttribute(ERROR_UNKNOWN, true);
        }



    }
}
