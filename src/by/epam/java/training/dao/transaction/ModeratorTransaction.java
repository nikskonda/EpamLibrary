package by.epam.java.training.dao.transaction;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.news.News;

public interface ModeratorTransaction {


    boolean addNews(News defNews, News translatedNews, String lang) throws DAOException;

    boolean editNews(News defNews, News translatedNews, String lang) throws DAOException;

    boolean addBook(Book defBook, Book translatedBook, String lang) throws DAOException;

    boolean editBook(Book defBook, Book translatedBook, String lang) throws TransactionException, ConnectionPoolException, DAOException;

}
