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
<section class="s-content">
    <div class="row">
        <form action="/moderator" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="command" value="add_book" >

            <div class="col-lg-6">
                <input type="text" name="name" style="width: 100%">
                <textarea name="description" style="width: 100%">Enter description here...</textarea>
                text
                <input type="file" name="textUrl">
                pdf
                <input type="file" name="pdfUrl">
            </div>
            <div class="col-lg-6">
                <input type="hidden" name="lang" value="ru" >
                <input type="text" name="nameRU" style="width: 100%">
                <textarea name="descriptionRU" style="width: 100%">Enter description here...</textarea>
                text ru
                <input type="file" name="textUrlRU">
                pdf ru
                <input type="file" name="pdfUrlRU">
            </div>

            <input type="number" name="year">
            <input type="number" name="price" min="0" step="0.25">
            <input type="number" name="pages">
            <input type="text" name="publishingHouse">
            cover
            <input type="file" name="coverUrl">

            <input type="submit" value="Add book">
        </form>
    </div>
</section>
</body>
</html>
