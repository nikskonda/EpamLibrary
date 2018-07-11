package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.LordOfPages;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsCover;

import java.util.List;

public interface NewsService {

    List<NewsCover> getNewsByPage(LordOfPages pageData) throws DAOException;

    News getNews(Integer newsId, String locale) throws DAOException;

    Integer calcTotalPages(String locale, Integer countNewsOnOnePage) throws DAOException;

}
