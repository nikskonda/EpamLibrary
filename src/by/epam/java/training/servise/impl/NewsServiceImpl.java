package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.NewsDAO;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsCover;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);

    private final NewsDAO newsDAO = DAOFactory.getInstance().getNewsDAO();
    private final ValidatorManager validator = new ValidatorManager();

    @Override
    public List<NewsCover> getNewsByPage(String locale, Integer countOnPage, Integer numberOfPage) {
        if (!validator.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }
        return newsDAO.getNewsByPage(locale, countOnPage, numberOfPage);
    }

    @Override
    public News getNews(Integer id, String locale) {
        if (!validator.isValid(ValidatorType.LOCALE_VALIDATOR, locale)){
            return null;
        }
        return newsDAO.getNews(id, locale);
    }
}
