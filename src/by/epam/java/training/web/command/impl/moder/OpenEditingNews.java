package by.epam.java.training.web.command.impl.moder;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.news.News;
import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java.training.web.command.util.FieldNames.*;

public class OpenEditingNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(OpenEditingNews.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            NewsService service = ServiceFactory.getNewsService();
            Integer newsId = Integer.parseInt(request.getParameter(NEWS_ID));

            News defNews = service.getNews(newsId, "en");
            News ruNews = service.getNews(newsId, "ru");

            request.setAttribute(NEWS, defNews);
            request.setAttribute(NEWS_RU, ruNews);

            forward(request, response, Page.NEWS_EDIT);
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
