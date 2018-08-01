package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.news.News;
import by.epam.training.model.news.NewsPreview;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NewsDAOTest {

    private static final NewsDAO dao = DAOFactory.getNewsDAO();

    @Test (expected = NullPointerException.class)
    public void getNewsByPage_badAttributes_returnEx() throws DAOException {
        PageAttribute pa = null;

        List<NewsPreview> list = dao.getNewsPerPage(pa);
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

    @Test (expected = NullPointerException.class)
    public void getNews_badId_returnEx() throws DAOException {
        Integer id = null;
        String locale = "en";

        News news = dao.getNews(id, locale);

    }

    @Test (expected = NullPointerException.class)
    public void getNews_badLocale_returnEx()throws DAOException  {
        Integer id = null;
        String locale = null;

        News news = dao.getNews(id, locale);
    }

    @Test
    public void getNews_GoodValue_returnNews() throws DAOException {
        Integer id = 27;
        String locale = "en";
        String expected = "2) Hello Word";

        News news = dao.getNews(id, locale);

        assertEquals(expected, news.getTitle());
    }


    @Test (expected = NullPointerException.class)
    public void calcTotalPages_badCount_returnEx() throws DAOException {
        Integer countOnOnePage = null;
        String locale = null;

        Integer totalPages = dao.calcPagesCountNews(locale, countOnOnePage);
    }

    @Test (expected = NullPointerException.class)
    public void calcTotalPages_badLocale_returnEx() throws DAOException {
        Integer countOnOnePage = null;
        String locale = null;

        Integer totalPages = dao.calcPagesCountNews(locale, countOnOnePage);
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