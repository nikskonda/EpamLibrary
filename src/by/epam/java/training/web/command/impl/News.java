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

import static by.epam.java.training.web.command.Pages.START_PAGE;

public class News extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(News.class);

    private static final String NEWS = "news";
    private static final String LOCALE = "local";
    private static final String COUNT_NEWS_ON_PAGE = "countNews";
    private static final String NUMBER_OF_PAGE = "numberOfPage";

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
            NewsService service = ServiceFactory.getInstance().getNewsService();

            HttpSession session = request.getSession(true);
            if (session.getAttribute(LOCALE)==null){
                session.setAttribute(LOCALE, "en");
            }
            String locale = (String)session.getAttribute(LOCALE);

            Integer countNews = getInt(request.getParameter(COUNT_NEWS_ON_PAGE));
            if (countNews==null){
                request.setAttribute(COUNT_NEWS_ON_PAGE, INIT_COUNT_NEWS);
                countNews = INIT_COUNT_NEWS;
            }

            Integer numberOfPage = getInt(request.getParameter(NUMBER_OF_PAGE));
            if (numberOfPage==null){
                request.setAttribute(NUMBER_OF_PAGE, INIT_NUMBER_OF_PAGE);
                numberOfPage = INIT_NUMBER_OF_PAGE;
            }

            request.setAttribute(NEWS, service.getNewsByPage(locale, countNews, numberOfPage));
            forward(request, response, START_PAGE.getPage());

        } catch (IOException ex){

        } catch (Exception ex) {

        }

    }
}
