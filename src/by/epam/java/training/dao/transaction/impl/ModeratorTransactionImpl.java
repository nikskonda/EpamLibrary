package by.epam.java.training.dao.transaction.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
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

public class ModeratorTransactionImpl extends AbstractDAO implements ModeratorTransaction {
    private static final Logger logger = Logger.getLogger(ModeratorTransactionImpl.class);

    @Override
    public boolean addNews(News defNews, News translatedNews, String lang) throws TransactionException, ConnectionPoolException {
        Connection con = null;
        CallableStatement cstmt = null;
        ConnectionPool conPool = TransactionFactory.getConnectionPool();
        boolean result = false;
        try{
            con = conPool.retrieve();
            con.setAutoCommit(false);

            Integer newsId = addDefNews(defNews, con);
            translatedNews.setId(newsId);
            addTranslatedNews(translatedNews, lang, con);

            con.commit();
            con.setAutoCommit(true);

            result = true;
        } catch (ConnectionPoolException ex) {
            logger.warn("Error connecting to database", ex);
            throw new ConnectionPoolException(ex);
        } catch (SQLException ex){
            logger.warn("Transaction failed, rolling back.", ex);
            try
            {
                con.rollback ();
                con.setAutoCommit (true);
            }
            catch (SQLException sqlEx) {
                logger.warn("Rolling back failed.", sqlEx);
            }
            throw new TransactionException(ex);
        }  finally {
            putbackConnection(con, conPool);
        }
        return result;
    }

    private int addDefNews(News defNews, Connection con) throws SQLException{
        Integer newsId = null;
        CallableStatement cstmt = con.prepareCall(ADD_NEWS);

        cstmt.setString(NEWS_TITLE, defNews.getTitle());
        cstmt.setString(NEWS_TEXT, defNews.getText());
        cstmt.setString(NEWS_PHOTO_URL, defNews.getPhotoUrl());
        cstmt.setString(NEWS_THUMBS_URL, defNews.getThumbsUrl());
        cstmt.setInt(USER_ID, defNews.getUserId());
        cstmt.registerOutParameter(NEWS_ID, Types.SMALLINT);
        cstmt.executeQuery();
        newsId = cstmt.getInt(NEWS_ID);
        closeCallableStatement(cstmt);

        return newsId;
    }

    private void addTranslatedNews(News translatedNews, String lang, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(ADD_TRANSLATED_NEWS);

        cstmt.setInt(NEWS_ID, translatedNews.getId());
        cstmt.setString(NEWS_TITLE, translatedNews.getTitle());
        cstmt.setString(NEWS_TEXT, translatedNews.getText());
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeCallableStatement(cstmt);
    }


    @Override
    public boolean editNews(News defNews, News translatedNews, String lang) throws TransactionException, ConnectionPoolException {
        Connection con = null;
        CallableStatement cstmt = null;
        ConnectionPool conPool = TransactionFactory.getConnectionPool();
        boolean result = false;
        try{
            con = conPool.retrieve();
            con.setAutoCommit(false);

            updateDefNews(defNews, con);
            updateTranslatedNews(translatedNews, lang, con);

            con.commit();
            con.setAutoCommit(true);

            result = true;
        } catch (ConnectionPoolException ex) {
            logger.warn("Error connecting to database", ex);
            throw new ConnectionPoolException(ex);
        } catch (SQLException ex){
            logger.warn("Transaction failed, rolling back.", ex);
            try
            {
                con.rollback ();
                con.setAutoCommit (true);
            }
            catch (SQLException sqlEx) {
                logger.warn("Rolling back failed.", sqlEx);
            }
            throw new TransactionException(ex);
        }  finally {
            putbackConnection(con, conPool);
        }
        return result;
    }

    private void updateDefNews(News defNews, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(UPDATE_NEWS);

        cstmt.setInt(NEWS_ID, defNews.getId());
        cstmt.setString(NEWS_TITLE, defNews.getTitle());
        cstmt.setString(NEWS_TEXT, defNews.getText());
        cstmt.setString(NEWS_PHOTO_URL, defNews.getPhotoUrl());
        cstmt.setString(NEWS_THUMBS_URL, defNews.getThumbsUrl());
        cstmt.setInt(USER_ID, defNews.getUserId());
        cstmt.executeQuery();

        closeCallableStatement(cstmt);
    }

    private void updateTranslatedNews(News translatedNews, String lang, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(UPDATE_TRANSLATED_NEWS);

        cstmt.setInt(NEWS_ID, translatedNews.getId());
        cstmt.setString(NEWS_TITLE, translatedNews.getTitle());
        cstmt.setString(NEWS_TEXT, translatedNews.getText());
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeCallableStatement(cstmt);
    }


    @Override
    public boolean addBook(Book defBook, Book translatedBook, String lang) throws TransactionException, ConnectionPoolException {
        Connection con = null;
        CallableStatement cstmt = null;
        ConnectionPool conPool = TransactionFactory.getConnectionPool();
        boolean result = false;
        try{
            con = conPool.retrieve();
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
            try
            {
                con.rollback ();
                con.setAutoCommit (true);
            }
            catch (SQLException sqlEx) {
                logger.warn("Rolling back failed.", sqlEx);
            }
            throw new TransactionException(ex);
        }  finally {
            putbackConnection(con, conPool);
        }
        return result;
    }

