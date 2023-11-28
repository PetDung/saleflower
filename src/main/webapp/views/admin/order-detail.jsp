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
                    <input hidden type="text" id="id" value="${o.id}">
                    <input value="${o.customerName}" required type="text" class="form-control input" id="name" placeholder="Category name">
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label fw-bold pb-2">Address:</label>
                    <input value="${o.address}" required type="text" class="form-control input" id="address" placeholder="Category name">
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label fw-bold pb-2">Phone:</label>
                    <input value="${o.phone}" required type="text" class="form-control input" id="phone" placeholder="Category name">
                </div>
                <div class="mb-3">
                    <label  class="form-label fw-bold pb-2">Status</label>
                    <select id="status" class="form-select select" aria-label="Default select example">
                        <option value="0" ${o.status == 0 ? 'selected' : ''}>
                            Wait for confirmation
                        </option>
                        <option value="1" ${o.status == 1 ? 'selected' : ''}>Shipping</option>
                        <option value="2" ${o.status == 2 ? 'selected' : ''}>Delivered</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label  class="form-label fw-bold pb-2">Order By:</label>
                    <span id="customer" data-id="${o.customer.id}" class="fw-bold">${o.customer.userName}</span>
                </div>
                <div class="mb-3">
                    <label  class="form-label fw-bold pb-2">Total:</label>
                    <span id="total" style="font-size: 20px" class="fw-bold price">${o.totalAmount}</span>
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
                    <c:forEach var="od" items="${o.orderDetails}">
                        <tr>
                            <td>
                               <c:set var="productImagePath" value="/api/v1/image/file/product/${od.product.image.id}" />
                                <img style="width: 60px; object-fit: scale-down"
                                     src="${od.product.image !=null ? productImagePath : '/img/nothing.png'}"
                                     alt=""
                                >
                            <td>
                                    ${od.product.name} - <span class="price"> ${od.product.price}</span>
                                    <input class="pd-id" type="text" value="${od.product.id}" hidden>
                                        <br>
                                    <span class="d-block pt-3">
                                        <input style=" width: 80px;padding:4px; font-size: 16px"
                                               class="input-qtn"
                                               data-price ="${od.product.price}"
                                               oninput="handlerInput(this)"
                                               onchange="handlerInput(this)"
                                               type="number"
                                               value="${od.quantity}"
                                               min = "1"
                                               step= "1"
                                               max = "${od.product.quantityInStock}"
                                               required
                                        >
                                        Total: <span class="price total">${od.quantity * od.product.price}</span>
                                    </span>
                            </td>
                            <td>
                                <input oninput="loadTotal()" class="checkName" data-id="${od.product.id}" checked ="true" style="width: 20px; height: 20px;" type="checkbox">
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
                   <c:forEach var="p" items="${productList}">
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
            if(quantity < 0 ) {
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
        let submit= ()=>{
            let id = document.getElementById("id").value;
            let data = getForm();
            if(!data) return;
            console.log(data)
            $.ajax({
                type: "POST",
                url: "/api/v1/admin/order/update?id=" + id  ,  // Thay "/search" bằng địa chỉ API của bạn
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function(response) {
                    console.log(response);
                    alert("success");
                    location.reload();
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
            let status = document.getElementById("status").value;
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
            function Order (customerName,phone,address,orderBy,orderDetalList,status){
                this.customerName = customerName;
                this.phone = phone;
                this.address = address;
                this.orderBy = orderBy;
                this.orderDetalList = orderDetalList;
                this.status = status;

            }
            return  new Order(customerName, phone, address, orderBy, orderDetalList,status);

        }
    </script>
</main>
