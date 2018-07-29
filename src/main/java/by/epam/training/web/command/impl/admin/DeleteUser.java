package by.epam.training.web.command.impl.admin;

import by.epam.training.model.user.ActiveUser;
import by.epam.training.model.user.form.SignInForm;
import by.epam.training.servise.AdministratorService;
import by.epam.training.servise.ServiceFactory;

import by.epam.training.servise.UserService;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.CommandFactory;
import by.epam.training.web.command.CommandConstants;
import by.epam.training.web.util.EncriptionMD5;

import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to delete the user.
 *
 * @author  Nikita Shkonda
 */
public class DeleteUser extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(DeleteUser.class);

    /**
     * Deleted the user.
     *
     * @param request  {@link HttpServletRequest}.
     * @param response {@link HttpServletResponse}.
     *
     * @throws ServletException if any inner exception in servlet occurs
     * @throws IOException      if I/O error occurs.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ActiveUser user = (ActiveUser) session.getAttribute(FieldNameConstant.USER);
            String password = EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.PASSWORD));
            Integer userId = Integer.parseInt(request.getParameter(FieldNameConstant.USER_ID));
            AdministratorService adminService = ServiceFactory.getAdministratorService();
            UserService userService = ServiceFactory.getUserService();

            if (userService.isUserExist(new SignInForm(user.getLogin(), password))){
                adminService.deleteUser(userId);
                CommandFactory.getCommand(CommandConstants.TAKE_LIST_OF_USERS).execute(request, response);
                return;
            }else{
                request.setAttribute(FieldNameConstant.ERROR_DEL_EXIST, true);
                CommandFactory.getCommand(CommandConstants.TAKE_USER).execute(request, response);
                return;
            }

        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }



    }
}
