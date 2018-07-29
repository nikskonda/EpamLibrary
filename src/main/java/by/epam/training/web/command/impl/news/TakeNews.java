package by.epam.training.web.command.impl.news;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to get a information about news.
 *
 * @author  Nikita Shkonda
 */
public class TakeNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeNews.class);

    /**
     * Getting information about news.
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
            rememberLastAction(request);
            NewsService service = ServiceFactory.getNewsService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(FieldNameConstant.LOCALE);
            Integer newsId = Integer.parseInt(request.getParameter(FieldNameConstant.NEWS_ID));

            News news = service.getNews(newsId, locale);

            request.setAttribute(FieldNameConstant.NEWS, news);
            request.setAttribute(FieldNameConstant.NEWS_TEXT, news.getText().split(FieldNameConstant.NEW_LINE.toString()));

            forward(request, response, PageConstant.NEWS);

        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
