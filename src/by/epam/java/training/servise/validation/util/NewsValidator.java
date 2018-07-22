package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.model.news.News;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

public class NewsValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof News)){
            return false;
        }

        News news = (News) obj;

        if (!ValidatorManager.isValid(ValidatorType.TRANSLATED_NEWS_VALIDATOR, news)){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, news.getText())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, news.getPhotoUrl())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, news.getThumbsUrl())){
            return false;
        }

        return true;
    }
}
