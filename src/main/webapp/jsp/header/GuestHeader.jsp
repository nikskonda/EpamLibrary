<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 19-Jun-18
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
<fmt:message bundle="${loc}" key="local.button.signin.name" var="signin" />
<fmt:message bundle="${loc}" key="local.button.signup.name" var="signup" />
<fmt:message bundle="${loc}" key="local.button.catalog.name" var="catalog" />
<fmt:message bundle="${loc}" key="local.button.home.name" var="home" />

<div class="row">
    <nav class="header__nav-wrap">

        <h2 class="header__nav-heading h6">Site Navigation</h2>

        <ul class="header__nav">
                <li class="current"><a href="/news?command=take_list_of_news" title="">${home}</a></li>
                <li><a href="/catalog?command=take_book_catalog">${catalog}</a></li>

                <li><a href="/signIn">${signin}</a></li>
                <li><a href="/signUp">${signup}</a></li>

        </ul> <!-- end header__nav -->

        <a href="#0" title="Close Menu" class="header__overlay-close close-mobile-menu">Close</a>
    </nav> <!-- end header__nav-wrap -->
</div>