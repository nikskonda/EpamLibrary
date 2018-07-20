package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.model.news.News;

import java.sql.SQLException;
import java.util.List;

public interface ModeratorService {

    boolean addNews(News defNews, News translatedNews, String lang) throws DAOException;

    boolean editNews(News defNews, News translatedNews, String lang) throws DAOException;

    boolean delNews(Integer newsId) throws DAOException;


    boolean addBook(Book defBook, Book translatedBook, String lang) throws DAOException;

    boolean editBook(Book defBook, Book translatedBook, String lang) throws DAOException;

    boolean delBook(Integer bookId) throws DAOException;


    boolean isModerator(String login) throws DAOException;
}
