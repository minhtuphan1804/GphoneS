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
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css"/>
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="don-hang/hien-thi" role="tab"
               aria-controls="description" aria-selected="true">Thông tin hóa đơn</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <div>
                        <h4 class="card-title" style="float: left">Danh sách hóa đơn
                        </h4>
                        <div class="basic-dropdown" style="float: right">
                            <div class="dropdown">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                    <i class="ti-export btn-icon-prepend"></i>
                                    Xuất Excel
                                </button>
                                <div class="dropdown-menu">
                                    <a href="/don-hang/export-excel" class="dropdown-item" tabindex="-1">Theo ngày thanh
                                        toán</a>
                                    <a href="/don-hang/export-excel-ngay-nhan" class="dropdown-item" tabindex="-1">Theo
                                        ngày nhận</a>
                                    <a href="/don-hang/export-excel-ngay-ship" class="dropdown-item" tabindex="-1">Theo
                                        ngày ship</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <div class="loc" style="color:black;">
                        <form:form action="/don-hang/loc" method="post" modelAttribute="donHang">
                            <div class="row" style="margin-top: 10px">
                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <div class="col-sm-12">
                                            <select id="selectKhachHang1" name="khachHang" class="form-control select2"
                                                    style="font-weight: bold; width: 100%">
                                                <option selected disabled>Khách hàng</option>
                                                <c:forEach items="${listKhachHang}" var="khachHang">
                                                    <option value="${khachHang.id}">${khachHang.hoTen}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <div class="col-sm-12">
                                            <select id="selectNhanVien1" name="nhanVien" class="form-control select2"
                                                    style="font-weight: bold; width: 100%">
                                                <option selected disabled>Nhân viên</option>
                                                <c:forEach items="${listNhanVien}" var="nhanVien">
                                                    <option value="${nhanVien.id}">${nhanVien.hoTen}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <div class="col-sm-12">
                                            <select id="selectTrangThai1" name="trangThai" class="form-control select2"
                                                    style="font-weight: bold; width: 100%">
                                                <option selected disabled>Trạng thái hóa đơn</option>
                                                <option value="0">Hóa đơn chờ</option>
                                                <option value="1">Hóa đơn đã xác nhận</option>
                                                <option value="3">Hóa đơn chờ thanh toán</option>
                                                <option value="2">Hóa đơn đã thanh toán</option>
                                                <option value="8">Hóa đơn đã hủy</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Từ ngày thanh toán:</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="ngayTao" name="startDate" class="form-control"
                                                   placeholder="Từ ngày">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-5 col-form-label">Từ ngày nhận:</label>
                                        <div class="col-sm-7">
                                            <input type="date" id="ngayNhan" name="receiveStartDate"
                                                   class="form-control"
                                                   placeholder="Từ ngày">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Từ ngày ship:</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="ngayShip" name="shipStartDate" class="form-control"
                                                   placeholder="Từ ngày  ">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Đến ngày thanh toán:</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="ngayTao1" name="endDate" class="form-control"
                                                   placeholder="Đến ngày">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-5 col-form-label">Đến ngày nhận:</label>
                                        <div class="col-sm-7">
                                            <input type="date" id="ngayNhan1" name="receiveEndDate" class="form-control"
                                                   placeholder="Đến ngày">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Đến ngày ship:</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="ngayShip1" name="shipEndDate" class="form-control"
                                                   placeholder="Đến ngày  ">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div align="center">
                                <BUTTON type="submit" class="btn btn-warning" style="" id="bt">
                                    Lọc hóa đơn
                                </BUTTON>
                            </div>
                        </form:form>
                    </div>
                    <br>
                    <div class="search">
                        <form action="/don-hang/search" method="post">
                            <div class="input-group" style="width: 30%; float: right">
                                <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                       aria-label="Bạn tìm gì..." name="search">
                                <div class="input-group-append">
                                    <button class="btn btn-sm btn-primary" type="submit">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example" class="display" style="color: black; width: 2800px">
                            <thead>
                            <tr>
                                <th>Mã hóa đơn</th>
                                <th>Ngày Tạo</th>
                                <th>Tên khách hàng</th>
                                <th>Tên nhân viên</th>
                                <th>Địa chỉ</th>
                                <th>SĐT</th>
                                <th>Tổng tiền</th>
                                <th>Trạng thái hóa đơn</th>
                                <th>Trạng thái giao hàng</th>
                                <th>Hình thức thanh toán</th>
                                <th>Ngày Thanh Toán</th>
                                <th>Ngày nhận</th>
                                <th>Ngày ship</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listHoaDon}" var="donHang">
                                <tr>
                                    <td>${donHang.ma}</td>
                                    <td>${donHang.ngayTao}</td>
                                    <td>${donHang.khachHang.hoTen}</td>
                                    <td>${donHang.nhanVien.hoTen}</td>
                                    <td>${donHang.diaChi.diaChi}-${donHang.diaChi.quan}-${donHang.diaChi.huyen}-${donHang.diaChi.thanhPho}</td>
                                    <td>${donHang.sdt}</td>
                                    <td>${donHang.tongTien}</td>
                                    <td>
                                        <c:if test="${donHang.tinhTrang == 0}">Đang chờ</c:if>
                                        <c:if test="${donHang.tinhTrang == 1}">Đã xác nhận</c:if>
                                        <c:if test="${donHang.tinhTrang == 2}">Đã thanh toán</c:if>
                                        <c:if test="${donHang.tinhTrang == 3}">Chờ thanh toán</c:if>
                                        <c:if test="${donHang.tinhTrang == 8}">Đã hủy</c:if>

                                    </td>
                                    <td>
                                        <c:if test="${donHang.tinhTrangGiaoHang == 0}">Chờ xử lý</c:if>
                                        <c:if test="${donHang.tinhTrangGiaoHang == 1}">Chờ giao hàng</c:if>
                                        <c:if test="${donHang.tinhTrangGiaoHang == 2}">Đang giao hàng</c:if>
                                        <c:if test="${donHang.tinhTrangGiaoHang == 3}">Giao hàng hoàn tất</c:if>
                                        <c:if test="${donHang.tinhTrangGiaoHang == 8}">Đã hủy</c:if>
                                    </td>

                                    <td>
                                        <c:if test="${donHang.hinhThucThanhToan == 1}">Online</c:if>
                                        <c:if test="${donHang.hinhThucThanhToan == 0}">Tiền mặt</c:if>
                                    </td>
                                    <td>${donHang.ngayThanhToan}</td>
                                    <td>${donHang.ngayNhan}</td>
                                    <td>${donHang.ngayShip}</td>
                                    <td>
                                        <a href="/don-hang/detail/${donHang.id}" class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Detail</a>
                                        <c:if test="${donHang.nhanVien== null && (donHang.tinhTrang == 2 || donHang.tinhTrang==3)}">
                                            <a href="/don-hang/xac-nhan/${donHang.id}"
                                               class="btn btn-info btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                     fill="currentColor" class="bi bi-check-circle-fill"
                                                     viewBox="0 0 16 16">
                                                    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                </svg>
                                                Xác nhận đơn hàng</a>
                                        </c:if>
                                        <c:if test="${donHang.tinhTrangGiaoHang == 1 || donHang.tinhTrangGiaoHang == 0}">
                                            <a href="/don-hang/xac-nhan-giao-hang/${donHang.id}"
                                               class="btn btn-info btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                     fill="currentColor" class="bi bi-truck" viewBox="0 0 16 16">
                                                    <path d="M0 3.5A1.5 1.5 0 0 1 1.5 2h9A1.5 1.5 0 0 1 12 3.5V5h1.02a1.5 1.5 0 0 1 1.17.563l1.481 1.85a1.5 1.5 0 0 1 .329.938V10.5a1.5 1.5 0 0 1-1.5 1.5H14a2 2 0 1 1-4 0H5a2 2 0 1 1-3.998-.085A1.5 1.5 0 0 1 0 10.5zm1.294 7.456A1.999 1.999 0 0 1 4.732 11h5.536a2.01 2.01 0 0 1 .732-.732V3.5a.5.5 0 0 0-.5-.5h-9a.5.5 0 0 0-.5.5v7a.5.5 0 0 0 .294.456M12 10a2 2 0 0 1 1.732 1h.768a.5.5 0 0 0 .5-.5V8.35a.5.5 0 0 0-.11-.312l-1.48-1.85A.5.5 0 0 0 13.02 6H12zm-9 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2m9 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2"/>
                                                </svg>
                                                Giao hàng</a>
                                        </c:if>

                                        <c:if test="${donHang.tinhTrangGiaoHang == 2 && (donHang.tinhTrang==2||donHang.tinhTrang==3)}">
                                            <a href="/don-hang/xac-nhan-giao-hang-hoan-tat/${donHang.id}"
                                               class="btn btn-outline-success btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <img src="/uploads/shipped.png">
                                                Giao hàng hoàn tất
                                            </a>
                                        </c:if>
                                        <c:if test="${donHang.tinhTrangGiaoHang == 2 && donHang.hinhThucThanhToan == 0}">

                                            <a href="/don-hang/xac-nhan-huy/${donHang.id}"
                                               class="btn btn-outline-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="ti-file btn-icon-prepend"></i>
                                                Hủy</a>

                                        </c:if>
                                        <c:if test="${(donHang.tinhTrangGiaoHang==0||donHang.tinhTrangGiaoHang==1)&&(donHang.tinhTrang == 2 || donHang.tinhTrang==3)}">
                                            <a href="/don-hang/view-update/${donHang.id}"
                                               class="btn btn-info btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="ti-reload btn-icon-prepend"></i>
                                                Update thông tin</a>
                                        </c:if>
                                        <c:if test="${donHang.tinhTrang == 2 || donHang.tinhTrang==3}">
                                            <a href="/don-hang/xuat-pdf/${donHang.id}"
                                               class="btn btn-outline-success btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="ti-file btn-icon-prepend"></i>
                                                Xuất PDF</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
<script>

    $('#selectKhachHang1').select2({
        theme: 'bootstrap-5'
    });
    $('#selectNhanVien1').select2({
        theme: 'bootstrap-5'
    });
    $('#selectDiaChi1').select2({
        theme: 'bootstrap-5'
    });
    $('#selectTrangThai1').select2({
        theme: 'bootstrap-5'
    });

</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>
