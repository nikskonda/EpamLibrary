package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

public class NewBookmarkValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        if (!(obj instanceof Bookmark)){
            return false;
        }

        Bookmark bookmark = (Bookmark) obj;

        if (!ValidatorManager.isValid(ValidatorType.BOOKMARK_VALIDATOR, bookmark)){
            return false;
        }
        if (!(ValidatorManager.isValid(ValidatorType.ID_VALIDATOR, bookmark.getPageNumber()))){
            return false;
        }

        return true;
    }
}
