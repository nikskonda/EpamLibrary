package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.news.News;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

public class TranslatedBookValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        if (!(obj instanceof Book)){
            return false;
        }

        Book book = (Book) obj;

        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, book.getName())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, book.getDescription())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, book.getPdfFileUrl())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, book.getTextFileUrl())){
            return false;
        }

        return true;
    }
}
