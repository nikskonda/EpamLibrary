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
<fmt:message bundle="${loc}" key="local.book.error.genres.notFound" var="genresNotFound" />

<fmt:message bundle="${loc}" key="local.book.lable.name" var="name" />
<fmt:message bundle="${loc}" key="local.book.lable.description" var="description" />
<fmt:message bundle="${loc}" key="local.book.lable.authors" var="authors" />
<fmt:message bundle="${loc}" key="local.book.lable.text" var="textFile" />
<fmt:message bundle="${loc}" key="local.book.lable.pdf" var="pdfFile" />
<fmt:message bundle="${loc}" key="local.book.lable.year" var="year" />
<fmt:message bundle="${loc}" key="local.book.lable.price" var="price" />
<fmt:message bundle="${loc}" key="local.book.lable.pages" var="pages" />
<fmt:message bundle="${loc}" key="local.book.lable.publishingHouse" var="publishingHouse" />
<fmt:message bundle="${loc}" key="local.book.lable.cover" var="coverFile" />

<fmt:message bundle="${loc}" key="local.book.placeholder.name" var="iname" />
<fmt:message bundle="${loc}" key="local.book.placeholder.description" var="idescription" />
<fmt:message bundle="${loc}" key="local.book.placeholder.authors" var="iauthors" />
<fmt:message bundle="${loc}" key="local.book.placeholder.year" var="iyear" />
<fmt:message bundle="${loc}" key="local.book.placeholder.price" var="iprice" />
<fmt:message bundle="${loc}" key="local.book.placeholder.pages" var="ipages" />
<fmt:message bundle="${loc}" key="local.book.placeholder.publishingHouse" var="ipublishingHouse" />
<fmt:message bundle="${loc}" key="local.book.lable.genres" var="listOfGenres" />

<fmt:message bundle="${loc}" key="local.book.button.addBook" var="addBook" />

