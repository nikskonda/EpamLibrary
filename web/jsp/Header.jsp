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
                <div class="col-md-2">
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
                <div class="col-md-4 offset-md-2">
                    <a class="logo" href="index.html">
                        <img src="images/logo.png" alt="Homepage">
                    </a>
                </div>
                <div class="col-md-2 offset-md-2">
                    <div class="row">
                        <form class="col-lg-1" action="/ru" method="POST">
                            <input type="hidden" name="command" value="ru">
                            <input class="btn btn--primary" type="submit" value="рус" style="padding: 0; width: 50px;">
                        </form>
                        <form class="col-lg-1" action="/en" method="POST">
                            <input type="hidden" name="command" value="en">
                            <input class="btn btn--primary" type="submit" value="en" style="padding: 0; width: 50px;">
                        </form>

                    </div>
                </div>
                <a class="header__toggle-menu" href="#0" title="Menu"><span>Menu</span></a>
            </div>
            <div class="row">
                <nav class="header__nav-wrap">

                    <h2 class="header__nav-heading h6">Site Navigation</h2>

                    <ul class="header__nav">
                        <li class="current"><a href="index.html" title="">Home</a></li>
                        <li class="has-children">
                            <a href="#0" title="">Categories</a>
                            <ul class="sub-menu">
                                <li><a href="page/category.html">Lifestyle</a></li>
                                <li><a href="page/category.html">Health</a></li>
                                <li><a href="page/category.html">Family</a></li>
                                <li><a href="page/category.html">Management</a></li>
                                <li><a href="page/category.html">Travel</a></li>
                                <li><a href="page/category.html">Work</a></li>
                            </ul>
                        </li>
                        <li class="has-children">
                            <a href="#0" title="">Blog</a>
                            <ul class="sub-menu">
                                <li><a href="page/single-video.html">Video Post</a></li>
                                <li><a href="page/single-audio.html">Audio Post</a></li>
                                <li><a href="page/single-gallery.html">Gallery Post</a></li>
                                <li><a href="page/single-standard.html">Standard Post</a></li>
                            </ul>
                        </li>
                        <li><a href="page/style-guide.html" title="">Styles</a></li>
                        <li><a href="page/about.html" title="">About</a></li>
                        <li><a href="page/contact.html" title="">Contact</a></li>
                        <li class="has-children">
                            <a href="#0" title="">JSP</a>
                            <ul class="sub-menu">
                                <li><a href="index.jsp">Index</a></li>
                                <li><a href="jsp/user/SignIn.jsp">${signin}</a></li>
                                <li><a href="jsp/user/SignUp.jsp">${signup}</a></li>
                                <li><a href="/catalog?command=open_catalog">${catalog}</a></li>
                                <li><a href="jsp/Test.jsp">Test Page</a></li>
                                <li><a href="jsp/AddNews.jsp">Add news</a></li>

                            </ul>
                        </li>
                    </ul> <!-- end header__nav -->

                    <a href="#0" title="Close Menu" class="header__overlay-close close-mobile-menu">Close</a>

                </nav> <!-- end header__nav-wrap -->
            </div>
        </div>
    </header>
</section>
