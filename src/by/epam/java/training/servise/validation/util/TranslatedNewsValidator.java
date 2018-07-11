package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.model.news.News;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

public class TranslatedNewsValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        if (!(obj instanceof News)){
            return false;
        }

        News news = (News) obj;

        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, news.getTitle())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, news.getText())){
            return false;
        }

        return true;
    }
}
