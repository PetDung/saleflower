<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
    <div class="position-sticky pt-3">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" aria-current="page" href="/admin">
                    <i class="fa-solid fa-chart-line"></i>
                    Dashboard
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/order">
                    <i class="fa-solid fa-file-invoice"></i>
                    Orders
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/products">
                    <i class="fa-solid fa-sun"></i>
                    Products
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/users">
                    <i class="fa-solid fa-user"></i>
                    User & Customer
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/brand">
                    <i class="fa-solid fa-code-branch"></i>
                    Brand
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/category">
                    <i class="fa-solid fa-chart-bar"></i>
                    Category
                </a>
            </li>
        </ul>
    </div>
    <style>
        .nav-item{
            font-size: 17px;
            padding: 4px 2px;
        }
    </style>
</nav>
