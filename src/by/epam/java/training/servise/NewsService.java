package by.epam.java.training.servise;

import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsPreview;
import by.epam.java.training.servise.exception.ServiceException;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with news.
 *
 * @author  Nikita Shkonda
 */
public interface NewsService {

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
    List<NewsPreview> getNewsPerPage(PageAttribute pageAttribute) throws ServiceException;

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
    News getNews(Integer newsId, String locale) throws ServiceException;

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
    Integer calcPagesCountNews(String locale, Integer countNewsOnOnePage) throws ServiceException;

}