    private int addDefBook(Book defBook, Connection con) throws SQLException{
        Integer newsId = null;
        CallableStatement cstmt = con.prepareCall(ADD_BOOK);

        cstmt.setString(BOOK_NAME, defBook.getName());
        cstmt.setString(BOOK_DESCRIPTION, defBook.getDescription());
        cstmt.setInt(BOOK_PUBLISH_YEAR, defBook.getPublishYear());
        cstmt.setDouble(BOOK_PRICE, defBook.getPrice());
        cstmt.setInt(BOOK_PAGES, defBook.getPages());
        cstmt.setString(BOOK_PUBLISHING_HOUSE_NAME,
                defBook.getPublishingHouse().getName());
        cstmt.setString(BOOK_PDF_FILE_URL, defBook.getPdfFileUrl());
        cstmt.setString(BOOK_COVER_URL, defBook.getCoverUrl());
        cstmt.setString(BOOK_TEXT_FILE_URL, defBook.getTextFileUrl());

        cstmt.registerOutParameter(BOOK_ID, Types.SMALLINT);
        cstmt.executeQuery();
        newsId = cstmt.getInt(BOOK_ID);
        closeCallableStatement(cstmt);

        return newsId;
    }

    private void addGenres(Integer bookId, Integer genreId, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(ADD_BOOK_GENRES);

        cstmt.setInt(BOOK_ID, bookId);
        cstmt.setInt(GENRE_ID, genreId);

        cstmt.executeQuery();
        closeCallableStatement(cstmt);
    }

    private void addTranslatedBook(Book translatedBook, String lang, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(ADD_TRANSLATED_BOOK);

        cstmt.setInt(BOOK_ID, translatedBook.getId());
        cstmt.setString(BOOK_NAME, translatedBook.getName());
        cstmt.setString(BOOK_DESCRIPTION, translatedBook.getDescription());
        cstmt.setString(BOOK_PDF_FILE_URL, translatedBook.getPdfFileUrl());
        cstmt.setString(BOOK_TEXT_FILE_URL, translatedBook.getTextFileUrl());
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeCallableStatement(cstmt);
    }


    @Override
    public boolean editBook(Book defBook, Book translatedBook, String lang) throws TransactionException, ConnectionPoolException{
        Connection con = null;
        CallableStatement cstmt = null;
        ConnectionPool conPool = TransactionFactory.getConnectionPool();
        boolean result = false;
        try{
            con = conPool.retrieve();
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
            throw new ConnectionPoolException(ex);
        } catch (SQLException ex){
            logger.warn("Transaction failed, rolling back.", ex);
            try
            {
                con.rollback ();
                con.setAutoCommit (true);
            }
            catch (SQLException sqlEx) {
                logger.warn("Rolling back failed.", sqlEx);
            }
            throw new TransactionException(ex);
        }  finally {
            putbackConnection(con, conPool);
        }
        return result;
    }

    private void deleteGenres(Integer bookId, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(DELETE_BOOK_GENRES);

        cstmt.setInt(BOOK_ID, bookId);

        cstmt.executeQuery();
        closeCallableStatement(cstmt);
    }

    private void updateDefBook(Book defBook, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(UPDATE_BOOK);

        cstmt.setInt(BOOK_ID, defBook.getId());
        cstmt.setString(BOOK_NAME, defBook.getName());
        cstmt.setString(BOOK_DESCRIPTION, defBook.getDescription());
        cstmt.setInt(BOOK_PUBLISH_YEAR, defBook.getPublishYear());
        cstmt.setDouble(BOOK_PRICE, defBook.getPrice());
        cstmt.setInt(BOOK_PAGES, defBook.getPages());
        cstmt.setString(BOOK_PUBLISHING_HOUSE_NAME,
                defBook.getPublishingHouse().getName());
        cstmt.setString(BOOK_PDF_FILE_URL, defBook.getPdfFileUrl());
        cstmt.setString(BOOK_COVER_URL, defBook.getCoverUrl());
        cstmt.setString(BOOK_TEXT_FILE_URL, defBook.getTextFileUrl());
        cstmt.setInt(BOOK_ID, defBook.getId());
        cstmt.executeQuery();

        closeCallableStatement(cstmt);
    }

    private void updateTranslatedBook(Book translatedBook, String lang, Connection con) throws SQLException{
        CallableStatement cstmt = con.prepareCall(UPDATE_TRANSLATED_BOOK);

        cstmt.setInt(BOOK_ID, translatedBook.getId());
        cstmt.setString(BOOK_NAME, translatedBook.getName());
        cstmt.setString(BOOK_DESCRIPTION, translatedBook.getDescription());
        cstmt.setString(BOOK_PDF_FILE_URL, translatedBook.getPdfFileUrl());
        cstmt.setString(BOOK_TEXT_FILE_URL, translatedBook.getTextFileUrl());
        cstmt.setString(LOCALE, lang);
        cstmt.executeQuery();

        closeCallableStatement(cstmt);
    }
}
