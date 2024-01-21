<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="utf-8">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
<%--    <meta name="viewport" content="width=device-width,initial-scale=1">--%>
<%--    <title>Focus - Bootstrap Admin Dashboard </title>--%>
<%--    <!-- Favicon icon -->--%>
<%--</head>--%>

<%--<body>--%>
<%--<div class="col-lg-12 grid-margin stretch-card">--%>
<%--    <div class="card">--%>
<%--        <div class="card-body">--%>
<%--            <h4 class="card-title">Doanh thu sản phẩm--%>
<%--                <form action="/thong-ke/loc-hang" method="post" style="float: right">--%>
<%--                    <select name="hangSelect">--%>
<%--                        <option value="" disabled selected>Chọn hãng</option>--%>
<%--                        <c:forEach items="${listHang}" var="hang">--%>
<%--                            <option value="${hang.getTenHang()}">${hang.getTenHang()}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                    <button type="submit" class="btn btn-primary mr-2"--%>
<%--                            onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">--%>
<%--                        Lọc--%>
<%--                    </button>--%>
<%--                </form>--%>
<%--            </h4>--%>

<%--            <div class="table-responsive">--%>
<%--                <table class="table table-striped" style="color: black">--%>
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th>Tên sản phẩm</th>--%>
<%--                        <th>Số lượng sản phẩm đã bán</th>--%>
<%--                        <th>Số lượng tồn</th>--%>
<%--                        <th>Doanh thu tháng</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    <i class="mdi mdi-border-color"></i>--%>
<%--                    <c:forEach items="${listDoanhThuSP}" var="DTSP" varStatus="index">--%>
<%--                        <tr>--%>
<%--                            <td>${DTSP.getTenSanPham()}</td>--%>
<%--                            <td>${DTSP.getSoLuongSP()}</td>--%>
<%--                            <td>${DTSP.getSoLuongTon()}</td>--%>
<%--                            <td>${DTSP.getDoanhThu()}</td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
<%--                    </tbody>--%>
<%--                </table>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<div><h3>Biểu đồ thống kê doanh thu theo sản sản phẩm</h3></div>--%>
<%--<div class="card">--%>
<%--    <div class="card-body">--%>
<%--       <canvas id="myChart" ></canvas>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>--%>
<%--<script>--%>
<%--    const data = [];--%>

<%--    <c:forEach items="${listDoanhThuSP}" var="DTSP" varStatus="index">--%>
<%--    data.push({--%>
<%--        ten: "${DTSP.getTenSanPham()}",--%>
<%--        doanhThu: ${DTSP.getDoanhThu()},--%>
<%--        soLuongBan: ${DTSP.getSoLuongSP()},--%>
<%--        soLuongTon: ${DTSP.getSoLuongTon()}--%>
<%--    });--%>
<%--    </c:forEach>--%>

<%--    const ctx = document.getElementById('myChart');--%>

<%--    new Chart(ctx, {--%>
<%--        type: 'bar',--%>
<%--        data: {--%>
<%--            labels: data.map(item => item.ten),--%>
<%--            datasets: [{--%>
<%--                label: 'Doanh Thu',--%>
<%--                data: data.map(item => item.doanhThu),--%>
<%--                borderWidth: 1,--%>
<%--                yAxisID: 'y'--%>
<%--            }, {--%>
<%--                label: 'Số lượng bán',--%>
<%--                data: data.map(item => item.soLuongBan),--%>
<%--                borderWidth: 1,--%>
<%--                yAxisID: 'y1'--%>
<%--            },{--%>
<%--                label: 'Số lượng tồn',--%>
<%--                data: data.map(item => item.soLuongTon),--%>
<%--                borderWidth: 1,--%>
<%--                yAxisID: 'y1'--%>
<%--            }]--%>
<%--        },--%>
<%--        options: {--%>
<%--            scales: {--%>
<%--                y: {--%>
<%--                    type: 'linear',--%>
<%--                    display: true,--%>
<%--                    position: 'left',--%>
<%--                    id: 'y'--%>
<%--                },--%>
<%--                y1: {--%>
<%--                    type: 'linear',--%>
<%--                    display: true,--%>
<%--                    position: 'right',--%>
<%--                    id: 'y1',--%>

<%--                    // grid line settings--%>
<%--                    grid: {--%>
<%--                        drawOnChartArea: false, // only want the grid lines for one axis to show up--%>
<%--                    },--%>
<%--                },--%>
<%--            }--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>
<%--</html>--%>
