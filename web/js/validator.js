function isValidSignUpForm() {
    var result = true;

    var loginField = document.getElementById("login");
    var passwordField = document.getElementById("password");
    var confirmPasswordField = document.getElementById("confirmPassword");
    var firstNameField = document.getElementById("firstName");
    var lastNameField = document.getElementById("lastName");
    var emailField = document.getElementById("email");


    var loginMinLength = 3, loginMaxLength = 20;
    var passwordMinLength = 6, passwordMaxLength = 20;
    var firstNameMinLength = 3, firstNameMaxLength = 20;
    var lastNameMinLength = 3, lastNameMaxLength = 20;

    var REGEXP_LOGIN = "^[a-zA-Z][a-zA-Z0-9_-]+[a-zA-Z0-9]$";
    var REGEXP_NAME = "^[a-zA-Z][A-Za-z\\s]+[A-Za-z]$";
    var REGEXP_EMAIL = "^[\\w]+@[a-zA-Z]+\\.[a-z]+$";
    var borderColorRed = "#F54D4D";

    if(loginField.value.length >= loginMinLength && loginField.value.length <= loginMaxLength){
        if (loginField.value.search(REGEXP_LOGIN) === -1){
            result=false;
            loginField.borderColor = borderColorRed;
            loginError.innerText = validationErrorMessages.loginContentError;
        }
    } else {
        result = false;
        loginField.borderColor = borderColorRed;
        loginError.innerText = validationErrorMessages.loginLengthError;
    }

    if(passwordField.value.length < passwordMinLength || passwordField.value.length > passwordMaxLength) {
        result = false;
        passwordField.borderColor = borderColorRed;
        passwordError.innerText = validationErrorMessages.passwordLengthError;
    }

    if (passwordField.value !== confirmPasswordField.value){
        result = false;
        confirmPasswordField.borderColor = borderColorRed;
        confirmPasswordError.innerText = validationErrorMessages.confirmPasswordError;
    }

    if(firstNameField.value.length >= firstNameMinLength && firstNameField.value.length <= firstNameMaxLength){
        if (firstNameField.value.search(REGEXP_NAME) === -1){
            result=false;
            firstNameField.borderColor = borderColorRed;
            firstNameError.innerText = validationErrorMessages.firstNameContentError;
        }
    } else {
        result = false;
        firstNameField.borderColor = borderColorRed;
        firstNameError.innerText = validationErrorMessages.firstNameLengthError;
    }

    if(lastNameField.value.length >= lastNameMinLength && lastNameField.value.length <= lastNameMaxLength){
        if (lastNameField.value.search(REGEXP_NAME) === -1){
            result=false;
            lastNameField.borderColor = borderColorRed;
            lastNameError.innerText = validationErrorMessages.lastNameContentError;
        }
    } else {
        result = false;
        lastNameField.borderColor = borderColorRed;
        lastNameError.innerText = validationErrorMessages.lastNameLengthError;
    }

    if (emailField.value.search(REGEXP_EMAIL) === -1){
        result = false;
        emailField.borderColor = borderColorRed;
        emailError.innerText = validationErrorMessages.emailError;
    }

    return result;
}

function isValidSignInForm() {
    var result = true;

    var loginField = document.getElementById("login");
    var passwordField = document.getElementById("password");

    var loginError = document.getElementById("loginError");
    var passwordError = document.getElementById("passwordError");

    var loginMinLength = 3, loginMaxLength = 20;
    var passwordMinLength = 6, passwordMaxLength = 20;

    var REGEXP_LOGIN = "^[a-zA-Z][a-zA-Z0-9_-]+[a-zA-Z0-9]$";
    var borderColorRed = "#F54D4D";

    if(loginField.value.length >= loginMinLength && loginField.value.length <= loginMaxLength){
        if (loginField.value.search(REGEXP_LOGIN) === -1){
            result=false;
            loginField.borderColor = borderColorRed;
            loginError.innerText = validationErrorMessages.loginContentError;
        }
    } else {
        result = false;
        loginField.borderColor = borderColorRed;
        loginError.innerText = validationErrorMessages.loginLengthError;
    }

    if(passwordField.value.length < passwordMinLength || passwordField.value.length > passwordMaxLength) {
        result = false;
        passwordField.borderColor = borderColorRed;
        passwordError.innerText = validationErrorMessages.passwordLengthError;
    }

    return result;
}

function isValidProfileForm() {
    var result = true;

    var oldPasswordField = document.getElementById("oldPassword");
    var newPasswordField = document.getElementById("newPassword");
    var confirmPasswordField = document.getElementById("confirmPassword");
    var firstNameField = document.getElementById("firstName");
    var lastNameField = document.getElementById("lastName");
    var emailField = document.getElementById("email");

    var passwordMinLength = 6, passwordMaxLength = 20;
    var firstNameMinLength = 3, firstNameMaxLength = 20;
    var lastNameMinLength = 3, lastNameMaxLength = 20;

    var REGEXP_NAME = "^[a-zA-Z][A-Za-z\\s]+[A-Za-z]$";
    var REGEXP_EMAIL = "^[\\w]+@[a-zA-Z]+\\.[a-z]+$";
    var borderColorRed = "#F54D4D";

    if(oldPasswordField.value.length < passwordMinLength || oldPasswordField.value.length > passwordMaxLength) {
        result = false;
        oldPasswordField.borderColor = borderColorRed;
        oldPasswordError.innerText = validationErrorMessages.passwordLengthError;
    }

    if (!(isEmpty(newPasswordField.value) && isEmpty(confirmPasswordField.value))) {

        if (newPasswordField.value.length < passwordMinLength || newPasswordField.value.length > passwordMaxLength) {
            result = false;
            newPasswordField.borderColor = borderColorRed;
            newPasswordError.innerText = validationErrorMessages.passwordLengthError;
        }

        if (newPasswordField.value !== confirmPasswordField.value) {
            result = false;
            confirmPasswordField.borderColor = borderColorRed;
            confirmPasswordError.innerText = validationErrorMessages.confirmPasswordError;
        }
    }

    if(firstNameField.value.length >= firstNameMinLength && firstNameField.value.length <= firstNameMaxLength){
        if (firstNameField.value.search(REGEXP_NAME) === -1){
            result=false;
            firstNameField.borderColor = borderColorRed;
            firstNameError.innerText = validationErrorMessages.firstNameContentError;
        }
    } else {
        result = false;
        firstNameField.borderColor = borderColorRed;
        firstNameError.innerText = validationErrorMessages.firstNameLengthError;
    }

    if(lastNameField.value.length >= lastNameMinLength && lastNameField.value.length <= lastNameMaxLength){
        if (lastNameField.value.search(REGEXP_NAME) === -1){
            result=false;
            lastNameField.borderColor = borderColorRed;
            lastNameError.innerText = validationErrorMessages.lastNameContentError;
        }
    } else {
        result = false;
        lastNameField.borderColor = borderColorRed;
        lastNameError.innerText = validationErrorMessages.lastNameLengthError;
    }

    if (emailField.value.search(REGEXP_EMAIL) === -1){
        result = false;
        emailField.borderColor = borderColorRed;
        emailError.innerText = validationErrorMessages.emailError;
    }

    return result;
}

function isEmpty(str) {
    if (str != null && typeof str !== "undefined") {
        str = str.trim();
    }
    if (!str) {
        return true;
    }
    return false;
}