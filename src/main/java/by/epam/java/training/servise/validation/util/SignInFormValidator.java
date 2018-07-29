package by.epam.java.training.servise.validation.util;

import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.validation.Validator;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

/**
 * The class for checking sign in form for validity.
 *
 * @author  Nikita Shkonda
 *
 */
public class SignInFormValidator implements Validator {

    private static final int PASSWORD_LENGTH = 32;

    /**
     * The method returns <tt>true<tt/> if the sign in form is valid.
     *
     * @author  Nikita Shkonda
     *
     */
    @Override
    public boolean isValid(Object obj) {
        if (obj == null){
            return false;
        }
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
