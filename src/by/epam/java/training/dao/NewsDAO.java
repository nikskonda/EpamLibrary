package by.epam.java.training.dao;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsCover;

import java.util.List;

public interface NewsDAO {

    List<NewsCover> getNewsByPage(String locale, Integer countOnPage, Integer numberOfPage);

    News getNews(Integer newsId, String locale);

    Integer calcMaxPages(String locale, Integer countNewsOnOnePage);

}
