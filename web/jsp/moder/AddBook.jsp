<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message.error.book.name.length" var="nameLengthError" />
<fmt:message bundle="${loc}" key="local.message.error.book.description.length" var="descriptionContentError" />
<fmt:message bundle="${loc}" key="local.message.error.book.authors.length" var="authorsLengthError" />
<fmt:message bundle="${loc}" key="local.message.error.book.text.content" var="textUrlError" />
<fmt:message bundle="${loc}" key="local.message.error.book.pdf.content" var="pdfUrlError" />
<fmt:message bundle="${loc}" key="local.message.error.book.year.content" var="yearError" />
<fmt:message bundle="${loc}" key="local.message.error.book.price.content" var="priceError" />
<fmt:message bundle="${loc}" key="local.message.error.book.pages.content" var="pagesError" />
<fmt:message bundle="${loc}" key="local.message.error.book.publishingHouse.length" var="phLengthError" />
<fmt:message bundle="${loc}" key="local.message.error.book.cover.content" var="coverError" />

<html>
<head>
    <title>Error</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

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
        <form action="/moderator" method="POST" enctype="multipart/form-data" onsubmit="return isValidBookForm()">
            <input type="hidden" name="command" value="add_book" >

            <div class="col-lg-6">
                <input type="text" id="name" name="name" style="width: 100%" value="<c:out value="${requestScope.book.getName()}"/>" required>
                <p class="error-input" id="nameError"></p>
                <textarea id="description" name="description" style="width: 100%" required><c:out value="${requestScope.book.getDescription()}"/></textarea>
                <p class="error-input" id="descriptionError"></p>
                <input type="text" id="authors" name="authors" style="width: 100%" value="<c:out value="${requestScope.bookRU.getName()}"/>" required>
                <p class="error-input" id="authorsError"></p>
                txt
                <input type="file" id="textUrl" name="textUrl" required>
                <p class="error-input" id="textError"></p>
                pdf
                <input type="file" id="pdfUrl" name="pdfUrl" required>
                <p class="error-input" id="pdfError"></p>
            </div>
            <div class="col-lg-6">
                <input type="hidden"name="lang" value="ru" >
                <input type="text" id="nameRU" name="nameRU" style="width: 100%" value="<c:out value="${requestScope.bookRU.getName()}"/>" required>
                <p class="error-input" id="nameRuError"></p>
                <textarea id="descriptionRU" name="descriptionRU" style="width: 100%" required placeholder="Enter here"><c:out value="${requestScope.bookRU.getDescription()}"/></textarea>
                <p class="error-input" id="descriptionRuError"></p>
                <input type="text" id="authorsRU" name="authorsRU" style="width: 100%" value="<c:out value="${requestScope.bookRU.getName()}"/>" required>
                <p class="error-input" id="authorsRuError"></p>
                text ru
                <input type="file" id="textUrlRU" name="textUrlRU" required>
                <p class="error-input" id="textRuError"></p>
                pdf ru
                <input type="file" id="pdfUrlRU" name="pdfUrlRU" required>
                <p class="error-input" id="pdfRuError"></p>
            </div>

            <input type="number" id="year" name="year" value="<c:out value="${requestScope.book.publishYear}"/>" required>
            <p class="error-input" id="yearError"></p>
            <input type="number" id="price" name="price" min="0" step="0.1" value="<c:out value="${requestScope.book.getPrice()}"/>" required>
            <p class="error-input" id="priceError"></p>

            <input type="number" id="pages" name="pages" value="<c:out value="${requestScope.book.getPages()}"/>" required>
            <p class="error-input" id="pagesError"></p>
            <input type="text" id="publishingHouse" name="publishingHouse" value="<c:out value="${requestScope.book.getPublishingHouse().getName()}"/>" required>
            <p class="error-input" id="publishingHouseError"></p>
            cover
            <input type="file" id="coverUrl" name="coverUrl" required>
            <p class="error-input" id="coverError"></p>

            List of Genres:
            <c:choose>
                <c:when test="${requestScope.genres.size()>0}">
                    <c:forEach var="genre" items="${requestScope.genres}">
                        <input type="checkbox" name="genres" value="${genre.id}"><c:out value="${genre.name}"/>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>genre нет, сорян</p>
                </c:otherwise>
            </c:choose>
            <p class="error-input" id="genresError"></p>

            <input type="submit" value="Add book">
        </form>
    </div>
</section>

<script type="text/javascript">
    var validationErrorMessages =
        {
            "nameLengthError":${nameLengthError},
            "descriptionContentError":${descriptionContentError},
            "authorsLengthError":${authorsLengthError},
            "textUrlError":${textUrlError},
            "pdfUrlError":${pdfUrlError},
            "yearError":${yearError},
            "priceError":${priceError},
            "pagesError":${pagesError},
            "phLengthError":${phLengthError},
            "coverError":${coverError}
        }
    ;
</script>
<script src="../../js/validator.js"></script>

<jsp:include page="../Footer.jsp"/>

</body>
</html>
