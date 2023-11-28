<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">Orders</h1>
        <div class="btn-toolbar mb-2 mb-md-0">
            <a href="/admin/order/create">
                <button type="button" class="btn btn-sm btn-outline-secondary">
                    <i class="fa-solid fa-circle-plus"></i>
                    Create new order
                </button>
            </a>

        </div>
    </div>
    <div class="container-fluid g-0">
        <div class="row pb-3 g-0">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Customer</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Address</th>
                    <th scope="col">Total</th>
                    <th scope="col">Create At</th>
                    <th scope="col">Detail</th>
                    <th scope="col">Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="o" items="${orders}" varStatus="loopStatus">
                    <tr>
                        <th scope="row">${loopStatus.index +1}</th>
                        <td>${o.customerName}</td>
                        <td>${o.phone}</td>
                        <td>${o.address}</td>
                        <td class="price">${o.totalAmount}</td>
                        <td>${o.createdAt}</td>
                        <td>
                            <a href="/admin/order/detail/${o.id}">
                                <button type="button" class="btn btn-sm btn-outline-secondary">
                                    <i class="fa-solid fa-eye"></i>
                                    View Detail
                                </button>
                            </a>
                        </td>
                        <td>
                            <a href="/admin/order/remove/${o.id}">
                                <button type="button" class="btn btn-sm btn-danger">
                                    <i class="fa-solid fa-trash"></i>
                                    Delete
                                </button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                </tbody>
            </table>
        </div>
    </div>
    <script>
    </script>
</main>
