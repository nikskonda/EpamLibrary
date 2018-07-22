package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

public class PagesValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof PageAttributes)){
            return false;
        }

        PageAttributes pageData = (PageAttributes) obj;

        Validator idValidator = ValidatorManager.getValidator(ValidatorType.NATURAL_NUMBER_VALIDATOR);

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
