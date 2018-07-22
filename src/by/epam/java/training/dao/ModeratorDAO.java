package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.SignInForm;

import java.sql.SQLException;
import java.util.List;

public interface ModeratorDAO {

    boolean addNews(News defNews, News translatedNews, String lang) throws ConnectionPoolException, TransactionException, DAOException;

    boolean editNews(News defNews, News translatedNews, String lang) throws ConnectionPoolException, TransactionException, DAOException;

    boolean delNews(Integer newsId) throws DAOException;


    boolean addBook(Book defBook, Book translatedBook, String lang) throws ConnectionPoolException, TransactionException, DAOException;

    boolean editBook(Book defBook, Book translatedBook, String lang) throws ConnectionPoolException, TransactionException, DAOException;

    boolean delBook(Integer bookId) throws DAOException;

    boolean isModerator(String login) throws DAOException;


}
