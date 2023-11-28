<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Product Details</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
                <button onclick="update()" type="button" class="btn btn-sm btn-outline-primary">
                    <i class="fa-solid fa-check"></i>
                    Save
                </button>
        </div>
    </div>
    <div class="container-fluid g-0">
        <div class="row mt-5">
            <div class="col-12 col-md-6">
                <input id="id" hidden type="text" value="${product.id}">
                <div class="mb-3">
                    <label for="product-name" class="form-label fw-bold pb-2">Product Name:</label>
                    <input value="${product.name}" required type="text" class="form-control input" id="product-name" placeholder="Product name">
                </div>
                <div class="mb-3">
                    <label for="product-price" class="form-label fw-bold pb-2">Product price:</label>
                    <input value="${product.price}" required min="0" type="number" class="form-control input" id="product-price" placeholder="Product price">
                </div>
                <div class="mb-3">
                    <label for="quantity-in-stock" class="form-label fw-bold pb-2">Product Quantity In Stock:</label>
                    <input value="${product.quantityInStock}" required min="0" step="1" type="number" class="form-control input" id="quantity-in-stock" placeholder="Product quantity">
                </div>
                <div class="mb-3">
                    <label  class="form-label fw-bold pb-2">Brand</label>
                    <select id="brand"  class="form-select select" aria-label="Default select example">
                        <c:forEach var="brand" items="${brandList}">
                            <option value="${brand.id}" ${brand.id == product.brandId ? 'selected' : ''}>${brand.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="mb-3">
                    <label  class="form-label fw-bold pb-2">Category</label>
                    <select id="category" class="form-select select" aria-label="Default select example">
                        <c:forEach var="category" items="${categoryList}">
                            <option value="${category.id}" ${category.id == product.categoryId ? 'selected' : ''}>${category.name}</option>
                        </c:forEach>
                    </select>
                </div>

            </div>
            <div class="col-12 col-md-6">
                <div class="wrapper-img d-flex justify-content-center" style="height: 300px">
                    <img id="imagePreview" style=" max-width: 300px; max-height: 100%; object-fit: scale-down" src="${product.image}" alt="">
                </div>
                <div class="wrapper-btn d-flex justify-content-center pt-3">
                    <button id="btnUpload" class="btn text-danger btn-outline-secondary">
                        Choose
                        <input value="${product.image}" id="inputImage" hidden type="file" accept="image/*">
                    </button>
                </div>
            </div>
        </div>
    </div>
    <script src = "/js/handlerProductAdmin/main.js"></script>
</main>
