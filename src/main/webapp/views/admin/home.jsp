<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 mb-5">
  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h2">Dashboard</h1>
  </div>

  <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>
  <h3 class="pt-2">Top 5 Selling Products</h3>
  <table class="table pt-4 pb-5">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Quantity in Stock</th>
      <th scope="col">Quantity sold</th>
      <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${top5}" varStatus="loopStatus">
      <tr>
        <th scope="row">${loopStatus.index +1}</th>
        <td>${p.product.name}</td>
        <td>${p.product.quantityInStock}</td>
        <td>${p.totalQuantity}</td>
        <td>${p.product.price}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script>
    document.addEventListener('DOMContentLoaded', function () {
      var ctx = document.getElementById('myChart').getContext('2d');

      function fetchDataAndRenderChart() {
        fetch('/api/v1/admin/order/revenue?year=2023')
                .then(response => response.json())
                .then(dat => {
                  let labels =[];
                  let data =[];
                  dat.forEach(item=>{
                    labels.push(item.month);
                    data.push(item.revenue);
                  })
                  let d = {
                    labels: labels ,
                    datasets: [{
                      label: 'Monthly Revenue',
                      backgroundColor: 'rgba(45,211,192,0.4)',
                      borderColor: 'rgb(43,218,199)',
                      borderWidth: 4,
                      pointBackgroundColor: 'rgb(27,74,178)', // Màu điểm trên đường đồ thị
                      pointRadius: 4, // Kích thước điểm trên đường đồ thị
                      data: data
                    }]
                  };
                  let myChart = new Chart(ctx, {
                    type: 'line',
                    data: d,
                    options: {
                      scales: {
                        y: {
                          beginAtZero: true
                        }
                      }
                    }
                  });
                  console.log(data)
                })
                .catch(error => console.error('Error fetching data:', error));
      }
      fetchDataAndRenderChart();

    });
  </script>

</main>
