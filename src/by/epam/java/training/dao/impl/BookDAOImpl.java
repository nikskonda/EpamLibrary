package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.dao.util.EncriptionMD5;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.PublishingHouse;
import by.epam.java.training.model.user.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class BookDAOImpl implements BookDAO {

    private static final Logger logger = Logger.getLogger(BookDAOImpl.class);

    private void closeResultSetAndStatement(ResultSet resultSet, Statement statement){
        try{
            if (resultSet!=null){
                resultSet.close();
            }
        }
        catch (SQLException ex){
            logger.warn("I can not close ResultSet",ex);
        }

        try{
            if (statement!=null){
                statement.close();
            }
        }
        catch (SQLException ex){
            logger.warn("I can not close Statement",ex);
        }
    }


    @Override
    public List<Book> getBooks() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ConnectionPool connectionPool = DAOFactory.getInstance().getConnectionPool();
        List<Book> books = new ArrayList<>();
        try {
            connection = connectionPool.retrieve();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL_BOOKS);

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt(BOOK_ID));
                book.setName(resultSet.getString(BOOK_DEF_NAME));
                book.setDescription(resultSet.getString(BOOK_DEF_DESCRIPTION));
                book.setPublishYear(resultSet.getInt(BOOK_PUBLISH_YEAR));
                book.setPrice(resultSet.getDouble(BOOK_PRICE));
                book.setPages(resultSet.getInt(BOOK_PAGES));
                book.setPublishingHouse(
                        new PublishingHouse(resultSet.getInt(BOOK_PUBLISHING_HOUSE_ID), "PublicHouse"));
                book.setPdfFileUrl(resultSet.getString(BOOK_DEF_PDF_FILE));
                book.setCoverUrl(resultSet.getString(BOOK_COVER));

                books.add(book);
            }
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
            System.out.println(ex.getMessage());
        } finally {
            closeResultSetAndStatement(resultSet, statement);
            try{
                connectionPool.putback(connection);
            } catch (NullPointerException ex){
                logger.warn("Connection was not received", ex);
            }
        }
        return books;
    }

    @Override
    public List<Book> getBooks(String locale){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ConnectionPool connectionPool = DAOFactory.getInstance().getConnectionPool();
        List<Book> books = new ArrayList<>();
        try {
            connection = connectionPool.retrieve();
            statement = connection.createStatement();
            String query = FIND_ALL_BOOKS_BY_LOCALE.replaceFirst(PLACE_FOR_LANGUAGE, locale);
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt(BOOK_ID));
                book.setName(resultSet.getString(BOOK_NAME));
                book.setDescription(resultSet.getString(BOOK_DESCRIPTION));
                book.setPublishYear(resultSet.getInt(BOOK_PUBLISH_YEAR));
                book.setPrice(resultSet.getDouble(BOOK_PRICE));
                book.setPages(resultSet.getInt(BOOK_PAGES));
                book.setPublishingHouse(
                        new PublishingHouse(resultSet.getInt(BOOK_PUBLISHING_HOUSE_ID), "PublicHouse"));
                book.setPdfFileUrl(resultSet.getString(BOOK_PDF_FILE));
                book.setCoverUrl(resultSet.getString(BOOK_COVER));

                books.add(book);
            }
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
            System.out.println(ex.getMessage());
        } finally {
            closeResultSetAndStatement(resultSet, statement);
            try{
                connectionPool.putback(connection);
            } catch (NullPointerException ex){
                logger.warn("Connection was not received", ex);
            }
        }
        return books;
    }

}
