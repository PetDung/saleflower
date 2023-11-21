<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    /* Thiết lập chiều cao tối đa cho bảng */
    table {
        max-height: 300px;
        overflow-y: auto;
        display: block;
    }

    /* Thiết lập chiều rộng cố định cho các ô trong bảng */
    table {
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
        width: 100vh;
    }

    th {
        background-color: #f2f2f2;
    }
</style>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Categories Detail</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
            <button onclick="update()" type="button" class="btn btn-sm btn-outline-secondary">
                <i class="fa-solid fa-check"></i>
                Save
            </button>
        </div>
    </div>
    <div class="container-fluid g-0">
        <div class="row pb-3">
            <div class="col-12 col-md-6">
                <div class="mb-3">
                    <label for="name" class="form-label fw-bold pb-2">Category Name:</label>
                    <input hidden type="text" id="id" value="${c.id}">
                    <input value="${c.name}" required type="text" class="form-control input" id="name" placeholder="Category name">
                </div>
                <h4 class="pt-2">Product Now</h4>
                <table class="table" style="max-height: 550px; overflow-y: auto;display: block">
                    <thead>
                    <tr>
                        <th scope="col">Image</th>
                        <th scope="col">Product Name</th>
                        <th scope="col">Check</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${c.productList}">
                        <tr>
                            <td>
                               <c:set var="productImagePath" value="/api/v1/image/file/product/${p.image.id}" />
                                <img style="width: 60px; object-fit: scale-down"
                                     src="${p.image !=null ? productImagePath : '/img/nothing.png'}"
                                     alt=""
                                >
                            <td>
                                    ${p.name} <br>
                                <span style="color: red; font-size: 12px">now: ${p.category.name}</span>
                            </td>
                            <td>
                                <input class="checkName" data-id="${p.id}" checked ="true" style="width: 20px; height: 20px;" type="checkbox">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
           <div class="col-12 col-md-6">
               <h4 class="pt-2">Add Product</h4>
               <table class="table" style="max-height: 600px; overflow-y: auto;">
                   <thead>
                   <tr>
                       <th scope="col">Image</th>
                       <th scope="col">Product Name</th>
                       <th scope="col">Check</th>
                   </tr>
                   </thead>
                   <tbody>
                   <c:forEach var="p" items="${productListNotCategory}">
                       <tr>
                           <td>
                              <c:set var="productImagePath" value="/api/v1/image/file/product/${p.image.id}" />
                               <img style="width: 60px; object-fit: scale-down"
                                    src="${p.image !=null ? productImagePath : '/img/nothing.png'}"
                                    alt=""
                               >
                           </td>
                           <td>
                                   ${p.name} <br>
                               <span style="color: red; font-size: 12px">now: ${p.category.name}</span>
                           </td>
                           <td>
                               <input class="checkName" data-id="${p.id}"  style="width: 20px; height: 20px;" type="checkbox">
                           </td>
                       </tr>
                   </c:forEach>
                   </tbody>
               </table>
           </div>
        </div>
    </div>
    <script>
        let update =()=>{
            let products= [];
            let category = document.getElementById("name");
            let id = document.getElementById("id").value;
            category.oninput =()=>{
                category.style.border ="2px solid #ced4da";
            }
            category.onblur =()=>{
                if(!category.value){
                    category.style.border ="2px solid red";
                    return false;
                }
            }
            if(!category.value){
                category.style.border ="2px solid red";
                return false;
            }
            let checks =document.querySelectorAll(".checkName");
            checks.forEach(item=>{
                if(item.checked){
                    products.push(Number(item.getAttribute("data-id")));
                }
            })
            let formData = new FormData();
            formData.append("id",id);
            formData.append("name",category.value);
            formData.append("products",products);

            let url = "/api/v1/admin/category/update-category"
            postCategory(url,formData);
        }
        let postCategory =(url,formData) =>{
            fetch(url, {
                method: 'POST',
                body: formData,
            })
                .then(response => {
                    if (!response.ok) {
                        return response.json()
                            .then(errorData => {
                                throw new Error(errorData.message);
                            });
                    }
                    return response.json();
                })
                .then(data => {
                    alert("Successfully")
                    window.location.reload();
                })
                .catch(error => {
                    alert(error.message);
                    window.location.reload();
                });
        }
    </script>
</main>
