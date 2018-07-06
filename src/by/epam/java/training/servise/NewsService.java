package by.epam.java.training.servise;

import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsConstr;
import by.epam.java.training.model.news.NewsCover;
import by.epam.java.training.model.news.NewsLang;

import java.util.List;

public interface NewsService {

    List<NewsCover> getNewsByPage(String locale, Integer countOnPage, Integer numberOfPage);

    News getNews(Integer newsId, String locale);

    Integer calcMaxPages(String locale, Integer countNewsOnOnePage);

    void addNews(NewsConstr defNews, NewsLang news);
}