<fmt:message bundle="${loc}" key="local.book.message.en" var="headerEN" />
<fmt:message bundle="${loc}" key="local.book.message.ru" var="headerRU" />



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
            <div>
                <div class="col-lg-6">
                    <div>
                        <h3>${headerEN}</h3>
                    </div>
                    <div>
                        <label for="name">${name}</label>
                        <input type="text" id="name" name="name" placeholder="${iname}" class="full-width" value="<c:out value="${requestScope.book.getName()}"/>" required>
                        <p class="error-input" id="nameError"></p>
                    </div>

                    <div>
                        <label for="description">${description}</label>
                        <textarea id="description" name="description" class="add_book_descr full-width" name="description" placeholder="${idescription}"  required><c:out value="${requestScope.book.getDescription()}"/></textarea>
                        <p class="error-input" id="descriptionError"></p>
                    </div>

                    <div>
                        <label for="authors">${authors}</label>
                        <input type="text" id="authors" name="authors"  class="full-width" placeholder="${iauthors}" value="<c:out value="${requestScope.bookRU.getName()}"/>" required>
                        <p class="error-input" id="authorsError"></p>
                    </div>

                    <div>
                        <label for="textUrl">${textFile}</label>
                        <input type="file" id="textUrl" name="textUrl" class="full-width" required>
                        <p class="error-input" id="textError"></p>
                    </div>

                    <div>
                        <label for="pdfUrl">${pdfFile}</label>
                        <input type="file" id="pdfUrl" name="pdfUrl" class="full-width" required>
                        <p class="error-input" id="pdfError"></p>
                    </div>

                </div>
                <div class="col-lg-6">
                    <input type="hidden"name="lang" value="ru" >

                    <div>
                        <h3>${headerRU}</h3>
                    </div>

                    <div>
                        <label for="nameRU">${name}</label>
                        <input type="text" id="nameRU" name="nameRU" class="full-width" placeholder="${iname}"  value="<c:out value="${requestScope.bookRU.getName()}"/>" required>
                        <p class="error-input" id="nameRuError"></p>
                    </div>

                    <div>
                        <label for="descriptionRU">${description}</label>
                        <textarea id="descriptionRU" name="descriptionRU" class="add_book_descr full-width" required placeholder="${idescription}" required><c:out value="${requestScope.bookRU.getDescription()}"/></textarea>
                        <p class="error-input" id="descriptionRuError"></p>
                    </div>

                    <div>
                        <label for="authorsRU">${authors}</label>
                        <input type="text" id="authorsRU" name="authorsRU"  class="full-width" placeholder="${iauthors}" value="<c:out value="${requestScope.bookRU.getName()}"/>" required>
                        <p class="error-input" id="authorsRuError"></p>
                    </div>

                    <div>
                        <label for="textUrlRU">${textFile}</label>
                        <input type="file" id="textUrlRU" name="textUrlRU" class="full-width" required>
                        <p class="error-input" id="textRuError"></p>
                    </div>

                    <div>
                        <label for="pdfUrlRU">${pdfFile}</label>
                        <input type="file" id="pdfUrlRU" name="pdfUrlRU" class="full-width" required>
                        <p class="error-input" id="pdfRuError"></p>
                    </div>

                </div>
            </div>

            <div style="width: 50%; margin: 0 auto;">
                <div>
                    <label for="year">${year}</label>
                    <input type="number" id="year" name="year" class="full-width" placeholder="${iyear}" value="<c:out value="${requestScope.book.publishYear}"/>" required>
                    <p class="error-input" id="yearError"></p>
                </div>

                <div>
                    <label for="price">${price}</label>
                    <input type="number" id="price" name="price" min="0" step="0.1" class="full-width" placeholder="${iprice}" value="<c:out value="${requestScope.book.getPrice()}"/>" required>
                    <p class="error-input" id="priceError"></p>
                </div>

                <div>
                    <label for="pages">${pages}</label>
                    <input type="number" id="pages" name="pages" class="full-width" placeholder="${ipages}" value="<c:out value="${requestScope.book.getPages()}"/>" required>
                    <p class="error-input" id="pagesError"></p>
                </div>

                <div>
                    <label for="publishingHouse">${publishingHouse}</label>
                    <input type="text" id="publishingHouse" name="publishingHouse" class="full-width" placeholder="${ipublishingHouse}" value="<c:out value="${requestScope.book.getPublishingHouse().getName()}"/>" required>
                    <p class="error-input" id="publishingHouseError"></p>
                </div>

                <div>
                    <label for="coverUrl">${coverFile}</label>
                    <input type="file" id="coverUrl" name="coverUrl" class="full-width" required>
                    <p class="error-input" id="coverError"></p>
                </div>

                <div>
                    <label for="genres">${listOfGenres}</label>
                    <div id="genres" class="genre-checkbox">
                        <c:choose>
                            <c:when test="${requestScope.genres.size()>0}">
                                <c:forEach var="genre" items="${requestScope.genres}">
                                        <input type="checkbox" name="genres" value="${genre.id}"><c:out value="${genre.name}"/><br>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p>${genresNotFound}</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <p class="error-input" id="genresError"></p>
                </div>

                <div>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" class="full-width" required>
                    <p class="error-input"></p>
                </div>

                <input class="add_book_button full-width" type="submit" value="${addBook}">
            </div>

        </form>
    </div>
</section>

<script type="text/javascript">
    var validationErrorMessages =
        {
            "nameLengthError":"${nameLengthError}",
            "descriptionContentError":"${descriptionContentError}",
            "authorsLengthError":"${authorsLengthError}",
            "textUrlError":"${textUrlError}",
            "pdfUrlError":"${pdfUrlError}",
            "yearError":"${yearError}",
            "priceError":"${priceError}",
            "pagesError":"${pagesError}",
            "phLengthError":"${phLengthError}",
            "coverError":"${coverError}"
        }
    ;
</script>
<script src="../../js/validator.js"></script>

<jsp:include page="../Footer.jsp"/>

</body>
</html>
