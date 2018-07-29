package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.ModeratorDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.dao.transaction.AbstractTransaction;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.model.news.News;
import org.apache.log4j.Logger;

import java.sql.*;

import static by.epam.java.training.dao.util.SQLRequestConstant.*;

/**
 * Implement an interface that defines methods that allow you to moderate information in database.
 *
 * @author  Nikita Shkonda
 */
public class ModeratorDAOImpl extends AbstractTransaction implements ModeratorDAO {

    private static final Logger logger = Logger.getLogger(ModeratorDAOImpl.class);

    /**
     * Add new news in system.
     *
     * @param defNews - Information about news on English.
     * @param lang - Language of translated news.
     * @param translatedNews - Information about translated news on lang.
     *
     * @return <tt>true<tt/> if added was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
    @Override
    public boolean addNews(News defNews, News translatedNews, String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try{
            con = retrieveConnection();
            startTransaction(con);

            Integer newsId = addDefNews(defNews, con);
            translatedNews.setId(newsId);
            addTranslatedNews(translatedNews, lang, con);

            closeTransaction(con);
            result = true;
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } catch (TransactionException ex){
            throw new DAOException(ex);
        } catch (SQLException ex){
            rollback(con);
            throw new DAOException(ex);
        } finally {
            putbackConnection(con);
        }
        return result;
    }

    /**
     * Edits existing news.
     *
     * @param defNews - Information about news on English.
     * @param lang - Language of translated news.
     * @param translatedNews - Information about translated news on lang.
     *
     * @return <tt>true<tt/> if edited was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
    @Override
    public boolean editNews(News defNews, News translatedNews, String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try{
            con = retrieveConnection();
            startTransaction(con);

            updateDefNews(defNews, con);
            updateTranslatedNews(translatedNews, lang, con);

            closeTransaction(con);
            result = true;
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } catch (TransactionException ex){
            throw new DAOException(ex);
        } catch (SQLException ex){
            rollback(con);
            throw new DAOException(ex);
        } finally {
            putbackConnection(con);
        }
        return result;
    }

    /**
     * Delete the news by id.
     *
     * @param newsId - Id of the news
     *
     * @return <tt>true<tt/> if deleted was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
    @Override
    public boolean delNews(Integer newsId) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(DELETE_NEWS);
            cstmt.setInt(NEWS_ID, newsId);
            cstmt.executeQuery();

            result = true;
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    /**
     * Add new book in system.
     *
     * @param defBook - Information about book on English.
     * @param lang - Language of translated book.
     * @param translatedBook - Information about translated book on lang.
     *
     * @return <tt>true<tt/> if added was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Book
     *
     */
    @Override
    public boolean addBook(Book defBook, Book translatedBook, String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try{
            con = retrieveConnection();
            startTransaction(con);

            Integer bookId = addDefBook(defBook, con);
            translatedBook.setId(bookId);
            addTranslatedBook(translatedBook, lang, con);
            for (Genre genre : defBook.getGenres()){
                addGenres(bookId, genre.getId(), con);
            }

            closeTransaction(con);
            result = true;
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } catch (TransactionException ex){
            throw new DAOException(ex);
        } catch (SQLException ex){
            rollback(con);
            throw new DAOException(ex);
        } finally {
            putbackConnection(con);
        }
        return result;
    }

    /**
     * Edits existing news.
     *
     * @param defBook - Information about book on English.
     * @param lang - Language of translated book.
     * @param translatedBook - Information about translated book on lang.
     *
     * @return <tt>true<tt/> if edited was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Book
     *
     */
    @Override
    public boolean editBook(Book defBook, Book translatedBook, String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try{
            con = retrieveConnection();
            startTransaction(con);

            updateDefBook(defBook, con);
            updateTranslatedBook(translatedBook, lang, con);
            deleteGenres(defBook.getId(), con);
            for (Genre genre : defBook.getGenres()){
                addGenres(defBook.getId(), genre.getId(), con);
            }

            closeTransaction(con);
            result = true;
        } catch (ConnectionPoolException ex) {
            throw new DAOException(ex);
        } catch (TransactionException ex){
            throw new DAOException(ex);
        } catch (SQLException ex){
            rollback(con);
            throw new DAOException(ex);
        } finally {
            putbackConnection(con);
        }
        return result;
    }

