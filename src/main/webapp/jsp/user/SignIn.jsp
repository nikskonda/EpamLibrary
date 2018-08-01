<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
<fmt:message bundle="${loc}" key="local.header.sign" var="headerTitle" />
<fmt:message bundle="${loc}" key="local.button.signin.name" var="signin" />
<fmt:message bundle="${loc}" key="local.button.signup.name" var="signup" />
<fmt:message bundle="${loc}" key="local.link.goToHome" var="goToHome" />
<fmt:message bundle="${loc}" key="local.lable.login.value" var="login" />
<fmt:message bundle="${loc}" key="local.lable.password.value" var="password" />
<fmt:message bundle="${loc}" key="local.input.login.value" var="ilogin" />
<fmt:message bundle="${loc}" key="local.input.password.value" var="ipassword" />

<fmt:message bundle="${loc}" key="local.message.error.login.not_found" var="notFound" />
<fmt:message bundle="${loc}" key="local.message.error.login.content" var="loginContent" />
<fmt:message bundle="${loc}" key="local.message.error.login.length" var="loginLen" />
<fmt:message bundle="${loc}" key="local.message.error.password.length" var="pwLen" />


<html>
<head>
    <title>SignIn</title>

    <!-- CSS -->


    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>


    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="../../css/sign.css">

    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
    <body>

        <section id="signup">
            <div class="container">
                <div class="row main">
                    <div class="panel-heading">
                        <div class="panel-title text-center">
                            <h1 class="title">${headerTitle}</h1>
                            <hr/>
                        </div>
                    </div>
                    <div class="main-login main-center">
                        <form class="form-horizontal" method="post" action="/authorization" onsubmit="return isValidSignInForm()">
                            <input type="hidden" name="command" value="sign_in">
                            <div class="form-group">
                                <label for="login" class="cols-sm-2 control-label">${login}</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="login" id="login"  placeholder="${ilogin}"
                                               value="<c:out value="${requestScope.signInForm.login}"/>"/>
                                    </div>
                                </div>
                                <p class="error-input" id="loginError">
                                    <c:if test="${error_exist == true}">
                                        ${notFound}
                                    </c:if>
                                </p>
                            </div>

                            <div class="form-group">
                                <label for="password" class="cols-sm-2 control-label">${password}</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" class="form-control" name="password" id="password"  placeholder="${ipassword}"/>
                                    </div>
                                </div>
                                <p class="error-input" id="passwordError"></p>
                            </div>

                            <div class="form-group ">
                                <button type="submit" class="btn btn-primary btn-lg btn-block login-button">${signin}</button>
                            </div>
                            <div class="form-group ">
                                <a class="btn btn-primary btn-lg btn-block login-button" href="/signUp">${signup}</a>
                            </div>
                            <div class="form-group ">
                                <a href="/news?command=open_news_list">${goToHome}</a>
                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </section>

        <script type="text/javascript">
            var validationErrorMessages =
                {
                    "loginLengthError":"${loginLen}",
                    "loginContentError":"${loginContent}",
                    "passwordLengthError":"${pwLen}"
                }
            ;
        </script>
        <script src="../../js/validator.js"></script>

    </body>
</html>
