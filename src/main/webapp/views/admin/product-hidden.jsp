<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Products</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
            <a href="/admin/products/create-product">
                <button type="button" class="btn btn-sm btn-outline-secondary">
                    <i class="fa-solid fa-circle-plus"></i>
                    Add Product
                </button>
            </a>
        </div>
    </div>
    <div class="container-fluid g-0">
        <div class="mb-4">
            <a href="/admin/products">Product showing</a>
        </div>
        <div class="row pb-3 ">
            <c:forEach var="p" items="${products}">
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="card product-item border-0 mb-4">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="${p.image}" alt="">
                        </div>
                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3">${p.name}</h6>
                            <div class="d-flex justify-content-center">
                                <h6 class="price" >${p.price}</h6>
                                <h6 class="text-muted ml-2">
                                    <del class="price">${p.price}</del>
                                </h6>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between bg-light border">
                            <a href="/admin/products/detail?id=${p.id}" class="btn btn-sm text-dark p-0">
                                <i class="fas fa-eye "></i>
                                View Detail
                            </a>
                            <a href="/admin/products/change?id=${p.id}&status=1" class="btn btn-sm text-dark p-0">
                                <i class="fa-solid fa-right-left"></i>
                                Show
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</main>
