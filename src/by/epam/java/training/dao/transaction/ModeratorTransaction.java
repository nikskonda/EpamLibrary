package by.epam.java.training.dao.transaction;

import by.epam.java.training.dao.exception.TransactionException;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.news.News;

public interface ModeratorTransaction {

    boolean addNews(News defNews, News translatedNews, String lang) throws TransactionException;

    boolean addBook(Book defBook, Book translatedBook, String lang) throws TransactionException;

}
