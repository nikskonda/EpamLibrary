package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.LordOfPages;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

public class PagesValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        if (!(obj instanceof LordOfPages)){
            return false;
        }

        LordOfPages pageData = (LordOfPages) obj;

        Validator idValidator = ValidatorManager.getValidator(ValidatorType.ID_VALIDATOR);

        if (!(idValidator.isValid(pageData.getNumberOfPage()))){
            return false;
        }
        if (!(idValidator.isValid(pageData.getCountOnPage()))){
            return false;
        }
        if (!(ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, pageData.getLocale()))){
            return false;
        }

        return true;
    }
}
