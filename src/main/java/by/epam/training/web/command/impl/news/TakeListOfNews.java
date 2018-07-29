package by.epam.training.web.command.impl.news;

import by.epam.training.model.PageAttribute;
import by.epam.training.servise.NewsService;
import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.web.command.AbstractCommand;
import by.epam.training.web.command.util.FieldNameConstant;
import by.epam.training.web.command.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Used to get a list of news.
 *
 * @author  Nikita Shkonda
 */
public class TakeListOfNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(TakeListOfNews.class);


    /**
     * Getting all news.
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
            Integer countNews = getCount(request, FieldNameConstant.COUNT_NEWS_ON_PAGE, FieldNameConstant.INIT_COUNT_NEWS);
            Integer currentPage = getCurrentPage(request);

            PageAttribute pageData = new PageAttribute();
            pageData.setCountOnPage(countNews);
            pageData.setNumberOfPage(currentPage);
            pageData.setLocale(locale);

            session.setAttribute(FieldNameConstant.COUNT_NEWS_ON_PAGE, countNews);
            request.setAttribute(FieldNameConstant.NUMBER_OF_PAGE, currentPage);
            request.setAttribute(FieldNameConstant.TOTAL_PAGES, service.calcPagesCountNews(locale, countNews));
            request.setAttribute(FieldNameConstant.NEWS, service.getNewsPerPage(pageData));

            forward(request, response, PageConstant.NEWS_LIST);

        } catch (ServiceException ex){
            logger.warn("Problem with service", ex);
        } catch (IOException ex){
            logger.warn("Error in pages path", ex);
        } catch (Exception ex){
            logger.warn(ex);
        }

    }
}
