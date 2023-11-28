<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Products</h1>
        <label id="searchLabel" style="position: relative">
            <input id="search" type="search"
                   style="
                    outline: none ;
                    width: 200px;
                    padding: 4px 4px;
                    transition: all ease-out 0.3s;
                    border: 2px solid #2dcbcb;
                    border-radius: 6px"
                   placeholder="Search"
                   autocomplete="off"
            >
            <div style="position: absolute; left: 0;
                        right: 0; padding: 4px 0;
                        max-height: 300px;z-index: 999;
                        margin-top: 2px;display: none;
                        background-color: rgba(183,177,177,0.78)" class="rs-search">
            </div>
        </label>
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
            <a href="/admin/products/hidden">Product hidden</a>
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
                            <a href="/admin/products/change?id=${p.id}&status=0" class="btn btn-sm text-dark p-0">
                                <i class="fa-solid fa-right-left"></i>
                                Hidden
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        let inputSearch = document.querySelector("#search");
        let result=document.querySelector(".rs-search");
        inputSearch.onclick =()=> {
            inputSearch.style.width = "300px";
        }
        inputSearch.oninput =()=>{
            result.innerHTML="";
            if(!inputSearch.value){
                result.style.display="none";
                return;
            }
            let url =`/api/v1/admin/product/search?name=` + inputSearch.value;
            $.ajax({
                type: "GET",
                url: url,
                success: function(response) {
                    console.log(response)
                    if (response.length <= 0) {
                        result.style.display="none";
                        return false;
                    }
                    result.style.display ="block";
                    let html = "";
                    response.forEach(item=>{
                         html += '<a style="color: #030303; font-weight: bold; text-decoration: none;" href="/admin/products/detail?id=' + item.id + '">' +
                            '<div class="p-2">' +
                            '<img style="width: 40px" src="' + item.image + '" alt="">' +
                            '<span>' + item.name+ '</span>' +
                            '</div>' +
                            '</a>';
                    })
                    result.innerHTML =html;
                },
                error: function(error) {
                    console.error("Error:", error);
                }
            });
        }
        document.addEventListener("click", function (event) {
            let searchLabel = document.getElementById("searchLabel");

            if (event.target !== searchLabel && !searchLabel.contains(event.target)) {
                result.style.display = "none";
                inputSearch.style.width="200px";
            }
        });
    </script>
</main>
