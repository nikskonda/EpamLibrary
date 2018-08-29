package by.epam.training.servise.validation;

import by.epam.training.servise.validation.util.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ValidatorManager {

    private static final Logger logger = Logger.getLogger(ValidatorManager.class);

    /**
     * ValidatorManager instance.
     */
    private static ValidatorManager instance = new ValidatorManager();
    /**
     * {@link Lock} field.
     */
    private static Lock lock = new ReentrantLock();

    /**
     * {@link Map} field.
     */
    private final Map<ValidatorType, Validator> validators;

    private ValidatorManager() {
        this.validators = new HashMap<>();
        validators.put(ValidatorType.SIGN_IN_FORM_VALIDATOR, new SignInFormValidator());
        validators.put(ValidatorType.SIGN_UP_FORM_VALIDATOR, new SignUpFormValidator());

        validators.put(ValidatorType.LOGIN_VALIDATOR, new LoginValidator());
        validators.put(ValidatorType.LOCALE_VALIDATOR, new LocaleValidator());
        validators.put(ValidatorType.NATURAL_NUMBER_VALIDATOR, new NaturalNumberValidator());
        validators.put(ValidatorType.STRING_VALIDATOR, new StringValidator());
        validators.put(ValidatorType.DOUBLE_VALIDATION, new DoubleValidator());

        validators.put(ValidatorType.PAGES_VALIDATOR, new PagesValidator());

        validators.put(ValidatorType.TRANSLATED_NEWS_VALIDATOR, new TranslatedNewsValidator());
        validators.put(ValidatorType.NEWS_VALIDATOR, new NewsValidator());

        validators.put(ValidatorType.TRANSLATED_BOOK_VALIDATOR, new TranslatedBookValidator());
        validators.put(ValidatorType.BOOK_VALIDATOR, new BookValidator());

        validators.put(ValidatorType.BOOKMARK_VALIDATOR, new BookmarkValidator());
        validators.put(ValidatorType.NEW_BOOKMARK_VALIDATOR, new NewBookmarkValidator());

    }

    /**
     * Returns <tt>true<tt/> if the object is valid.
     *
     * @param validatorType - Validation type.
     * @param obj - Object to check
     *
     * @return <tt>true<tt/> if the object is valid.
     */
    public static boolean isValid(ValidatorType validatorType, Object obj){
        return getInstance().validators.get(validatorType)
                .isValid(obj);
    }

    /**
     * Returns the validator of the type.
     *
     * @param validatorType - Validation type.
     *
     * @return the validator of the type.
     */
    public static Validator getValidator(ValidatorType validatorType){
        return getInstance().validators.get(validatorType);
    }

    /**
     * Return ValidatorManager instance.
     *
     * @return ValidatorManager instance.
     */
    private static ValidatorManager getInstance() {
        try {
            lock.lock();
            if (instance == null){
                instance = new ValidatorManager();
            }
        } catch (UnsupportedOperationException ex) {
            logger.warn("Error return instance", ex);
        } finally {
            lock.unlock();
        }
        return instance;
    }

}
