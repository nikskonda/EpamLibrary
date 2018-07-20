package by.epam.java.training.web.command.impl.news;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Page.NEWS_LIST;
import static by.epam.java.training.web.command.util.FieldNames.*;

public class ShowNewsList extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ShowNewsList.class);

    private static final int INIT_COUNT_NEWS = 8;
    private static final int INIT_NUMBER_OF_PAGE = 1;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);

            NewsService service = ServiceFactory.getNewsService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(LOCALE);
            Integer countNews = getCount(request, COUNT_NEWS_ON_PAGE, INIT_COUNT_NEWS);
            Integer currentPage = getCurrentPage(request, NUMBER_OF_PAGE, INIT_NUMBER_OF_PAGE);

            PageAttributes pageData = new PageAttributes();
            pageData.setCountOnPage(countNews);
            pageData.setNumberOfPage(currentPage);
            pageData.setLocale(locale);

            session.setAttribute(COUNT_NEWS_ON_PAGE, countNews);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcTotalPages(locale, countNews));
            request.setAttribute(NEWS, service.getNewsByPage(pageData));

            forward(request, response, NEWS_LIST);

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
