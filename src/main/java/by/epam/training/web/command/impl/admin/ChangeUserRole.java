package by.epam.training.web.command.impl.admin;

import by.epam.training.model.user.ActiveUser;
import by.epam.training.model.user.form.SignInForm;
import by.epam.training.servise.AdministratorService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserService;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.CommandFactory;
import static by.epam.training.web.command.CommandConstants.*;

import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to change the role of the user.
 *
 * @author  Nikita Shkonda
 */
public class ChangeUserRole extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ChangeUserRole.class);

    /**
     * Modifies the user role.
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
            String roleName = request.getParameter(FieldNameConstant.ROLE);
            UserService userService = ServiceFactory.getUserService();
            AdministratorService administratorService = ServiceFactory.getAdministratorService();

            if (userService.isUserExist(new SignInForm(user.getLogin(), password))){
                if (administratorService.changeRole(userId, roleName)){
                    CommandFactory.getCommand(TAKE_USER).execute(request, response);
                    return;
                }
                request.setAttribute(FieldNameConstant.ERROR, true);
                CommandFactory.getCommand(TAKE_USER).execute(request, response);
                return;
            }else{
                request.setAttribute(FieldNameConstant.ERROR_EXIST, true);
                CommandFactory.getCommand(TAKE_USER).execute(request, response);
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
