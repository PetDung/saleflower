<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Categories</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
            <button onclick="{ reset(); save.onclick =() => {create()}}" type="button" class="btn btn-sm btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                <i class="fa-solid fa-circle-plus"></i>
                Create New User
            </button>
        </div>
    </div>
    <div class="container-fluid g-0">
        <div class="row pb-3 g-0">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">User Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Joined Date</th>
                    <th scope="col">Detail</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="u" items="${users}" varStatus="loopStatus">
                    <tr>
                        <th scope="row">${loopStatus.index +1}</th>
                        <td>${u.userName}</td>
                        <td>${u.email}</td>
                        <td>${u.phone}</td>
                        <td>${u.createdAt}</td>
                        <td>
                            <button onclick="callData(this)" data-bs-toggle="modal" data-bs-target="#staticBackdrop" data-id="${u.id}" type="button" class="btn btn-sm btn-outline-secondary">
                                    <i class="fa-solid fa-eye"></i>
                                    View Detail
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="modal fade " id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog ">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">Create User</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <label for="name" class="form-label fw-bold pb-2">User Name:</label>
                                <input required type="text" class="form-control input" id="name" placeholder="User Name">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label fw-bold pb-2">Email:</label>
                                <input required type="text" class="form-control input" id="email" placeholder="Email">
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label fw-bold pb-2">Phone:</label>
                                <input required type="text" class="form-control input" id="phone" placeholder="Phone">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label fw-bold pb-2">Password:</label>
                                <input required type="password" class="form-control input" id="password" placeholder="Password">
                            </div>
                            <div class="mb-3">
                                <label for="enter-password" class="form-label fw-bold pb-2">Enter Password:</label>
                                <input required type="password" class="form-control input" id="enter-password" placeholder="Enter-password">
                            </div>
                            <label  class="form-label fw-bold pb-2">Role:</label>
                            <select id="role" class="form-select select" aria-label="Default select example">
                                <c:forEach var="r" items="${roles}">
                                    <option value="${r.id}">${r.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button id="save" type="button" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        let save = document.getElementById("save");

        let id ;
        let validateAndGetForm =()=>{
            let inputs = document.querySelectorAll(".input");
            let name = document.getElementById("name");
            let email = document.getElementById("email");
            let phone = document.getElementById("phone");
            let password = document.getElementById("password");
            let enterPassword = document.getElementById("enter-password");
            let role = document.getElementById("role");

            let isValid = true;

            inputs.forEach(item =>{
                if(!item.value || item.value < 0) {
                    item.style.border = '2px solid red';
                    isValid = false;
                }
                item.oninput =()=>{
                    if(item.value || item.value >=0 ){
                        item.style.border = '1px solid #ced4da';
                    }
                }
                item.onblur =()=>{
                    if(!item.value  || item.value < 0) item.style.border = '2px solid red'
                    return false;
                }
            })
            if(password.value !== enterPassword.value ){
                isValid = false;
                password.style.border = '2px solid red';
                enterPassword.style.border = '2px solid red';
            }
            if(!isValidPhoneNumber(phone.value)){
                phone.style.border = '2px solid red';
                isValid = false;
            }
            if(!isValidEmail(email.value)){
                email.style.border = '2px solid red';
                isValid = false;
            }
            if(!isValid) return false;
            let formData = new FormData();
            formData.append("userName",name.value);
            formData.append("password",password.value);
            formData.append("email",email.value);
            formData.append("phone",phone.value);
            formData.append("role",role.value);
            return formData;
        }
        let create =()=>{
            let formData = validateAndGetForm();
            if(!formData) return null;
            let url = "/api/v1/admin/user/create-user"
            postProduct(url,formData);
        }
        let update =() =>{
            let formData = validateAndGetForm();
            if(!formData) return null;
            let url = "/api/v1/admin/user/update-user/" + id;
            postProduct(url,formData);
        }
        let callData= (e)=>{
            reset();
            save.onclick = ()=>{
                update();
            };
            id = e.getAttribute("data-id");
            let url = "/api/v1/admin/user/detail/" + id;
            callAPI(url)
                .then((result) => {
                    console.log('Dữ liệu nhận được từ API:', result);
                    let name = document.getElementById("name");
                    let email = document.getElementById("email");
                    let phone = document.getElementById("phone");
                    let password = document.getElementById("password");
                    let enterPassword = document.getElementById("enter-password");
                    let role = document.getElementById("role");

                    name.value = result.userName;
                    email.value = result.email;
                    phone.value = result.phone;
                    password.value = result.password;
                    enterPassword.value = result.password;
                    role.value = result.roles[0].id;
                    console.log(result.roles[0].id)

                })
                .catch((error) => {
                    console.log('Lỗi khi gọi API:', error);
                });
        }
        async function callAPI(url) {
            try {
                const response = await fetch(url);
                const data = await response.json();
                return data;
            } catch (error) {
                console.log('Lỗi khi gọi API:', error);
                return [];
            }
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

        function isValidPhoneNumber(phoneNumber) {
            let phoneRegex = /^\d{10}$/;
            return phoneRegex.test(phoneNumber);
        }
        function isValidEmail(email) {
            let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return emailRegex.test(email);
        }
        let reset =()=>{
            let inputs = document.querySelectorAll(".input");
            inputs.forEach(item => {
                item.style.border = '2px solid #ced4da';
                item.value ="";
            })
        }
    </script>
</main>
