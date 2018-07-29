package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

/**
 * The class for checking bookmarks for validity.
 *
 * @author  Nikita Shkonda
 */
public class NewBookmarkValidator implements Validator {

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

        if (!ValidatorManager.isValid(ValidatorType.BOOKMARK_VALIDATOR, bookmark)){
            return false;
        }
        if (!(ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, bookmark.getPageNumber()))){
            return false;
        }

        return true;
    }
}
