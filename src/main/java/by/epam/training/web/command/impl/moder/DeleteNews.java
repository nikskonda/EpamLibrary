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

import static by.epam.training.web.command.CommandConstants.GO_TO_EDIT_NEWS_FORM;
import static by.epam.training.web.command.CommandConstants.TAKE_LIST_OF_NEWS;

/**
 * Used to delete the news.
 *
 * @author  Nikita Shkonda
 */
public class DeleteNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(DeleteNews.class);

    /**
     * Deleted the news.
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
            ModeratorService service = ServiceFactory.getModeratorService();
            UserService userService = ServiceFactory.getUserService();
            ActiveUser user = (ActiveUser)session.getAttribute(FieldNameConstant.USER);
            Integer newsId = Integer.parseInt(request.getParameter(FieldNameConstant.NEWS_ID));
            String password = EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.PASSWORD));

            if (!userService.isUserExist(new SignInForm(user.getLogin(), password))){
                request.setAttribute(FieldNameConstant.ERROR_EXIST, true);
                CommandFactory.getCommand(GO_TO_EDIT_NEWS_FORM).execute(request, response);
                return;
            }

            if (!service.delNews(newsId)){
                CommandFactory.getCommand(GO_TO_EDIT_NEWS_FORM).execute(request, response);
                return;
            }

            CommandFactory.getCommand(TAKE_LIST_OF_NEWS).execute(request, response);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }

}
