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

import static by.epam.java.training.web.command.Pages2.NEWS;

public class ShowNews extends AbstractCommand {

    private static final Logger logger = Logger.getLogger(ShowNews.class);

    private static final String NEWS_DATA = "news";
    private static final String NEWS_ID = "news_id";
    private static final String LOCALE = "local";

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

            Integer newsId = Integer.parseInt(request.getParameter(NEWS_ID));

            request.setAttribute(NEWS_DATA, service.getNews(newsId, locale));
            forward(request, response, NEWS.getPage());

        } catch (IOException ex){

        } catch (Exception ex) {

        }

    }
}
