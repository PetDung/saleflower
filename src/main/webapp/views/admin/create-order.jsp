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
    .item-rs{
        color: white;
        cursor: pointer;
        padding: 2px 4px;
        border-radius: 12px;
    }
    .item-rs:hover{
        background-color:black;
    }
</style>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Order Detail</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
            <button onclick="submit()" type="button" class="btn btn-sm btn-outline-secondary">
                <i class="fa-solid fa-check"></i>
                Save
            </button>
        </div>
    </div>
    <div class="container-fluid g-0">
        <div class="row pb-3">
            <div class="col-12 col-md-6">
                <div class="mb-3">
                    <label for="name" class="form-label fw-bold pb-2">Customer Name:</label>
                    <input  required type="text" class="form-control input" id="name" placeholder="Customer name">
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label fw-bold pb-2">Address:</label>
                    <input  required type="text" class="form-control input" id="address" placeholder="Address">
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label fw-bold pb-2">Phone:</label>
                    <input  required type="text" class="form-control input" id="phone" placeholder="Phone">
                </div>
                <div style="position: relative" class="mb-3">
                    <label for="customer"  class="form-label fw-bold pb-2">Order By:</label>
                    <input data-id="0" oninput="searchCustomer(this)" required type="text" class="form-control input" id="customer" placeholder="Search">
                    <div style="position: absolute; left: 0; right: 0; max-height: 200px;
                                background-color: #a19d9d; top: 100%; margin-top: 2px;
                                border-radius: 12px ; display: none"
                         class="rs-search pt-2 pb-2">

                    </div>
                </div>
                <div class="mb-3">
                    <label  class="form-label fw-bold pb-2">Total:</label>
                    <span id="total" style="font-size: 20px" class="fw-bold price">0</span>
                </div>
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
                   <c:forEach var="p" items="${productLists}">
                       <tr>
                           <td>
                               <img style="width: 60px; object-fit: scale-down"
                                    src="${p.image}"
                                    alt=""
                               >
                           </td>
                           <td>
                                   ${p.name} - <span class="price"> ${p.price}</span>
                                       <input class="pd-id" type="text" value="${p.id}" hidden>
                               <br>
                               <span class="d-block pt-3">
                                        <input style=" width: 80px;padding:4px; font-size: 16px"
                                               class="input-qtn"
                                               data-price ="${p.price}"
                                               oninput="handlerInput(this)"
                                               onchange="handlerInput(this)"
                                               type="number"
                                               value="1"
                                               step= "1"
                                               max = "${p.quantityInStock}"
                                               required
                                        >
                                        Total: <span class="price total">${1 *p.price}</span>
                                    </span>
                           </td>
                           <td>
                               <input oninput="loadTotal()" class="checkName" data-id="${p.id}"      style="width: 20px; height: 20px;" type="checkbox">
                           </td>
                       </tr>
                   </c:forEach>
                   </tbody>
               </table>
           </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        let handlerInput =(e)=>{
            let quantity = Number(e.value);
            let total = e.parentElement.querySelector(".total");
            let price = Number(e.getAttribute("data-price"));
            let max = Number(e.getAttribute("max"))
            if(quantity>max) {
                e.value=max;
                alert("Too much quantity in stock")
                return;
            }
            if(quantity<0) {
                e.value=1;
                return;
            }

            total.innerText = formatCurrency(quantity * price);
            loadTotal();

        }
        let loadTotal = ()=>{
            let checkNames = document.querySelectorAll(".checkName");
            let sum =0;
            let total = document.getElementById("total");
            checkNames.forEach(item=>{
                if(item.checked){
                    let totalTable = item.parentElement
                        .parentElement
                        .querySelector(".total");
                    let totalTableNumber =extractNumberFromString(totalTable.innerText);
                    console.log(totalTableNumber)
                    sum += totalTableNumber;
                }
                total.innerText = formatCurrency(sum);
            })
        }
        let customer = document.getElementById("customer");
        let searchCustomer = (e)=>{
            let result = document.querySelector(".rs-search");
            let userName = e.value;
            if(!userName){
                result.style.display ="none";
                return false;
            }
            let url =`/admin/users/search?userName=` + userName;
            $.ajax({
                type: "GET",
                url: url,
                success: function(response) {
                    if (response.length <= 0) {
                        return false;
                    }
                    result.style.display ="block";
                    let html = "";
                    response.forEach(item=>{
                        html += `<div onclick="handlerClick(this)" class="item-rs"> <span class="username">`+ item.userName + `</span> <br/> <span class="phone">` + item.phone + `</span> <br/> <span hidden class="id">` +  item.id  + `</span> </div>`
                    })
                    result.innerHTML =html;
                },
                error: function(error) {
                    console.error("Error:", error);
                }
            });
        }
        let handlerClick = (e)=>{
            let userName = e.querySelector(".username").innerText;
            let id = e.querySelector(".id").innerText;
            customer.value = userName;
            customer.setAttribute("data-id", id);
            e.parentElement.style.display="none";
        }

        let submit= ()=>{
            let data = getForm();
            if(!data) return;
            $.ajax({
                type: "POST",
                url: "/api/v1/admin/order/create",  // Thay "/search" bằng địa chỉ API của bạn
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function(response) {
                    console.log(response);
                    alert("success");
                },
                error: function(error) {
                    // Xử lý lỗi ở đây
                    console.error("Error:", error);
                    alert(error.message)
                }
            });
        }


        let getForm = ()=>{
            let customerName = document.getElementById("name").value;
            let phone = document.getElementById("phone").value;
            let address = document.getElementById("address").value;
            let orderBy = document.getElementById("customer").getAttribute("data-id");

            if(!customerName || !phone || !address){
                alert("Please enter full data!");
                return false;
            }
            function OrderDetal (productId,quantity){
                this.productId = productId;
                this.quantity = quantity;
            }

            let orderDetalList = [];
            let checkNames = document.querySelectorAll(".checkName");
            checkNames.forEach(item=>{
                if(item.checked){
                    let father = item.parentElement.parentElement;
                    let quantity = extractNumberFromString(father.querySelector(".input-qtn").value);
                    let productId = father.querySelector(".pd-id").value;
                    if(quantity > 0) {
                        orderDetalList.push(new OrderDetal(productId,quantity));
                    }
                }
            })
            function Order (customerName,phone,address,orderBy,orderDetalList){
                this.customerName = customerName;
                this.phone = phone;
                this.address = address;
                this.orderBy = orderBy;
                this.orderDetalList = orderDetalList;

            }
            return  new Order(customerName, phone, address, orderBy, orderDetalList);

        }
    </script>
</main>
