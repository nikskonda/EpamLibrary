package by.epam.java.training.web.command.impl;

import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.web.command.AbstractCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.java.training.web.command.Pages2.START_PAGE;

public class ShowNewsList extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ShowNewsList.class);

    private static final String NEWS = "news";
    private static final String LOCALE = "local";
    private static final String COUNT_NEWS_ON_PAGE = "countNews";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String TOTAL_PAGES = "totalPages";

    private static final int INIT_COUNT_NEWS = 10;
    private static final int INIT_NUMBER_OF_PAGE = 1;

    private Integer getInt(String str){
        Integer result = null;
        try{
            result = Integer.parseInt(str);
        } catch (NumberFormatException ex){
            logger.warn("", ex);
        }
        return result;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            NewsService service = ServiceFactory.getNewsService();

            HttpSession session = request.getSession(true);
            if (session.getAttribute(LOCALE)==null){
                session.setAttribute(LOCALE, "en");
            }
            String locale = (String)session.getAttribute(LOCALE);

            Integer countNews = null;

            Integer newCountNews = getInt(request.getParameter(COUNT_NEWS_ON_PAGE));
            if (newCountNews == null){
                countNews = (Integer)(session.getAttribute(COUNT_NEWS_ON_PAGE));
                if (countNews==null){
                    countNews = INIT_COUNT_NEWS;
                }
            } else {
                countNews = newCountNews;
            }

            Integer currentPage = getInt(request.getParameter(CURRENT_PAGE));
            if (currentPage==null){
                currentPage = INIT_NUMBER_OF_PAGE;
            }

            session.setAttribute(COUNT_NEWS_ON_PAGE, newCountNews);
            request.setAttribute(CURRENT_PAGE, currentPage);
            request.setAttribute(TOTAL_PAGES, service.calcTotalPages(locale, countNews));
            request.setAttribute(NEWS, service.getNewsByPage(locale, countNews, currentPage));

            forward(request, response, START_PAGE.getPage());

        } catch (IOException ex){

        } catch (Exception ex) {

        }

    }
}
