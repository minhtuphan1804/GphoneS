<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Focus - Bootstrap Admin Dashboard </title>
    <!-- Favicon icon -->
</head>

<body>

<div class="col-lg-3 col-sm-6" style="margin-left: 450px">
    <div class="card">
        <div class="stat-widget-two card-body">
            <div class="stat-content">
                <c:forEach items="${listDoanhThuNgay}" var="dtNgay">
                    <div class="stat-text" style="color: black">Doanh thu ngày ${dtNgay.getNgay()}/${dtNgay.getThang()}/${dtNgay.getNam()} </div>

                    <div class="stat-digit"> <script>
                        var tongTien = ${dtNgay.getDoanhThuThucTe()};
                        document.write(tongTien.toLocaleString('vi-VN'));
                    </script> VNĐ</div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<div class="card">
    <h4 class="card-title">Biểu đồ doanh thu các tháng trong năm </h4>
    <form action="/thong-ke/loc-nam" method="post" style="text-align: right">
        <select name="namSelect" style="height: 34.78px">
            <option value="" disabled selected>Chọn năm</option>
            <c:forEach items="${listYear}" var="nam">
                <option value="${nam.getNam()}">${nam.getNam()}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-primary mr-2"
                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
            Lọc
        </button>
    </form>
    <div class="card-body">
        <canvas id="myChart" ></canvas>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
<script>
    const data = [];

    <c:forEach items="${listDoanhThu}" var="DT" varStatus="index">
    data.push({
        thang: ${DT.getThang()},
        soLuong: ${DT.getSoLuongSP()},
        tienDoiTra: ${DT.getTienDoiTra()},
        doanhThuThucTe: ${DT.getDoanhThuThucTe()},
        doanhThuChuaKhuyenMai: ${DT.getDoanhThuChuaKhuyenMai()}
    });
    </c:forEach>

    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: data.map(item => item.thang),
            datasets: [{
                label: 'Doanh thu thực tế',
                data: data.map(item => item.doanhThuThucTe),
                borderWidth: 1,
                yAxisID: 'y',
                borderColor: '#ff6384',
                backgroundColor: '#ff6384',
            },
                {
                    label: 'Số lượng sản phẩm đã bán',
                    data: data.map(item => item.soLuong),
                    borderWidth: 1,
                    yAxisID: 'y1',
                    borderColor: '#36a2eb',
                    backgroundColor: '#36a2eb',
                },
                {
                    label: 'Tiền đổi trả',
                    data: data.map(item => item.tienDoiTra),
                    borderWidth: 1,
                    yAxisID: 'y',
                    borderColor: '#ffce56',
                    backgroundColor: '#ffce56',
                },
                {
                    label: 'Doanh thu chưa khuyến mãi',
                    data: data.map(item => item.doanhThuChuaKhuyenMai),
                    borderWidth: 1,
                    yAxisID: 'y',
                    borderColor: '#5CF142',
                    backgroundColor: '#5CF142',
                },
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    id: 'y'
                },
                y1: {
                    beginAtZero: true,
                    id: 'y1',
                    position: 'right',

                    // grid line settings
                    grid: {
                        drawOnChartArea: false, // only want the grid lines for one axis to show up
                    },
                },
            }
        }
    });
</script>
</html>
