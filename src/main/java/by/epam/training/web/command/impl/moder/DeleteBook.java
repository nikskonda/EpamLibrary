package by.epam.training.web.command.impl.moder;

import by.epam.training.model.user.ActiveUser;
import by.epam.training.model.user.form.SignInForm;
import by.epam.training.servise.ModeratorService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserService;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.CommandFactory;
import by.epam.training.web.util.EncriptionMD5;
import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.training.web.command.CommandConstants.*;

/**
 * Used to delete the book.
 *
 * @author  Nikita Shkonda
 */
public class DeleteBook extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(DeleteBook.class);

    /**
     * Deleted the book.
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
            ActiveUser user = (ActiveUser)session.getAttribute(FieldNameConstant.USER);

            ModeratorService service = ServiceFactory.getModeratorService();
            UserService userService = ServiceFactory.getUserService();

            Integer bookId = Integer.parseInt(request.getParameter(FieldNameConstant.BOOK_ID));
            String password = EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.PASSWORD));

            if (!userService.isUserExist(new SignInForm(user.getLogin(), password))){
                request.setAttribute(FieldNameConstant.ERROR_EXIST, true);
                CommandFactory.getCommand(GO_TO_ADD_BOOK_FORM).execute(request, response);
                return;
            }

            if (!service.delBook(bookId)){
                CommandFactory.getCommand(GO_TO_EDIT_BOOK_FORM).execute(request, response);
                return;
            }

            CommandFactory.getCommand(TAKE_BOOK_CATALOG).execute(request, response);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }

}
