package by.epam.training.web.command.impl.moder.edit;

import by.epam.training.model.news.News;
import by.epam.training.model.user.ActiveUser;
import by.epam.training.model.user.form.SignInForm;
import by.epam.training.servise.ModeratorService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.UserService;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.CommandFactory;
import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.util.EncriptionMD5;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import static by.epam.training.web.command.CommandConstants.GO_TO_EDIT_NEWS_FORM;
import static by.epam.training.web.command.CommandConstants.TAKE_LIST_OF_NEWS;

/**
 * Used to edit a news.
 *
 * @author  Nikita Shkonda
 */
public class EditNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(EditNews.class);

    /**
     * Editing a news.
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

            News defNews = new News();
            defNews.setId(Integer.parseInt(request.getParameter(FieldNameConstant.NEWS_ID)));
            defNews.setText(request.getParameter(FieldNameConstant.NEWS_TEXT));
            defNews.setPhotoUrl(request.getParameter(FieldNameConstant.NEWS_PHOTO_URL));
            defNews.setThumbsUrl(request.getParameter(FieldNameConstant.NEWS_THUMBS_URL));
            defNews.setTitle(request.getParameter(FieldNameConstant.NEWS_TITLE));
            defNews.setUserId(user.getId());

            News tNews = new News();
            tNews.setId(defNews.getId());
            tNews.setText(request.getParameter(FieldNameConstant.NEWS_TEXT_RU));
            tNews.setTitle(request.getParameter(FieldNameConstant.NEWS_TITLE_RU));
            String lang = (request.getParameter(FieldNameConstant.NEWS_LANG));

            String password = EncriptionMD5.encrypt(request.getParameter(FieldNameConstant.PASSWORD));

            if (!userService.isUserExist(new SignInForm(user.getLogin(), password))){
                clearData(request, defNews, tNews);
                request.setAttribute(FieldNameConstant.ERROR_EXIST, true);
                CommandFactory.getCommand(GO_TO_EDIT_NEWS_FORM).execute(request, response);
                return;
            }

            if (!service.editNews(defNews, tNews, lang)){
                clearData(request, defNews, tNews);
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

    private static void delete(String nameFile) {
        File file = new File(nameFile);
        if (file.exists()) {
            file.delete();
        }
    }

    private void clearData(HttpServletRequest request, News defNews, News tNews){
        delete(request.getServletContext().getRealPath(defNews.getPhotoUrl()));
        delete(request.getServletContext().getRealPath(defNews.getThumbsUrl()));
        request.setAttribute(FieldNameConstant.NEWS, defNews);
        request.setAttribute(FieldNameConstant.NEWS_RU, tNews);
    }

}
