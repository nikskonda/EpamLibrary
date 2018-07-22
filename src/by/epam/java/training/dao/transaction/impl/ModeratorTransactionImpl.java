package by.epam.java.training.dao.transaction.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.transaction.AbstractTransaction;
import by.epam.java.training.dao.transaction.ModeratorTransaction;
import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.dao.transaction.TransactionFactory;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.model.news.News;
import org.apache.log4j.Logger;

import java.sql.*;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class ModeratorTransactionImpl extends AbstractTransaction implements ModeratorTransaction {

    private static final Logger logger = Logger.getLogger(ModeratorTransactionImpl.class);


    @Override
    public boolean addNews(News defNews, News translatedNews, String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try{
            con = retrieveConnection();
            con.setAutoCommit(false);

            Integer newsId = addDefNews(defNews, con);
            translatedNews.setId(newsId);
            addTranslatedNews(translatedNews, lang, con);

            con.commit();
            con.setAutoCommit(true);
            result = true;
        } catch (ConnectionPoolException ex) {
            logger.warn("Error connecting to database", ex);
            throw new DAOException(ex);
        } catch (SQLException ex){
            logger.warn("Transaction failed, rolling back.", ex);
            rollback(con);
            throw new DAOException(ex);
        }  finally {
            putbackConnection(con);
        }
        return result;
    }

    private int addDefNews(News defNews, Connection con) throws SQLException{
        Integer newsId = null;
        CallableStatement cstmt = con.prepareCall(ADD_NEWS);

        setNews(defNews, cstmt);
        cstmt.registerOutParameter(NEWS_ID, Types.SMALLINT);
        cstmt.executeQuery();

        newsId = cstmt.getInt(NEWS_ID);
        closeStatement(cstmt);

        return newsId;
    }

    private void addTranslatedNews(News translatedNews, String lang, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(ADD_TRANSLATED_NEWS);

        cstmt.setInt(NEWS_ID, translatedNews.getId());
        setTNews(translatedNews, cstmt);
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeStatement(cstmt);
    }

    @Override
    public boolean editNews(News defNews, News translatedNews, String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try{
            con = retrieveConnection();
            con.setAutoCommit(false);

            updateDefNews(defNews, con);
            updateTranslatedNews(translatedNews, lang, con);

            con.commit();
            con.setAutoCommit(true);
            result = true;
        } catch (ConnectionPoolException ex) {
            logger.warn("Error connecting to database", ex);
            throw new DAOException(ex);
        } catch (SQLException ex){
            logger.warn("Transaction failed, rolling back.", ex);
            rollback(con);
            throw new DAOException(ex);
        }  finally {
            putbackConnection(con);
        }
        return result;
    }

    private void updateDefNews(News defNews, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(UPDATE_NEWS);

        cstmt.setInt(NEWS_ID, defNews.getId());
        setNews(defNews, cstmt);
        cstmt.executeQuery();

        closeStatement(cstmt);
    }

    private void updateTranslatedNews(News translatedNews, String lang, Connection con) throws SQLException{
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

    @Override
    public boolean addBook(Book defBook, Book translatedBook, String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try{
            con = retrieveConnection();
            con.setAutoCommit(false);

            Integer bookId = addDefBook(defBook, con);
            translatedBook.setId(bookId);
            addTranslatedBook(translatedBook, lang, con);
            for (Genre genre : defBook.getGenres()){
                addGenres(bookId, genre.getId(), con);
            }

            con.commit();
            con.setAutoCommit(true);
            result = true;
        } catch (ConnectionPoolException ex) {
            logger.warn("Error connecting to database", ex);
            throw new ConnectionPoolException(ex);
        } catch (SQLException ex){
            logger.warn("Transaction failed, rolling back.", ex);
            rollback(con);
            throw new TransactionException(ex);
        }  finally {
            putbackConnection(con);
        }
        return result;
    }

    private int addDefBook(Book defBook, Connection con) throws SQLException{
        Integer newsId = null;
        CallableStatement cstmt = con.prepareCall(ADD_BOOK);

        setBook(defBook, cstmt);
        cstmt.registerOutParameter(BOOK_ID, Types.SMALLINT);
        cstmt.executeQuery();

        newsId = cstmt.getInt(BOOK_ID);
        closeStatement(cstmt);

        return newsId;
    }

    private void addGenres(Integer bookId, Integer genreId, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(ADD_BOOK_GENRES);

        cstmt.setInt(BOOK_ID, bookId);
        cstmt.setInt(GENRE_ID, genreId);

        cstmt.executeQuery();
        closeStatement(cstmt);
    }

    private void addTranslatedBook(Book translatedBook, String lang, Connection con) throws SQLException{
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


    @Override
    public boolean editBook(Book defBook, Book translatedBook, String lang) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try{
            con = retrieveConnection();
            con.setAutoCommit(false);

            updateDefBook(defBook, con);
            updateTranslatedBook(translatedBook, lang, con);
            deleteGenres(defBook.getId(), con);
            for (Genre genre : defBook.getGenres()){
                addGenres(defBook.getId(), genre.getId(), con);
            }
            con.commit();
            con.setAutoCommit(true);

            result = true;
        } catch (ConnectionPoolException ex) {
            logger.warn("Error connecting to database", ex);
            throw new DAOException(ex);
        } catch (SQLException ex){
            logger.warn("Transaction failed, rolling back.", ex);
           rollback(con);
            throw new DAOException(ex);
        }  finally {
            putbackConnection(con);
        }
        return result;
    }

    private void deleteGenres(Integer bookId, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(DELETE_BOOK_GENRES);

        cstmt.setInt(BOOK_ID, bookId);

        cstmt.executeQuery();
        closeStatement(cstmt);
    }

    private void updateDefBook(Book defBook, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(UPDATE_BOOK);

        cstmt.setInt(BOOK_ID, defBook.getId());
        setBook(defBook, cstmt);
        cstmt.setInt(BOOK_ID, defBook.getId());
        cstmt.executeQuery();

        closeStatement(cstmt);
    }

    private void updateTranslatedBook(Book translatedBook, String lang, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(UPDATE_TRANSLATED_BOOK);

        cstmt.setInt(BOOK_ID, translatedBook.getId());
        setTBook(translatedBook, cstmt);
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeStatement(cstmt);
    }
}
