package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.Book;
import by.epam.training.model.book.BookPreview;
import by.epam.training.model.book.constituents.Genre;
import by.epam.training.model.book.constituents.PublishingHouse;
import by.epam.training.model.news.News;
import by.epam.training.model.news.NewsPreview;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ModeratorDAOTest {

    private static final ModeratorDAO dao = DAOFactory.getModeratorDAO();

    private static final String newsTitle = "News title";
    private static final String bookName = "Book name";


    private News initNews(){
        News news = new News();
        news.setTitle(newsTitle);
        news.setText("News text");
        news.setThumbsUrl("News thumbs");
        news.setPhotoUrl("News photo");
        news.setUserId(1);
        return news;
    }

    private News initTNews(){
        News news = new News();
        news.setTitle("Ru News title");
        news.setText("Ru News text");
        return news;
    }

    private int getNewsId() throws DAOException{
        NewsDAO dao = DAOFactory.getNewsDAO();
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(100);

        List<NewsPreview> list = dao.getNewsPerPage(pa);
        for (NewsPreview nc : list){
            if (nc.getTitle().equals(newsTitle)){
                return nc.getId();
            }
        }
        return 0;
    }

    private int getCountNews() throws DAOException{
        NewsDAO dao = DAOFactory.getNewsDAO();
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(100);

        List<NewsPreview> list = dao.getNewsPerPage(pa);
        return list.size();
    }

    private Book initBook(){
        Book book = new Book();
        book.setName(bookName);
        book.setDescription("Book Desc");
        book.setPublishingHouse(new PublishingHouse("PH"));
        book.setCoverUrl("Book cover");
        book.setPages(123);
        book.setPublishYear(123);
        book.setTextFileUrl("Book txt");
        book.setPdfFileUrl("Book pdf");
        book.setPrice(123d);
        book.setAuthors("Book Authors");
        book.addGenre(new Genre(1, "History"));
        return book;
    }

    private Book initTBook(){
        Book tBook = new Book();
        tBook.setName("Ru Book NAME");
        tBook.setDescription("Ru Book Desc");
        tBook.setTextFileUrl("Ru Book txt");
        tBook.setPdfFileUrl("Ru Book pdf");
        tBook.setAuthors("Ru Book Authors");
        return tBook;
    }

    private int getBookId() throws DAOException{
        BookDAO dao = DAOFactory.getBookDAO();
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(100);

        List<BookPreview> list = dao.getBooksPerPage(pa);
        for (BookPreview bc : list){
            if (bc.getName().equals(bookName)){
                return bc.getId();
            }
        }
        return 0;
    }

    private Integer getCountBook() throws DAOException{
        BookDAO dao = DAOFactory.getBookDAO();
        PageAttribute pa = new PageAttribute();
        pa.setLocale("en");
        pa.setNumberOfPage(1);
        pa.setCountOnPage(100);

        List<BookPreview> list = dao.getBooksPerPage(pa);
        return list.size();
    }

    @Test
    public void addNews_badNews_returnFalse() throws DAOException {
        News news = initNews();
        news.setTitle(null);
        News tNews = initTNews();
        String locale = "fr";

        boolean result = dao.addNews(news, tNews, locale);

        assertFalse(result);
    }

    @Test
    public void addNews_badTNews_returnFalse() throws DAOException {
        News news = initNews();
        News tNews = initTNews();
        tNews.setTitle(null);
        String locale = "fr";

        boolean result = dao.addNews(news, tNews, locale);

        assertFalse(result);
    }

    @Test
    public void addNews_badLocale_returnFalse() throws DAOException {
        News news = initNews();
        News tNews = initTNews();
        tNews.setTitle(null);
        String locale = "frqwe";

        boolean result = dao.addNews(news, tNews, locale);

        assertFalse(result);
    }

    @Test
    public void addNews_goodValue_returnTrue() throws DAOException {
        News news = initNews();
        News tNews = initTNews();
        String locale = "ru";

        boolean result = dao.addNews(news, tNews, locale);
        assertTrue(result);

        int id = getNewsId();
        dao.delNews(id);
    }

    @Test
    public void editNews_badNews_returnFalse() throws DAOException {
        News news = initNews();
        News tNews = initTNews();
        String locale = "ru";



        news.setTitle(null);
        boolean result = dao.editNews(news, tNews, locale);

        assertFalse(result);
    }

    @Test
    public void editNews_badTNews_returnFalse() throws DAOException {
        News news = initNews();
        News tNews = initTNews();
        tNews.setTitle(null);
        String locale = "fr";

        boolean result = dao.editNews(news, tNews, locale);

        assertFalse(result);
    }

    @Test
    public void editNews_badLocale_returnFalse() throws DAOException {
        News news = initNews();
        News tNews = initTNews();
        tNews.setTitle(null);
        String locale = "frqwe";

        boolean result = dao.editNews(news, tNews, locale);

        assertFalse(result);
    }

    @Test
    public void editNews_goodValue_returnTrue() throws DAOException{
        NewsDAO newsDAO = DAOFactory.getNewsDAO();
        News news = initNews();
        News tNews = initTNews();
        String locale = "ru";
        dao.addNews(news, tNews, locale);
        int id = getNewsId();
        String expected = "Edit news text";
        news.setText(expected);
        news.setId(id);
        tNews.setId(id);

        boolean result = dao.editNews(news, tNews, locale);
        assertTrue(result);

        News eNews = newsDAO.getNews(id, "en");
        assertEquals(expected, eNews.getText());

        dao.delNews(id);
    }

    @Test
    public void delNews_badId_returnFalse() throws DAOException{
        int id = -100;
        boolean result = dao.delNews(id);
        assertFalse(result);
    }

    @Test
    public void delNews_goodId_returnTrue() throws DAOException{
        News news = initNews();
        News tNews = initTNews();
        String locale = "ru";
        dao.addNews(news, tNews, locale);

        Integer count = getCountNews();
        Integer expected = count-1;

        Integer id = getNewsId();

        boolean result = dao.delNews(id);
        assertTrue(result);

        count = getCountNews();
        assertEquals(expected, count);
    }

    @Test
    public void addBook_badBook_returnFalse() throws DAOException{
        Book book = initBook();
        Book tBook = initTBook();
        book.setName(null);
        String lang = "ru";

        boolean result = dao.addBook(book, tBook, lang);
        assertFalse(result);
    }

    @Test
    public void addBook_badTBook_returnFalse() throws DAOException{
        Book book = initBook();
        Book tBook = initTBook();
        tBook.setName(null);
        String lang = "ru";

        boolean result = dao.addBook(book, tBook, lang);
        assertFalse(result);
    }

    @Test
    public void addBook_badLang_returnFalse() throws DAOException{
        Book book = initBook();
        Book tBook = initTBook();
        String lang = "ruqwe";

        boolean result = dao.addBook(book, tBook, lang);
        assertFalse(result);
    }

    @Test
    public void addBook_goodValue_returnTrue() throws DAOException{
        Book book = initBook();
        Book tBook = initTBook();
        String lang = "ru";
        Integer count = getCountBook();
        Integer expected = count+1;
        boolean result = dao.addBook(book, tBook, lang);
        assertTrue(result);

        assertEquals(expected, getCountBook());

        int id = getBookId();
        dao.delBook(id);
    }

    @Test
    public void editBook_badBook_returnFalse() throws DAOException{
        Book book = initBook();
        Book tBook = initTBook();
        book.setName(null);
        String lang = "ru";

        boolean result = dao.editBook(book, tBook, lang);
        assertFalse(result);
    }

    @Test
    public void editBook_badTBook_returnFalse() throws DAOException{
        Book book = initBook();
        Book tBook = initTBook();
        tBook.setName(null);
        String lang = "ru";

        boolean result = dao.editBook(book, tBook, lang);
        assertFalse(result);
    }

    @Test
    public void editBook_badLang_returnFalse() throws DAOException{
        Book book = initBook();
        Book tBook = initTBook();
        String lang = "ruqwe";

        boolean result = dao.editBook(book, tBook, lang);
        assertFalse(result);
    }

    @Test
    public void editBook_goodValue_returnTrue() throws DAOException{
        BookDAO bookDAO = DAOFactory.getBookDAO();
        Book book = initBook();
        Book tBook = initTBook();
        String lang = "ru";

        dao.addBook(book, tBook, lang);
        int id = getBookId();
        String expected = "Edit book desc";
        book.setDescription(expected);
        book.setId(id);
        tBook.setId(id);
        dao.editBook(book, tBook, lang);

        Book eBook = bookDAO.getBook(id, "en");
        assertEquals(expected, eBook.getDescription());

        dao.delBook(id);
    }

    @Test
    public void delBook_badId_returnFalse() throws DAOException{
        int id = -100;
        boolean result = dao.delBook(id);
        assertFalse(result);
    }

    @Test
    public void delBook_GoodId_returnTrue() throws DAOException{
        Book book = initBook();
        Book tBook = initTBook();
        String lang = "ru";
        dao.addBook(book, tBook, lang);

        Integer count = getCountBook();
        Integer expected = count-1;

        Integer id = getBookId();

        boolean result = dao.delBook(id);
        assertTrue(result);

        count = getCountBook();
        assertEquals(expected, count);
    }

    @Test
    public void isModerator_badLogin_returnFalse() throws DAOException{
        String login = "";

        boolean result = dao.isModerator(login);

        assertFalse(result);
    }

    @Test
    public void isModerator_userLogin_returnFalse() throws DAOException{
        String login = "123";

        boolean result = dao.isModerator(login);

        assertFalse(result);
    }

    @Test
    public void isModerator_moderLogin_returnTrue() throws DAOException{
        String login = "qwerty";

        boolean result = dao.isModerator(login);

        assertTrue(result);
    }

    @Test
    public void isModerator_adminLogin_returnTrue() throws DAOException{
        String login = "admin";

        boolean result = dao.isModerator(login);

        assertTrue(result);
    }
}