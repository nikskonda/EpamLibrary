package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.NewsDAO;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsPreview;
import by.epam.java.training.servise.NewsService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Implementing the {@link NewsService} interface for different activities with news.
 *
 * @author  Nikita Shkonda
 */
public class NewsServiceImpl implements NewsService {
    private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);

    private final NewsDAO newsDAO = DAOFactory.getNewsDAO();

    /**
     * Return list of news previews for specified page.
     *
     * @param pageAttribute - Information about page.
     *
     * @return list of news previews for specified page.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see NewsPreview
     * @see PageAttribute
     *
     */
    @Override
    public List<NewsPreview> getNewsPerPage(PageAttribute pageAttribute) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttribute)){
            return null;
        }
        try{
            return newsDAO.getNewsPerPage(pageAttribute);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    /**
     * Return information about news by id and language
     *
     * @param newsId - Id of the news.
     * @param locale - Language.
     *
     * @return information about news.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see News
     *
     */
    @Override
    public News getNews(Integer newsId, String locale) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, newsId)){
            return null;
        }

        try{
            return newsDAO.getNews(newsId, locale);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    /**
     * Return total count of page with news.
     *
     * @param locale - Language.
     * @param countNewsOnOnePage - Count news on one page.
     *
     * @return Total count of page with news.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
    @Override
    public Integer calcPagesCountNews(String locale, Integer countNewsOnOnePage) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countNewsOnOnePage)){
            return null;
        }

        try{
            return  newsDAO.calcPagesCountNews(locale, countNewsOnOnePage);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

}
