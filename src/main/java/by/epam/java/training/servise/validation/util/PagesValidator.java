package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

/**
 * The class for checking page for validity.
 *
 * @author  Nikita Shkonda
 */
public class PagesValidator implements Validator {

    /**
     * The method returns <tt>true<tt/> if the page is valid.
     *
     * @author  Nikita Shkonda
     */
    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (!(obj instanceof PageAttribute)){
            return false;
        }

        PageAttribute pageData = (PageAttribute) obj;

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
