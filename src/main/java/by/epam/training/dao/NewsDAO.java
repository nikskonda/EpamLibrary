package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.news.News;
import by.epam.training.model.news.NewsPreview;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with news in database.
 *
 * @author  Nikita Shkonda
 */
public interface NewsDAO {

    /**
     * Return list of news previews for specified page.
     *
     * @param pageAttribute - Information about page.
     *
     * @return list of news previews for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see NewsPreview
     * @see PageAttribute
     *
     */
    List<NewsPreview> getNewsPerPage(PageAttribute pageAttribute) throws DAOException;

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
     * @param countNewsOnOnePage - Count news on one page.
     *
     * @return Total count of page with news.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountNews(String locale, Integer countNewsOnOnePage) throws DAOException;

}
