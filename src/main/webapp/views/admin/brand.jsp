<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Brands</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
            <button type="button" class="btn btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                <i class="fa-solid fa-circle-plus"></i>
                Add Brand
            </button>
        </div>
    </div>
    <div class="container-fluid g-0">
        <div class="row pb-3 g-0">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Brand Name</th>
                    <th scope="col">Create At</th>
                    <th scope="col">Update At</th>
                    <th scope="col">Detail</th>
                    <th scope="col">Remove</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="b" items="${brands}" varStatus="loopStatus">
                    <tr>
                        <th scope="row">${loopStatus.index +1}</th>
                        <td>${b.name}</td>
                        <td>${b.createdAt}</td>
                        <td>${b.updatedAt}</td>
                        <td>
                            <a href="/admin/brand/detail?id=${b.id}">
                                <button type="button" class="btn btn-sm btn-outline-secondary">
                                    <i class="fa-solid fa-eye"></i>
                                    View Detail
                                </button>
                            </a>

                        </td>
                        <td>
                            <a href="/admin/brand/remove?id=${b.id}">
                                <button type="button" class="btn btn-sm btn-danger">
                                    <i class="fa-solid fa-trash"></i>
                                    Delete
                                </button>
                            </a>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="modal fade " id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog ">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Add Brand</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="name" class="form-label fw-bold pb-2">Brand Name:</label>
                                <input required type="text" class="form-control input" id="name" placeholder="Brand name">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button onclick="create()" type="button" class="btn btn-primary">Add</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        let create =()=>{
            let name = document.getElementById("name");
            name.oninput =()=>{
                name.style.border = '1px solid #ced4da';
            }
            name.onblur = ()=>{
                if(!name.value){
                    name.style.border = '2px solid red';
                }
            }
            if(!name.value) {
                name.style.border = '2px solid red';
                return false;
            }
            let formData = new FormData();
            formData.append("name", name.value);
            let url = "/api/v1/admin/brand/create-brand"
            postProduct(url, formData);
        }
        let postProduct =(url,formData) => {
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
                });
        }
    </script>
</main>
