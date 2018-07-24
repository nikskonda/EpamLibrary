package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.NewsDAO;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsPreview;
import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);

    private final NewsDAO newsDAO = DAOFactory.getNewsDAO();

    @Override
    public List<NewsPreview> getNewsPerPage(PageAttributes pageAttributes) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttributes)){
            return null;
        }
        return newsDAO.getNewsByPage(pageAttributes);
    }

    @Override
    public News getNews(Integer newsId, String locale) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, newsId)){
            return null;
        }
        return newsDAO.getNews(newsId, locale);
    }

    @Override
    public Integer calcPagesCountNews(String locale, Integer countNewsOnPage) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countNewsOnPage)){
            return null;
        }
        return  newsDAO.calcTotalPagesWithBooks(locale, countNewsOnPage);
    }

}
