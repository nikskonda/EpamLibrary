package by.epam.java.training.web.command.impl.admin;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.ServiceFactory;

import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.command.CommandName;
import by.epam.java.training.web.util.EncriptionMD5;
import static by.epam.java.training.web.command.util.FieldNames.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteUser extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(DeleteUser.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser) session.getAttribute(USER);
            String password = EncriptionMD5.encrypt(request.getParameter(PASSWORD));
            Integer userId = Integer.parseInt(request.getParameter(USER_ID));
            AdministratorService adminService = ServiceFactory.getAdministratorService();
            UserService userService = ServiceFactory.getUserService();

            if (userService.isExistUser(new SignInForm(user.getLogin(), password))){
                adminService.deleteUser(userId);
                CommandFactory.getCommand(CommandName.TAKE_LIST_OF_USERS).execute(request, response);
                return;
            }else{
                request.setAttribute(ERROR_DEL_EXIST, true);
                CommandFactory.getCommand(CommandName.TAKE_USER).execute(request, response);
                return;
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
