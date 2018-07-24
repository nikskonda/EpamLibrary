package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsPreview;

import java.util.List;

public interface NewsDAO {

    List<NewsPreview> getNewsByPage(PageAttributes pageData) throws DAOException;

    News getNews(Integer newsId, String locale) throws DAOException;

    Integer calcTotalPagesWithBooks(String locale, Integer countNewsOnOnePage) throws DAOException;

}
