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

<section class="s-pageheader s-pageheader--home">

    <header class="header">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <ul class="header__social">
                        <li>
                            <a href="#0"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                        </li>
                        <li>
                            <a href="#0"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                        </li>
                        <li>
                            <a href="#0"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                        </li>
                        <li>
                            <a href="#0"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                        </li>
                    </ul>
                </div>
                <div class="col-md-6 offset-md-2">
                    <a class="header__logo" href="/news?command=open_news_list">
                        <img src="../images/logo.png" alt="Homepage">
                    </a>
                </div>
                <div class="col-md-3">
                    <div class="row lang-switch">
                        <a class="btn btn--primary" href="/en?command=en">en</a>
                        <a class="btn btn--primary" href="/ru?command=ru">рус</a>
                    </div>
                </div>
                <a class="header__toggle-menu" href="#0" title="Menu"><span>Menu</span></a>
            </div>

            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <jsp:include page="header/GuestHeader.jsp"/>
                </c:when>
                <c:when test="${sessionScope.user.role.name eq 'User'}">
                    <jsp:include page="header/UserHeader.jsp"/>
                </c:when>
                <c:when test="${sessionScope.user.role.name eq 'Moderator'}">
                    <jsp:include page="header/ModerHeader.jsp"/>
                </c:when>
                <c:when test="${sessionScope.user.role.name eq 'Administrator'}">
                    <jsp:include page="header/AdminHeader.jsp"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="header/GuestHeader.jsp"/>
                </c:otherwise>
            </c:choose>

        </div>
    </header>
</section>