    /**
     * Delete the book by id.
     *
     * @param bookId - Id of the book.
     *
     * @return <tt>true<tt/> if deleted was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
    @Override
    public boolean delBook(Integer bookId) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(DELETE_BOOK);
            cstmt.setInt(BOOK_ID, bookId);
            cstmt.executeQuery();

            result = true;
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    /**
     * Return true if the login belongs to the moderator.
     *
     * @param login - Login of the user.
     *
     * @return <tt>true</tt> if the login belongs to the moderator.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public boolean isModerator(String login) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(IS_MODERATOR);
            cstmt.setString(USER_LOGIN, login);
            cstmt.registerOutParameter(RESULT, Types.BOOLEAN);
            cstmt.executeQuery();

            result = cstmt.getBoolean(RESULT);
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    private int addDefNews(News defNews, Connection con) throws SQLException, DAOException{
        Integer newsId = null;
        CallableStatement cstmt = con.prepareCall(ADD_NEWS);

        setNews(defNews, cstmt);
        cstmt.registerOutParameter(NEWS_ID, Types.SMALLINT);
        cstmt.executeQuery();

        newsId = cstmt.getInt(NEWS_ID);
        closeStatement(cstmt);

        return newsId;
    }

    private void addTranslatedNews(News translatedNews, String lang, Connection con) throws SQLException, DAOException{
        CallableStatement cstmt = con.prepareCall(ADD_TRANSLATED_NEWS);

        cstmt.setInt(NEWS_ID, translatedNews.getId());
        setTNews(translatedNews, cstmt);
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeStatement(cstmt);
    }

    private void updateDefNews(News defNews, Connection con) throws SQLException, DAOException{
        CallableStatement cstmt = con.prepareCall(UPDATE_NEWS);

        cstmt.setInt(NEWS_ID, defNews.getId());
        setNews(defNews, cstmt);
        cstmt.executeQuery();

        closeStatement(cstmt);
    }

    private void updateTranslatedNews(News translatedNews, String lang, Connection con) throws SQLException, DAOException{
        CallableStatement cstmt = con.prepareCall(UPDATE_TRANSLATED_NEWS);

        cstmt.setInt(NEWS_ID, translatedNews.getId());
        setTNews(translatedNews, cstmt);
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeStatement(cstmt);
    }

    private void setNews(News news, CallableStatement cstmt) throws SQLException{
        setTNews(news, cstmt);
        cstmt.setString(NEWS_PHOTO_URL, news.getPhotoUrl());
        cstmt.setString(NEWS_THUMBS_URL, news.getThumbsUrl());
        cstmt.setInt(USER_ID, news.getUserId());
    }

    private void setTNews(News news, CallableStatement cstmt) throws SQLException{
        cstmt.setString(NEWS_TITLE, news.getTitle());
        cstmt.setString(NEWS_TEXT, news.getText());
    }

    private int addDefBook(Book defBook, Connection con) throws SQLException, DAOException{
        Integer newsId = null;
        CallableStatement cstmt = con.prepareCall(ADD_BOOK);

        setBook(defBook, cstmt);
        cstmt.registerOutParameter(BOOK_ID, Types.SMALLINT);
        cstmt.executeQuery();

        newsId = cstmt.getInt(BOOK_ID);
        closeStatement(cstmt);

        return newsId;
    }

    private void addGenres(Integer bookId, Integer genreId, Connection con) throws SQLException, DAOException{
        CallableStatement cstmt = con.prepareCall(ADD_BOOK_GENRES);

        cstmt.setInt(BOOK_ID, bookId);
        cstmt.setInt(GENRE_ID, genreId);

        cstmt.executeQuery();
        closeStatement(cstmt);
    }

    private void addTranslatedBook(Book translatedBook, String lang, Connection con) throws SQLException, DAOException{
        CallableStatement cstmt = con.prepareCall(ADD_TRANSLATED_BOOK);

        cstmt.setInt(BOOK_ID, translatedBook.getId());
        setTBook(translatedBook, cstmt);
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeStatement(cstmt);
    }

    private void setBook(Book book, CallableStatement cstmt) throws SQLException{
        setTBook(book, cstmt);
        cstmt.setInt(BOOK_PUBLISH_YEAR, book.getPublishYear());
        cstmt.setDouble(BOOK_PRICE, book.getPrice());
        cstmt.setInt(BOOK_PAGES, book.getPages());
        cstmt.setString(BOOK_PUBLISHING_HOUSE_NAME,
                book.getPublishingHouse().getName());
        cstmt.setString(BOOK_COVER_URL, book.getCoverUrl());
    }

    private void setTBook(Book tBook, CallableStatement cstmt) throws SQLException{
        cstmt.setString(BOOK_NAME, tBook.getName());
        cstmt.setString(BOOK_DESCRIPTION, tBook.getDescription());
        cstmt.setString(BOOK_PDF_FILE_URL, tBook.getPdfFileUrl());
        cstmt.setString(BOOK_TEXT_FILE_URL, tBook.getTextFileUrl());
        cstmt.setString(BOOK_AUTHORS, tBook.getAuthors());
    }

    private void deleteGenres(Integer bookId, Connection con) throws SQLException, DAOException{
        CallableStatement cstmt = con.prepareCall(DELETE_BOOK_GENRES);

        cstmt.setInt(BOOK_ID, bookId);

        cstmt.executeQuery();
        closeStatement(cstmt);
    }

    private void updateDefBook(Book defBook, Connection con) throws SQLException, DAOException{
        CallableStatement cstmt = con.prepareCall(UPDATE_BOOK);

        cstmt.setInt(BOOK_ID, defBook.getId());
        setBook(defBook, cstmt);
        cstmt.setInt(BOOK_ID, defBook.getId());
        cstmt.executeQuery();

        closeStatement(cstmt);
    }

    private void updateTranslatedBook(Book translatedBook, String lang, Connection con) throws SQLException, DAOException{
        CallableStatement cstmt = con.prepareCall(UPDATE_TRANSLATED_BOOK);

        cstmt.setInt(BOOK_ID, translatedBook.getId());
        setTBook(translatedBook, cstmt);
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeStatement(cstmt);
    }
}
