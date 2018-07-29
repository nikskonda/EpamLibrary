package by.epam.training.servise.validation.util;

import by.epam.training.servise.validation.Validator;
import by.epam.training.web.command.util.FieldNameConstant;

/**
 * The class for checking locale for validity.
 *
 * @author  Nikita Shkonda
 */
public class LocaleValidator implements Validator {

    private static final int LOCALE_LENGTH = 2;

    /**
     * The method returns <tt>true<tt/> if the locale is valid.
     *
     * @author  Nikita Shkonda
     *
     */
    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
        if (String.class != obj.getClass()){
            return false;
        }
        String locale = (String) obj;

        if (locale.length() != LOCALE_LENGTH){
            return false;
        }

        if (!(locale.equals(FieldNameConstant.RUSSIAN) || locale.equals(FieldNameConstant.ENGLISH))){
            return false;
        }

        return true;
    }
}
