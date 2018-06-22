package by.epam.java.training.servise.validation;

import by.epam.java.training.servise.validation.util.AuthorizationFormValidator;
import by.epam.java.training.servise.validation.util.LocaleValidator;
import by.epam.java.training.servise.validation.util.LoginValidator;
import by.epam.java.training.servise.validation.util.RegistrationFormValidator;
import java.util.HashMap;
import java.util.Map;
import static by.epam.java.training.servise.validation.ValidatorType.*;

public class ValidatorManager {

    private final Map<ValidatorType, Validator> validators;

    public ValidatorManager() {
        this.validators = new HashMap<>();
        validators.put(AUTHORIZATION_FORM_VALIDATOR, new AuthorizationFormValidator());
        validators.put(REGISTRATION_FORM_VALIDATOR, new RegistrationFormValidator());
        validators.put(LOGIN_VALIDATOR, new LoginValidator());
        validators.put(LOCALE_VALIDATOR, new LocaleValidator());
    }

    public boolean isValid(ValidatorType validatorType, Object obj){
        return this.validators.get(validatorType).isValid(obj);
    }

}
