package by.epam.java.training.servise.validation.util;

import by.epam.java.training.servise.validation.Validator;
import static by.epam.java.training.web.command.util.FieldNameConstants.*;

public class LocaleValidator implements Validator {

    private static final int LOCALE_LENGTH = 2;

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

        if (!(locale.equals(RUSSIAN) || locale.equals(ENGLISH))){
            return false;
        }

        return true;
    }
}
