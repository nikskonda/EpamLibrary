package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

public class SignInFormValidator implements Validator {

    private static final int PASSWORD_LENGTH = 32;

    @Override
    public boolean isValid(Object obj) {
        if (!(obj instanceof SignInForm)){
            return false;
        }

        SignInForm authForm = (SignInForm) obj;

        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, authForm.getLogin())){
            return false;
        }
        if (authForm.getPassword().length() != PASSWORD_LENGTH){
            return false;
        }

        return true;
    }
}
