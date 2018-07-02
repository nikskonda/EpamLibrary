package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.user.SignInForm;
import by.epam.java.training.servise.validation.Validator;

public class AuthorizationFormValidator implements Validator {

    private static final int PASSWORD_LENGTH = 32;

    @Override
    public boolean isValid(Object obj) {
        if (!(obj instanceof SignInForm)){
            return false;
        }

        SignInForm authForm = (SignInForm) obj;

        if (!new LoginValidator().isValid(authForm.getLogin())){
            return false;
        }
        if (authForm.getPassword().length() != PASSWORD_LENGTH){
            return false;
        }

        return true;
    }
}
