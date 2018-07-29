package by.epam.training.servise;

import by.epam.training.model.PageAttribute;
import by.epam.training.model.news.News;
import by.epam.training.model.news.NewsPreview;
import by.epam.training.servise.exception.ServiceException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NewsServiceTest {

    private static final NewsService newsService = ServiceFactory.getNewsService();

    @Test
    public void getNewsByPage_badAttributes_returnNull() throws ServiceException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(-1);
        pa.setNumberOfPage(-10);

        List<NewsPreview> list = newsService.getNewsPerPage(pa);

        assertNull(list);
    }

    @Test
    public void getNewsByPage_goodAttributes_returnListOfNews() throws ServiceException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        int extended = 10;

        List<NewsPreview> list = newsService.getNewsPerPage(pa);

        assertEquals(extended, list.size());
    }

    @Test
    public void getNews_badId_returnNull() throws ServiceException {
        Integer id = 0;
        String locale = "en";

        News news = newsService.getNews(id, locale);

        assertNull(news);
    }

    @Test
    public void getNews_badLocale_returnNull()throws ServiceException  {
        Integer id = 20;
        String locale = "qw";

        News news = newsService.getNews(id, locale);

        assertNull(news);
    }

    @Test
    public void getNews_GoodValue_returnNews() throws ServiceException {
        Integer id = 27;
        String locale = "en";
        String expected = "2) Hello Word";

        News news = newsService.getNews(id, locale);

        assertEquals(expected, news.getTitle());
    }


    @Test
    public void calcTotalPages_badCount_returnNull() throws ServiceException {
        Integer countOnOnePage = -10;
        String locale = "en";

        Integer totalPages = newsService.calcPagesCountNews(locale, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_badLocale_returnNull() throws ServiceException {
        Integer countOnOnePage = 10;
        String locale = "qw";

        Integer totalPages = newsService.calcPagesCountNews(locale, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_goodValue_returnTotalPages() throws ServiceException {
        Integer countOnOnePage = 10;
        String locale = "en";
        Integer expected = 3;

        Integer totalPages = newsService.calcPagesCountNews(locale, countOnOnePage);

        assertEquals(expected, totalPages);
    }
}