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

<fmt:message bundle="${loc}" key="local.book.lable.authors" var="authors" />
<fmt:message bundle="${loc}" key="local.book.lable.year" var="year" />
<fmt:message bundle="${loc}" key="local.book.lable.price" var="price" />
<fmt:message bundle="${loc}" key="local.book.lable.pages" var="pages" />
<fmt:message bundle="${loc}" key="local.book.lable.publishingHouse" var="publishingHouse" />
<fmt:message bundle="${loc}" key="local.book.lable.genre" var="genres" />
<fmt:message bundle="${loc}" key="local.book.lable.pdfFile" var="pdfFile" />
<fmt:message bundle="${loc}" key="local.book.message.info" var="info" />

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
        <div class="container">
            <form method="post" action="/moderator">
                <input type="hidden" name="command" value="open_editing_book">
                <input type="hidden" name="book_id" value="<c:out value="${requestScope.book.id}"/>">
                <button type="submit">Edit</button>
            </form>

            <div class="row">
                <div class="col-lg-6">
                    <img src="<c:out value="${requestScope.book.coverUrl}"/>" style="float: left">
                </div>

                <div class="col-lg-6">
                    <h1><c:out value="${requestScope.book.name}"/></h1>
                    <h5>${price}: <c:out value="${requestScope.book.price}"/>$</h5>
                    <c:forEach var="paragraph" items="${requestScope.description}">
                        <p><c:out value="${paragraph}"/></p>
                    </c:forEach>
                </div>
            </div>



            <div class="row">
                <div class="row">
                    <h1>${info}:</h1>
                </div>
                <div class="row">
                    <table>
                        <tbody>
                        <tr>
                            <td>${year}</td>
                            <td><c:out value="${requestScope.book.publishYear}"/></td>
                        </tr>

                        <tr>
                            <td>${publishingHouse}</td>
                            <td><c:out value="${requestScope.book.publishingHouse.name}"/></td>
                        </tr>


                        <tr>
                            <td>${authors}</td>
                            <td><c:out value="${requestScope.book.getAuthors()}"/></td>
                        </tr>

                        <tr>
                            <td>${genres}</td>
                            <td><c:out value="${requestScope.book.getGenresAsString()}"/></td>
                        </tr>
                        <tr>
                            <td>${pages}</td>
                            <td><c:out value="${requestScope.book.pages}"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><a href="<c:out value="${requestScope.book.pdfFileUrl}"/>" target="_blank">${pdfFile}</a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="book_sets">
                <form method="post" action="/book">
                    <input type="hidden" name="command" value="read_book">
                    <input type="hidden" name="book_id" value="<c:out value="${requestScope.book.id}"/>">
                    <button type="submit">Read</button>
                </form>
                <form method="post" action="/book">
                    <input type="hidden" name="command" value="open_bookmark">
                    <input type="hidden" name="book_id" value="<c:out value="${requestScope.book.id}"/>">
                    <button type="submit">Go to Bookmark</button>
                </form>
            </div>

        </div>

    </section> <!-- s-content -->
    <jsp:include page="../Footer.jsp"/>
    </body>
</html>
