function isValidRegistrationForm() {
    var loginField = document.getElementById("login");
    var passwordField = document.getElementById("password");
    var comfirmPasswordField = document.getElementById("comfirmPassword");
    var firstNameField = document.getElementById("firstName");
    var lastNameField = document.getElementById("lastName");
    var emailField = document.getElementById("email");


    var REGEXP_LOGIN = "[a-zA-Z][a-zA-Z0-9_-]+[a-zA-Z0-9]";

    var loginMinLength = 3, loginMaxLength = 20;

    var borderColorRed = "#F54D4D";

    if(loginField.value.length >= loginMinLength && loginField.value.length <= loginMaxLength){

    } else {
        loginField.borderColor = borderColorRed;

    }
}

function isValidLoginForm() {
    var result = "true";

    var loginField = document.getElementById("login");
    var passwordField = document.getElementById("password");

    var loginError = document.getElementById("loginError");
    var passwordError = document.getElementById("passwordError");

    var loginMinLength = 3, loginMaxLength = 20;
    var passwordMinLength = 6, passwordMaxLength = 20;

    var REGEXP_LOGIN = "[a-zA-Z][a-zA-Z0-9_-]+[a-zA-Z0-9]";
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