package by.epam.java.training.web.command.impl.news;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.util.PageConstant.NEWS_LIST;
import static by.epam.java.training.web.command.util.FieldNameConstant.*;

public class TakeListOfNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeListOfNews.class);


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            rememberLastAction(request);

            NewsService service = ServiceFactory.getNewsService();
            HttpSession session = request.getSession(true);
            String locale = (String)session.getAttribute(LOCALE);
            Integer countNews = getCount(request, COUNT_NEWS_ON_PAGE, INIT_COUNT_NEWS);
            Integer currentPage = getCurrentPage(request);

            PageAttribute pageData = new PageAttribute();
            pageData.setCountOnPage(countNews);
            pageData.setNumberOfPage(currentPage);
            pageData.setLocale(locale);

            session.setAttribute(COUNT_NEWS_ON_PAGE, countNews);
            request.setAttribute(NUMBER_OF_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcPagesCountNews(locale, countNews));
            request.setAttribute(NEWS, service.getNewsPerPage(pageData));

            forward(request, response, NEWS_LIST);

        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
