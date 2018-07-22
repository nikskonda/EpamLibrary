package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.AdministratorDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.ModeratorDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.dao.transaction.ModeratorTransaction;
import by.epam.java.training.dao.transaction.TransactionFactory;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.SignInForm;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class ModeratorDAOImpl extends AbstractDAO implements ModeratorDAO {

    private static final Logger logger = Logger.getLogger(ModeratorDAOImpl.class);

    private static final ModeratorTransaction transaction = TransactionFactory.getModeratorTransaction();

    @Override
    public boolean addNews(News defNews, News translatedNews, String lang) throws DAOException {
        return transaction.addNews(defNews, translatedNews, lang);
    }

    @Override
    public boolean editNews(News defNews, News translatedNews, String lang) throws DAOException {
        return transaction.editNews(defNews, translatedNews, lang);
    }

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
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    @Override
    public boolean addBook(Book defBook, Book translatedBook, String lang) throws DAOException {
        return transaction.addBook(defBook, translatedBook, lang);
    }

    @Override
    public boolean editBook(Book defBook, Book translatedBook, String lang) throws DAOException {
        return transaction.editBook(defBook, translatedBook, lang);
    }

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
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

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
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }
}
