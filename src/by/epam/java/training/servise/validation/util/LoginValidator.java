package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.AuthorizationForm;
import by.epam.java.training.servise.validation.Validator;

public class LoginValidator implements Validator {

    private static final int LOGIN_MIN_LENGTH = 3;
    private static final int LOGIN_MAX_LENGTH = 20;


    private static final String REGEXP_LOGIN = "^[a-zA-Z][a-zA-Z0-9_-]+[a-zA-Z0-9]$";

    @Override
    public boolean isValid(Object obj) {
        if (String.class != obj.getClass()){
            return false;
        }

        String login = (String) obj;

        if (login.length() < LOGIN_MIN_LENGTH ||
                login.length()>LOGIN_MAX_LENGTH){
            return false;
        }
        if (!login.matches(REGEXP_LOGIN)){
            return false;
        }

        return true;
    }
}
