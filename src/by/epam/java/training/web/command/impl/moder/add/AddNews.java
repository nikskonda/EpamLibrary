package by.epam.java.training.web.command.impl.moder.add;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.command.util.FieldNames;
import by.epam.java.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.OPEN_ADD_NEWS;
import static by.epam.java.training.web.command.CommandName.SHOW_NEWS_LIST;
import static by.epam.java.training.web.command.Page.*;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class AddNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(AddNews.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);
            ModeratorService service = ServiceFactory.getModeratorService();
            UserService userService = ServiceFactory.getUserService();
            ActiveUser user = (ActiveUser)session.getAttribute(USER);

            News defNews = new News();
            defNews.setText(request.getParameter(NEWS_TEXT));
            defNews.setPhotoUrl(request.getParameter(NEWS_PHOTO_URL));
            defNews.setThumbsUrl(request.getParameter(NEWS_THUMBS_URL));
            defNews.setTitle(request.getParameter(NEWS_TITLE));
            defNews.setUserId(user.getId());

            News tNews = new News();
            tNews.setText(request.getParameter(NEWS_TEXT_RU));
            tNews.setTitle(request.getParameter(NEWS_TITLE_RU));
            String lang = (request.getParameter(NEWS_LANG));

            String password = EncriptionMD5.encrypt(request.getParameter(PASSWORD));

            if (!userService.isExistUser(new SignInForm(user.getLogin(), password))){
                clearData(request, defNews, tNews);
                request.setAttribute(ERROR_EXIST, true);
                CommandFactory.getCommand(OPEN_ADD_NEWS).execute(request, response);
                return;
            }

            if (!service.addNews(defNews, tNews, lang)){
                clearData(request, defNews, tNews);
                CommandFactory.getCommand(OPEN_ADD_NEWS).execute(request, response);
                return;
            }

            CommandFactory.getCommand(SHOW_NEWS_LIST).execute(request, response);
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

    private static void delete(String nameFile) {
        File file = new File(nameFile);
        if (file.exists()) {
            file.delete();
        }
    }

    private void clearData(HttpServletRequest request, News defNews, News tNews){
        delete(request.getServletContext().getRealPath(defNews.getPhotoUrl()));
        delete(request.getServletContext().getRealPath(defNews.getThumbsUrl()));
        request.setAttribute(FieldNames.NEWS, defNews);
        request.setAttribute(NEWS_RU, tNews);
    }
}
