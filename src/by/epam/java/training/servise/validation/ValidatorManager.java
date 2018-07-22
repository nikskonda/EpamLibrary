package by.epam.java.training.servise.validation;

import by.epam.java.training.servise.validation.util.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.java.training.servise.validation.ValidatorType.*;

public class ValidatorManager {

    private static final Logger logger = Logger.getLogger(ValidatorManager.class);

    private static ValidatorManager instance = new ValidatorManager();
    private static Lock lock = new ReentrantLock();

    private final Map<ValidatorType, Validator> validators;

    private ValidatorManager() {
        this.validators = new HashMap<>();
        validators.put(SIGN_IN_FORM_VALIDATOR, new SignInFormValidator());
        validators.put(SIGN_UP_FORM_VALIDATOR, new SignUpFormValidator());

        validators.put(LOGIN_VALIDATOR, new LoginValidator());
        validators.put(LOCALE_VALIDATOR, new LocaleValidator());
        validators.put(NATURAL_NUMBER_VALIDATOR, new NaturalNumberValidator());
        validators.put(STRING_VALIDATOR, new StringValidator());
        validators.put(DOUBLE_VALIDATION, new DoubleValidator());

        validators.put(PAGES_VALIDATOR, new PagesValidator());

        validators.put(TRANSLATED_NEWS_VALIDATOR, new TranslatedNewsValidator());
        validators.put(NEWS_VALIDATOR, new NewsValidator());

        validators.put(TRANSLATED_BOOK_VALIDATOR, new TranslatedBookValidator());
        validators.put(BOOK_VALIDATOR, new BookValidator());

        validators.put(BOOKMARK_VALIDATOR, new BookmarkValidator());
        validators.put(NEW_BOOKMARK_VALIDATOR, new NewBookmarkValidator());

    }

    public static boolean isValid(ValidatorType validatorType, Object obj){
        return getInstance().validators.get(validatorType)
                .isValid(obj);
    }

    public static Validator getValidator(ValidatorType validatorType){
        return getInstance().validators.get(validatorType);
    }

    private static ValidatorManager getInstance() {
        try {
            lock.lock();
            if (instance == null){
                instance = new ValidatorManager();
            }
        } catch (UnsupportedOperationException ex) {
            logger.warn("=(", ex);
        } finally {
            lock.unlock();
        }
        return instance;
    }

}
