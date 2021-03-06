package by.epam.training.servise.validation.util;

import by.epam.training.model.book.Book;
import by.epam.training.servise.validation.Validator;
import by.epam.training.servise.validation.ValidatorManager;
import by.epam.training.servise.validation.ValidatorType;

/**
 * The class for checking book for validity.
 *
 * @author  Nikita Shkonda
 */
public class BookValidator implements Validator {

    /**
     * The method returns <tt>true<tt/> if the book is valid.
     *
     * @author  Nikita Shkonda
     *
     */
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
