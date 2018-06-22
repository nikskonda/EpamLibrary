package by.epam.java.training.servise.validation.util;

import by.epam.java.training.servise.validation.Validator;

public class LocaleValidator implements Validator {

    private static final int LOCALE_LENGTH = 2;

    private static final String RU_LOCALE = "ru";
    private static final String EN_LOCALE = "en";


    @Override
    public boolean isValid(Object obj) {
        if (String.class != obj.getClass()){
            return false;
        }
        String locale = (String) obj;

        if (locale.length() != LOCALE_LENGTH){
            return false;
        }

        if (!(locale.equals(RU_LOCALE) || locale.equals(EN_LOCALE))){
            return false;
        }

        return true;
    }
}
