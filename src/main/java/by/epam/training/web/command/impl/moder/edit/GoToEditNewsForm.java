package by.epam.training.web.command.impl.moder.edit;

import by.epam.training.model.news.News;
import by.epam.training.servise.NewsService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.util.PageConstant;
import by.epam.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used to go to the news constructor.
 *
 * @author  Nikita Shkonda
 */
public class GoToEditNewsForm extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(GoToEditNewsForm.class);

    /**
     * Moves to editing a news.
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
            NewsService service = ServiceFactory.getNewsService();
            Integer newsId = Integer.parseInt(request.getParameter(FieldNameConstant.NEWS_ID));

            News defNews = service.getNews(newsId, FieldNameConstant.ENGLISH);
            News ruNews = service.getNews(newsId, FieldNameConstant.RUSSIAN);

            request.setAttribute(FieldNameConstant.NEWS, defNews);
            request.setAttribute(FieldNameConstant.NEWS_RU, ruNews);

            forward(request, response, PageConstant.NEWS_EDIT);
        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }

}
