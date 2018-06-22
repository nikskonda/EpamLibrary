package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.user.SignInForm;
import by.epam.java.training.servise.validation.Validator;

public class AuthorizationFormValidator implements Validator {

    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 20;

    @Override
    public boolean isValid(Object obj) {
        if (SignInForm.class != obj.getClass()){
            return false;
        }

        SignInForm authForm = (SignInForm) obj;

        if (!new LoginValidator().isValid(authForm.getLogin())){
            return false;
        }
        if (authForm.getPassword().length() < PASSWORD_MIN_LENGTH ||
                authForm.getPassword().length()>PASSWORD_MAX_LENGTH){
            return false;
        }

        return true;
    }
}
