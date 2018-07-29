package by.epam.java.training.web.command.impl.news;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.news.News;
import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import by.epam.java.training.web.command.util.PageConstant;
import by.epam.java.training.web.command.util.FieldNameConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class TakeNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeNews.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);
            NewsService service = ServiceFactory.getNewsService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(LOCALE);
            Integer newsId = Integer.parseInt(request.getParameter(NEWS_ID));

            News news = service.getNews(newsId, locale);

            request.setAttribute(FieldNameConstant.NEWS, news);
            request.setAttribute(FieldNameConstant.NEWS_TEXT, news.getText().split(NEW_LINE.toString()));

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
