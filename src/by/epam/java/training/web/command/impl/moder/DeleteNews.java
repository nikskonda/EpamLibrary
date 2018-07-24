package by.epam.java.training.web.command.impl.moder;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandConstants.GO_TO_EDIT_NEWS_FORM;
import static by.epam.java.training.web.command.CommandConstants.TAKE_LIST_OF_NEWS;
import static by.epam.java.training.web.command.util.FieldNameConstants.*;

public class DeleteNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(DeleteNews.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ModeratorService service = ServiceFactory.getModeratorService();
            UserService userService = ServiceFactory.getUserService();
            ActiveUser user = (ActiveUser)session.getAttribute(USER);
            Integer newsId = Integer.parseInt(request.getParameter(NEWS_ID));
            String password = EncriptionMD5.encrypt(request.getParameter(PASSWORD));

            if (!userService.isUserExist(new SignInForm(user.getLogin(), password))){
                request.setAttribute(ERROR_EXIST, true);
                CommandFactory.getCommand(GO_TO_EDIT_NEWS_FORM).execute(request, response);
                return;
            }

            if (!service.delNews(newsId)){
                CommandFactory.getCommand(GO_TO_EDIT_NEWS_FORM).execute(request, response);
                return;
            }

            CommandFactory.getCommand(TAKE_LIST_OF_NEWS).execute(request, response);
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
