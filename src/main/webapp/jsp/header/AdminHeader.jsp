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


<div class="row">
    <nav class="header__nav-wrap">

        <h2 class="header__nav-heading h6">Site Navigation</h2>

        <ul class="header__nav">
            <li class="current"><a href="/news?command=open_news_list" title="">Home</a></li>
            <li><a href="/catalog?command=open_book_catalog">${catalog}</a></li>
            <li><a href="/profile?command=open_profile">Profile</a></li>
            <li><a href="/profile?command=take_list_of_bookmarks">Bookmarks</a></li>

            <li class="has-children">
                <a href="#0" title="">Moder</a>
                <ul class="sub-menu">
                    <li><a href="/news?command=open_add_news">Add news</a></li>
                    <li><a href="/book?command=open_add_book">Add book</a></li>
                </ul>
            </li>
            <li class="has-children">
                <a href="#0" title="">Admin</a>
                <ul class="sub-menu">
                    <li><a href="/admin?command=show_user_list">User List</a></li>
                    <li><a href="jsp/Test.jsp">Test Page</a></li>
                </ul>
            </li>
            <li><a href="/signOut?command=sign_out">Sign Out</a></li>
        </ul> <!-- end header__nav -->

        <a href="#0" title="Close Menu" class="header__overlay-close close-mobile-menu">Close</a>
    </nav> <!-- end header__nav-wrap -->
</div>

