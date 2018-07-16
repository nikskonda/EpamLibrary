package by.epam.java.training.web.command.impl.moder.edit;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.servise.ModeratorService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.CommandFactory;
import by.epam.java.training.web.command.util.FieldNames;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import static by.epam.java.training.web.command.CommandName.OPEN_EDITING_NEWS;
import static by.epam.java.training.web.command.CommandName.SHOW_NEWS_LIST;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class EditNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(EditNews.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            HttpSession session = request.getSession(true);

            ActiveUser user = (ActiveUser)session.getAttribute(USER);

            ModeratorService service = ServiceFactory.getModeratorService();
            News defNews = new News();
            defNews.setId(Integer.parseInt(request.getParameter(NEWS_ID)));
            defNews.setText(request.getParameter(NEWS_TEXT));
            defNews.setPhotoUrl(request.getParameter(NEWS_PHOTO_URL));
            defNews.setThumbsUrl(request.getParameter(NEWS_THUMBS_URL));
            defNews.setTitle(request.getParameter(NEWS_TITLE));
            defNews.setUserId(user.getId());

            News translatedNews = new News();
            translatedNews.setId(defNews.getId());
            translatedNews.setText(request.getParameter(NEWS_TEXT_RU));
            translatedNews.setTitle(request.getParameter(NEWS_TITLE_RU));
            String lang = (request.getParameter(NEWS_LANG));

            if (!service.editNews(defNews, translatedNews, lang)){
                delete(request.getServletContext().getRealPath(defNews.getPhotoUrl()));
                delete(request.getServletContext().getRealPath(defNews.getThumbsUrl()));
                CommandFactory.getCommand(OPEN_EDITING_NEWS).execute(request, response);
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

}
