package by.epam.java.training.servise;

import by.epam.java.training.model.news.News;
import by.epam.java.training.model.news.NewsCover;

import java.util.List;

public interface NewsService {

    List<NewsCover> getNewsByPage(String locale, Integer countOnPage, Integer numberOfPage);

    News getNews(Integer id, String locale);

    Integer calcMaxPages(String locale, Integer countNewsOnOnePage);
}
