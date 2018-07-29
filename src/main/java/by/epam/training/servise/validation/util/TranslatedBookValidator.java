package by.epam.training.servise.validation.util;

import by.epam.training.model.book.Book;
import by.epam.training.servise.validation.Validator;
import by.epam.training.servise.validation.ValidatorManager;
import by.epam.training.servise.validation.ValidatorType;

/**
 * The class for checking translated book for validity.
 *
 * @author  Nikita Shkonda
 *
 */
public class TranslatedBookValidator implements Validator {

    /**
     * The method returns <tt>true<tt/> if the translated book is valid.
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
