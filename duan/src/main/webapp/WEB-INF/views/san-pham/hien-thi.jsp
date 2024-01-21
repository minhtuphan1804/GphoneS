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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>

</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thông tin Sản Phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/san-pham/hien-thi-tung-xoa" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Sản Phẩm đã xoá</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/san-pham/view-add" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm mới sản
                phẩm</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="col-lg-12 grid-margin stretch-card">
            <form action="/san-pham/loc" method="post" onsubmit="return checkLoc()">
                <div class="card">
                    <div class="card-body">
                        <form class="forms-sample">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select name="hang" class="form-control" style="font-weight: bold; width: 100%"
                                                id="selectHang">
                                            <option selected disabled>Hãng</option>
                                            <c:forEach items="${listHangSP}" var="hang" varStatus="i">
                                                <option value="${hang.id}">${hang.ten}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select name="camera" class="form-control"
                                                style="font-weight: bold; width: 100%" id="selectCamera">
                                            <option selected disabled>Camera</option>
                                            <c:forEach items="${listCamera}" var="cam" varStatus="i">
                                                <option value="${cam.id}">${cam.thongSo}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <select name="manHinh" class="form-control"
                                                style="font-weight: bold; width: 100%" id="selectManHinh">
                                            <option selected disabled>Màn Hình</option>
                                            <c:forEach items="${listManHinh}" var="man" varStatus="i">
                                                <option value="${man.id}">${man.thongSo}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    Lọc Thông Tin
                                </button>
                            </div>


                        </form>
                    </div>
                </div>
                <%--    </div>--%>
            </form>
            <div class="card">
                <div class="card-header">
                    <h3 style="color: red;font-family: 'Times New Roman';text-align: center">${thongBao}</h3>
                </div>
                <div class="card-body">
                    <h4 class="card-title" style="float: left">Danh sách sản phẩm
                        <div class="basic-dropdown">
                            <div class="dropdown">
                                <button type="button" class="btn btn-primary dropdown-toggle"
                                        data-toggle="dropdown">
                                    <i class="ti-export btn-icon-prepend"></i>
                                    Xuất Excel
                                </button>
                                <div class="dropdown-menu">
                                    <a href="/san-pham/export-excel" class="dropdown-item" tabindex="-1">Sản phẩm còn
                                        kinh doanh</a>
                                    <a href="/san-pham/export-excel-san-pham-da-ngung-kinh-doanh" class="dropdown-item"
                                       tabindex="-1">Sản phẩm ngừng kinh doanh</a>

                                </div>
                            </div>
                        </div>
                    </h4>
                    <%--            Tìm kiếm               --%>
                    <form action="/san-pham/search" method="post">
                        <div class="input-group" style="width: 30%; float: right">
                            <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                   aria-label="Bạn tìm gì..." name="search">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="submit">Search</button>
                            </div>
                        </div>
                    </form>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example" class="display" style="color:black; width: 2900px ">
                            <thead>
                            <tr>
                                <th>Mã SP</th>
                                <th>Ngày tạo</th>
                                <th>Ngày cập nhật</th>
                                <th>Tên SP</th>
                                <th>Hãng</th>
                                <th>Màn hình</th>
                                <th>Camera</th>
                                <th>Thông số bluetooth</th>
                                <th>Hỗ trợ mạng</th>
                                <th>Cổng giao tiếp</th>
                                <th>Thông số wifi</th>
                                <th>Kich thước</th>
                                <th>Trọng lượng</th>
                                <th>Chất liệu máy</th>
                                <th>Hệ điều hành</th>
                                <th>Số khe sim</th>
                                <th>Tình trạng</th>
                                <th>Mô tả</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <i class="mdi mdi-border-color"></i>
                            <tbody>
                            <c:forEach items="${hsp}" var="list" varStatus="i">
                                <tr>
                                    <td>${list.ma}</td>
                                    <td>${list.ngayTao}</td>
                                    <td>${list.ngayCapNhat}</td>
                                    <td>${list.ten}</td>
                                    <td>${list.hangSanPham.ten}</td>
                                    <td>${list.manHinh.thongSo}</td>
                                    <td>${list.camera.thongSo}</td>
                                    <td>${list.bluetooth}</td>
                                    <td>${list.hoTroMang}</td>
                                    <td>${list.congGiaoTiep}</td>
                                    <td>${list.thongSoWifi}</td>
                                    <td>${list.kichThuoc}</td>
                                    <td>${list.trongLuong}</td>
                                    <td>${list.chatLieu}</td>
                                    <td>${list.heDieuHanh}</td>
                                    <td>${list.soSim}</td>

                                    <td><c:if test="${list.tinhTrang==0}">Hoạt động</c:if>
                                        <c:if test="${list.tinhTrang==1}">Ngừng hoạt động</c:if>
                                    </td>
                                    <td>${list.moTa}</td>
                                    <td>
                                        <a href="/san-pham/detail/${list.id}" class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Update</a>
                                        <a href="/san-pham/delete/${list.id}" class="btn btn-danger btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-reload btn-icon-prepend"></i>
                                            Status</a>
                                        <a href="/san-pham/them-vao-ctsp/${list.id}"
                                           class="btn btn-success btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">

                                            Thêm vào CTSP</a>

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
<c:if test="${thongBaoCTSP1 != null}">
    <div id="modalError" class="modal fade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="swal2-icon swal2-error swal2-animate-error-icon" style="display: block;">
                                    <span class="swal2-x-mark swal2-animate-x-mark"><span
                                            class="swal2-x-mark-line-left"></span><span
                                            class="swal2-x-mark-line-right"></span></span></div>
                            <h4 style="color: red;margin: 10px;text-align: center">${thongBaoCTSP1}</h4>
                        </div>
                        <div class="col-12" style="text-align: center;margin-top: 20px">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
                                Xác nhận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${thongBaoCTSP != null}">
    <div id="modalSuccess" class="modal fade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-12">
                            <div class="swal2-icon swal2-success swal2-animate-success-icon"
                                 style="display: block;">
                                <div class="swal2-success-circular-line-left"
                                     style="background: rgb(255, 255, 255);"></div>
                                <span class="swal2-success-line-tip swal2-animate-success-line-tip"></span> <span
                                    class="swal2-success-line-long swal2-animate-success-line-long"></span>
                                <div class="swal2-success-ring"></div>
                                <div class="swal2-success-fix" style="background: rgb(255, 255, 255);"></div>
                                <div class="swal2-success-circular-line-right"
                                     style="background: rgb(255, 255, 255);"></div>
                            </div>
                            <h4 style="color: #10ae05;margin: 10px;text-align: center">${thongBaoCTSP}</h4>
                        </div>
                        <div class="col-12" style="text-align: center;margin-top: 20px">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
                                Xác nhận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
</body>
<script>
    $(document).ready(function () {
        $('#modalSuccess').modal('show');
    });
</script>
<script>
    $(document).ready(function () {
        $('#modalError').modal('show');
    });
</script>
</html>
