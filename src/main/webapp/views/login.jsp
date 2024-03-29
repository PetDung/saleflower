<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

</head>
<body>
<div  class="d-flex justify-content-center align-items-center" style="background-color: blue; width: 100%; height: 100vh;">
    <form id="login-form" action="/login/login-handler" method="post" style="min-width: 500px">
        <div class="wapper p-5 bg-light" style="max-width: 500px; border-radius: 15px; min-width: 300px">
            <div class="logo text-center" >
                <h2> Login</h2>
            </div>
            <div class="wr h-25">
                <span  id="mess" class="mess text-danger fw-bold" style="font-size: 12px;">${mess}</span>
            </div>
            <div class="input d-flex mt-3" style="border: 1px solid #ccc; border-radius: 10px;">
                    <span class="d-flex justify-content-center align-items-center"
                          style="background-color: #ccc;
                        padding-left: 18px;
                        padding-right: 18px;
                        border-bottom-left-radius: 8px;
                        border-top-left-radius: 9px;
                      ">
                            <i class="fa-solid fa-envelope"></i>
                    </span>
                <input name="email" class="w-100" id="email"
                       style="padding: 12px 11px 10px 10px;
                                         outline: none;
                                         border: none;
                                         border-bottom-right-radius: 10px;
                                         border-top-right-radius: 10px; " type="email" placeholder="Email" required>
            </div>
            <div class="input d-flex mt-3" style="border: 1px solid #ccc; border-radius: 10px;">
                    <span class="d-flex justify-content-center align-items-center"
                          style="background-color: #ccc;
                        padding-left: 18px;
                        padding-right: 18px;
                        border-bottom-left-radius: 8px;
                        border-top-left-radius: 9px;
                      ">
                            <i class="fa-solid fa-key"></i>
                    </span>
                <input
                        id="password"
                        class="w-100 "
                        required
                        style="padding: 12px 11px 10px 10px;
                     outline: none;
                     border: none;
                     border-bottom-right-radius: 10px;
                     border-top-right-radius: 10px; "
                        name="password"
                        type="password"
                        placeholder="Mật khẩu">
            </div>
            <div class="input d-flex mt-3 mb-2" style="border: 1px solid #ccc; border-radius: 10px;">
                <button type="submit" class="btn btn-success w-100">Login</button>
            </div>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous">
</script>
</body>
</html>
</html>