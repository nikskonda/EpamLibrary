package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsPreview;
import by.epam.java.training.servise.ServiceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NewsDAOTest {

    private static final NewsDAO dao = DAOFactory.getNewsDAO();

    @Test
    public void getNewsByPage_badAttributes_returnNull() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(-1);
        pa.setNumberOfPage(-10);

        List<NewsPreview> list = dao.getNewsPerPage(pa);

        assertNull(list);
    }

    @Test
    public void getNewsByPage_goodAttributes_returnListOfNews() throws DAOException {
        PageAttribute pa = new PageAttribute();
        pa.setCountOnPage(10);
        pa.setNumberOfPage(1);
        pa.setLocale("en");
        int extended = 10;

        List<NewsPreview> list = dao.getNewsPerPage(pa);

        assertEquals(extended, list.size());
    }

    @Test
    public void getNews_badId_returnNull() throws DAOException {
        Integer id = 0;
        String locale = "en";

        News news = dao.getNews(id, locale);

        assertNull(news);
    }

    @Test
    public void getNews_badLocale_returnNull()throws DAOException  {
        Integer id = 20;
        String locale = "qw";

        News news = dao.getNews(id, locale);

        assertNull(news);
    }

    @Test
    public void getNews_GoodValue_returnNews() throws DAOException {
        Integer id = 27;
        String locale = "en";
        String expected = "2) Hello Word";

        News news = dao.getNews(id, locale);

        assertEquals(expected, news.getTitle());
    }


    @Test
    public void calcTotalPages_badCount_returnNull() throws DAOException {
        Integer countOnOnePage = -10;
        String locale = "en";

        Integer totalPages = dao.calcPagesCountNews(locale, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_badLocale_returnNull() throws DAOException {
        Integer countOnOnePage = 10;
        String locale = "qw";

        Integer totalPages = dao.calcPagesCountNews(locale, countOnOnePage);

        assertNull(totalPages);
    }

    @Test
    public void calcTotalPages_goodValue_returnTotalPages() throws DAOException {
        Integer countOnOnePage = 10;
        String locale = "en";
        Integer expected = 3;

        Integer totalPages = dao.calcPagesCountNews(locale, countOnOnePage);

        assertEquals(expected, totalPages);
    }
}