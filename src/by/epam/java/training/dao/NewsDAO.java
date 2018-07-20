package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsCover;

import java.util.List;

public interface NewsDAO {

    List<NewsCover> getNewsByPage(PageAttributes pageData) throws DAOException;

    News getNews(Integer newsId, String locale) throws DAOException;

    Integer calcTotalPagesWithBooks(String locale, Integer countNewsOnOnePage) throws DAOException;

}
