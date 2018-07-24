package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsPreview;

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
     * @param pageAttributes - Information about page.
     *
     * @return list of news previews for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see NewsPreview
     * @see PageAttributes
     *
     */
    List<NewsPreview> getNewsPerPage(PageAttributes pageAttributes) throws DAOException;

    /**
     * Return information about news by id and language
     *
     * @param newsId - Id of the news.
     * @param locale - Language.
     *
     * @return information about news.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see News
     *
     */
    News getNews(Integer newsId, String locale) throws DAOException;

    /**
     * Return total count of page with news.
     *
     * @param locale - Language.
     * @param countNewsOnPage - Count news on one page.
     *
     * @return Total count of page with news.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountNews(String locale, Integer countNewsOnPage) throws DAOException;

}
