package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

public class BookValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof Book)){
            return false;
        }

        Book book = (Book) obj;

        if (!ValidatorManager.isValid(ValidatorType.TRANSLATED_BOOK_VALIDATOR, book)){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.DOUBLE_VALIDATION, book.getPrice())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, book.getPages())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, book.getPublishingHouse().getName())){
            return false;
        }
        if (!ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, book.getCoverUrl())){
            return false;
        }

        return true;
    }
}
