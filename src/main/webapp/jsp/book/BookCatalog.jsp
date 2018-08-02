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
<%@ taglib uri="/WEB-INF/tld/Pagination.tld" prefix="pag" %>
<%@ taglib uri="/WEB-INF/tld/ItemsPerPage.tld" prefix="ipp" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message.catalog.title" var="title" />
<fmt:message bundle="${loc}" key="local.message.catalog.header" var="headerP" />
<fmt:message bundle="${loc}" key="local.button.find.name" var="find" />
<fmt:message bundle="${loc}" key="local.button.more.name" var="more" />
<fmt:message bundle="${loc}" key="local.book.error.notFound" var="bookNotFound" />
<fmt:message bundle="${loc}" key="local.search.error.length" var="searchError" />

<html>
<head>
    <title>Catalog</title>

    <!-- CSS
    ================================================== -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/base.css">
    <link rel="stylesheet" href="../../css/vendor.css">
    <link rel="stylesheet" href="../../css/main.css">

    <!-- script
    ================================================== -->
    <script src="../../js/modernizr.js"></script>
    <script src="../../js/pace.min.js"></script>

    <!-- favicons
    ================================================== -->
    <link rel="shortcut icon" href="../../favicon.ico" type="image/x-icon">
    <link rel="icon" href="../../favicon.ico" type="image/x-icon">

</head>
    <body>

    <jsp:include page="../Header.jsp"/>

    <section class="s-content">

        <div class="row">
            <div class="col-full s-content__header">
                <h1>${title}</h1>
            </div>
        </div>

        <div class="row narrow">
            <form method="post" action="/book" onsubmit="return isValidSearchForm()">
                <input type="hidden" name="command" value="find_book" >
                <input type="text" id="search" name="search" value="<c:out value="${requestScope.search}"/>" required>
                <p class="error-input" id="searchError"></p>
                <button type="submit">${find}</button>
            </form>
        </div>

        <div class="masonry-wrap row">
            <div class="masonry">

                <div class="grid-sizer"></div>
                <c:choose>
                    <c:when test="${requestScope.books.size()>0}">
                        <c:forEach var="book" items="${requestScope.books}">
                            <article class="masonry__brick entry format-standard">
                                    <img class="entry__thumb" src="../<c:out value="${book.getCoverUrl()}"/>" alt="">
                                    <div class="entry__text">
                                        <div class="entry__header">
                                            <h1 class="entry__title"><c:out value="${book.getName()}"/></h1>
                                        </div>
                                        <div class="entry__date">
                                            <p><c:out value="${book.getPublishYear()}"/></p>
                                        </div>
                                        <div class="entry__meta">
                                            <form action="/book">
                                                <input type="hidden" name="command" value="take_book">
                                                <input type="hidden" name="book_id" value="<c:out value="${book.id}"/>">
                                                <input class="btn btn--stroke full-width" type="submit" value="${more}">
                                            </form>
                                        </div>
                                    </div>
                            </article>

                        </c:forEach>
                    </c:when>

                    <c:otherwise>
                        <p>${bookNotFound}</p>
                    </c:otherwise>
                </c:choose>

            </div> <!-- end masonry -->
        </div> <!-- end masonry-wrap -->

        <c:choose>
            <c:when test="${not empty requestScope.search}">
                <pag:pagination url="book" command="find_book&search=${requestScope.search}&" currentPage="${requestScope.numberOfPage}" totalPages="${requestScope.totalPages}"/>
                <ipp:itemPerPage url="book" command="find_book&search=${requestScope.search}&" items="countBooks" currentCount="${sessionScope.countBooks}"/>
            </c:when>
            <c:otherwise>
                <pag:pagination url="book" command="take_book_catalog" currentPage="${requestScope.numberOfPage}" totalPages="${requestScope.totalPages}"/>
                <ipp:itemPerPage url="book" command="take_book_catalog" items="countBooks" currentCount="${sessionScope.countBooks}"/>
            </c:otherwise>
        </c:choose>
    </section> <!-- s-content -->
    <jsp:include page="../Footer.jsp"/>


    <script type="text/javascript">
        var validationErrorMessages =
            {
                "searchLengthError":"${searchError}"
            }
        ;
    </script>
    <script src="../../js/validator.js"></script>
    <script src="../../js/jquery-3.2.1.min.js"></script>
    <script src="../../js/plugins.js"></script>
    <script src="../../js/main.js"></script>
    </body>
</html>
