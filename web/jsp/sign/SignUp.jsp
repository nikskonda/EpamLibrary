<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.header.sign" var="headerTitle" />
    <fmt:message bundle="${loc}" key="local.button.signup.name" var="signup" />

    <fmt:message bundle="${loc}" key="local.lable.login.value" var="login" />
    <fmt:message bundle="${loc}" key="local.lable.password.value" var="password" />
    <fmt:message bundle="${loc}" key="local.lable.confirmPassword.value" var="confirmPassword" />
    <fmt:message bundle="${loc}" key="local.lable.firstName.value" var="firstName" />
    <fmt:message bundle="${loc}" key="local.lable.lastName.value" var="lastName" />
    <fmt:message bundle="${loc}" key="local.lable.email.value" var="email" />

    <fmt:message bundle="${loc}" key="local.input.login.value" var="ilogin" />
    <fmt:message bundle="${loc}" key="local.input.password.value" var="ipassword" />
    <fmt:message bundle="${loc}" key="local.input.confirmPassword.value" var="iconfirmPassword" />
    <fmt:message bundle="${loc}" key="local.input.firstName.value" var="ifirstName" />
    <fmt:message bundle="${loc}" key="local.input.lastName.value" var="ilastName" />
    <fmt:message bundle="${loc}" key="local.input.email.value" var="iemail" />
<html>
<html>
<head>
    <title>Sign Up</title>

    <!-- CSS -->
    <link rel="stylesheet" href="css/sign.css">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>


    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
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
                        <form class="form-horizontal" method="post" action="/sign_up" onsubmit="false">
                            <input type="hidden" name="command" value="sign_up">
                            <div class="form-group">
                                <label for="login" class="cols-sm-2 control-label">${login}</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="login" id="login"  placeholder="${ilogin}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="password" class="cols-sm-2 control-label">${login}</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" class="form-control" name="password" id="password"  placeholder="${ipassword}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="confirm" class="cols-sm-2 control-label">${confirmPassword}</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" class="form-control" name="confirmPassword" id="confirm"  placeholder="${iconfirmPassword}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="firstName" class="cols-sm-2 control-label">${firstName}</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="firstName" id="firstName"  placeholder="${ifirstName}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="lastName" class="cols-sm-2 control-label">${lastName}</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="lastName" id="lastName"  placeholder="${ilastName}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="email" class="cols-sm-2 control-label">${email}</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                        <input type="text" class="form-control" name="email" id="email"  placeholder="${iemail}"/>
                                    </div>
                                </div>
                            </div>




                            <div class="form-group ">
                                <button type="submit" class="btn btn-primary btn-lg btn-block login-button">${signup}</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>


    </body>
</html>
