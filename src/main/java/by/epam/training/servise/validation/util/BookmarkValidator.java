package by.epam.training.servise.validation.util;

import by.epam.training.model.book.Bookmark;
import by.epam.training.servise.validation.Validator;
import by.epam.training.servise.validation.ValidatorManager;
import by.epam.training.servise.validation.ValidatorType;

/**
 * The class for checking bookmarks for validity.
 *
 * @author  Nikita Shkonda
 *
 */
public class BookmarkValidator implements Validator {

    /**
     * The method returns <tt>true<tt/> if the bookmark is valid.
     *
     * @author  Nikita Shkonda
     *
     */
    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof Bookmark)){
            return false;
        }

        Bookmark bookmark = (Bookmark) obj;

        Validator idValidator = ValidatorManager.getValidator(ValidatorType.NATURAL_NUMBER_VALIDATOR);

        if (!(idValidator.isValid(bookmark.getUserId()))){
            return false;
        }
        if (!(idValidator.isValid(bookmark.getBookId()))){
            return false;
        }
        if (!(ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, bookmark.getLocale()))){
            return false;
        }

        return true;
    }
}
