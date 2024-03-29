<%--
  Created by IntelliJ IDEA.
  User: perdh
  Date: 11/19/2023
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>${title}</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/dashboard/">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="<c:url value= '/assets/dist/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value= '/css/dashboard.css' />" rel="stylesheet">
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
<body>
    <jsp:include page="layout/header.jsp" />
    <div class="container-fluid">
        <div class="row">
            <jsp:include page="layout/side-bar.jsp" />
            <jsp:include page="${page}" />
        </div>
    </div>
    <div>
        <script>
            function formatCurrency(number) {
                return extractNumberFromString(String(number)).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
            }
            let prices = document.querySelectorAll('.price');
            prices.forEach(item=>{
                let price = Number(item.innerText);
                item.innerText = formatCurrency(price);
            })
            function extractNumberFromString(inputString) {
                // Sử dụng biểu thức chính quy để loại bỏ dấu chấm và kí tự "đ"
                const cleanedString = inputString.replace(/[.,đ]/g, '');

                // Chuyển chuỗi thành số
                const number = parseFloat(cleanedString);

                return number;
            }

        </script>
        <script src="<c:url value= '/assets/dist/js/bootstrap.bundle.min.js' />"></script>
        <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"></script>
    </div>
</body>
</html>
