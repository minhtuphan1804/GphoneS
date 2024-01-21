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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <style>
        .status-bar {
            display: flex;
            justify-content: center; /* Chuyển thanh trạng thái ra giữa */
            background-color: #DDDDDD;
            padding: 10px;
            border-radius: 50px 50px 50px 50px;
            margin: 0 auto; /* Đưa thanh trạng thái vào giữa trang */
            max-width: 1700px; /* Đặt chiều rộng tối đa của thanh trạng thái */
            border: 1px solid black;
        }

        .status {
            flex: 1;
            text-align: center;
        }

        .pill {
            border: 2px solid blue;
            display: inline-block;
            padding: 8px 16px;
            border-radius: 20px;
            font-size: 12px;
            font-weight: bold;
            color: #000;
            margin-right: 1cm; /* Khoảng cách giữa các viên thuốc */
            background-color: white; /* Màu xám mặc định cho tất cả các trạng thái */
        }

        .status-bar .status:hover .pill.default {
            background-color: #00ff00;
        }

        .pending {
            color: black;
            background-color: greenyellow;
        }

        /* Màu xanh cho trạng thái Đang chờ */
        .confirmed {
            color: black;
            background-color: yellow;
        }

        /* Màu xanh cho trạng thái Đã xác nhận */
        .waiting {
            color: black;
            background-color: yellowgreen;
        }

        /* Màu xanh cho trạng thái Chờ thanh toán */
        .paid {
            color: black;
            background-color: cyan;
        }

        /* Màu xanh cho trạng thái Đã thanh toán */
        .canceled {
            color: black;
            background-color: red;
        }

        /* Màu xanh cho trạng thái Đã hủy */
        .default {
            color: black;
        }
    </style>
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn
                chưa xác nhận</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi-xac-nhan" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn đã
                xác nhận</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi-cho-giao" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn chờ giao hàng</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi-dang-giao" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn đang giao hàng</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi-hoan-tat" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn hoàn tất</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi-da-huy" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn đã hủy</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Xem hóa đơn</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent" style="color: black">
    <div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
        <div class="col-12 grid-margin">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div>
                        <div class="card-body">
                            <div class="container mt-5">
                                <c:if test="${hoaDon.loai==0}">
                                    <div class="status-bar">
                                        <div class="status">
                                            <div class="pill ${hoaDon.tinhTrang == 0 ? 'pending' : 'default'}">Đang
                                                chờ
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${hoaDon.tinhTrang == 1 ? 'confirmed' : 'default'}">Đã xác
                                                nhận
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${hoaDon.tinhTrang == 3 ? 'waiting' : 'default'}">Chờ thanh
                                                toán
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${hoaDon.tinhTrang == 2 ? 'paid' : 'default'}">Đã thanh
                                                toán
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${hoaDon.tinhTrang == 8 ? 'canceled' : 'default'}">Đã hủy
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${hoaDon.loai==1}">
                                    <div class="status-bar">
                                        <div class="status">
                                            <div class="pill ${hoaDon.tinhTrang == 3&& hoaDon.tinhTrangGiaoHang==0 ? 'waiting' : 'default'}">
                                                Chờ thanh
                                                toán
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${hoaDon.tinhTrang == 2&& hoaDon.tinhTrangGiaoHang==0 ? 'paid' : 'default'}">
                                                Đã thanh toán
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${(hoaDon.tinhTrang == 2|| hoaDon.tinhTrang==3) && hoaDon.tinhTrangGiaoHang==1 ? 'pending' : 'default'}">
                                                Chờ giao hàng
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${(hoaDon.tinhTrang == 2|| hoaDon.tinhTrang==3) && hoaDon.tinhTrangGiaoHang==2 ? 'confirmed' : 'default'}">
                                                Đang giao hàng
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${(hoaDon.tinhTrang == 2|| hoaDon.tinhTrang==3) && hoaDon.tinhTrangGiaoHang==3 ? 'paid' : 'default'}">
                                                Giao hàng hoàn tất
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${hoaDon.tinhTrang == 8 || hoaDon.tinhTrangGiaoHang==8? 'canceled' : 'default'}">
                                                Đã hủy
                                            </div>
                                        </div>
                                    </div>
                                </c:if>

                                <br><br>
                                <form:form action="/hoa-don/update-cho-giao/${hoaDon.id}" method="post"
                                           modelAttribute="hoaDon">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Mã đơn hàng:</label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" id="form-control" readonly="true"
                                                                placeholder=""
                                                                path="ma"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Khách hàng đặt:
                                                </label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control"
                                                           value="${hoaDon.khachHang.hoTen}" readonly>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Người nhận:
                                                </label>
                                                <div class="col-sm-9">
                                                    <form:input path="nguoiNhan" type="text" class="form-control"
                                                                value="${hoaDon.khachHang.hoTen}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">SĐT nhận hàng:
                                                </label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="sdt"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Địa chỉ:</label>
                                                <div class="col-sm-9">
                                                    <form:select path="diaChi"
                                                                 class="form-control" id="selectDiaChiDonHang">
                                                        <c:forEach items="${listDiaChi}" var="ht">
                                                            <option value="${ht.id}">${ht.diaChi} - ${ht.quan}
                                                                - ${ht.huyen}
                                                                - ${ht.thanhPho}</option>
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Hình thức thanh toán:</label>
                                                <div class="col-sm-9">
                                                    <input class="form-control" placeholder="" readonly="true"
                                                           value="${hoaDon.hinhThucThanhToan == 0 ? 'Tiền mặt' : 'Chuyển khoản'}"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${hoaDon.loai==1}">
                                        <div class="row">
                                                <%--                                            <div class="col-md-6">--%>
                                                <%--                                                <div class="form-group row">--%>
                                                <%--                                                    <label class="col-sm-3 col-form-label">Phí ship:--%>
                                                <%--                                                    </label>--%>
                                                <%--                                                    <div class="col-sm-9">--%>
                                                <%--                                                        <form:input path="phiShip" type="number" class="form-control"/>--%>
                                                <%--&lt;%&ndash;                                                                    id="phiShipDonHang"&ndash;%&gt;--%>

                                                <%--                                                    </div>--%>
                                                <%--                                                </div>--%>
                                                <%--                                            </div>--%>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Tổng tiền đơn hàng:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" path="tongTien" readonly="true"
                                                                    />
                                                            <%--                                                                    id="tongTienShip"--%>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Ngày ship:
                                                        <div style="color: crimson;float: right"></div>
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" type="date"
                                                                    placeholder=""
                                                                    path="ngayShip" required="true"/>
                                                        <label style="color: red">${thongBao}</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">

                                                <%--                                            <div class="col-md-6">--%>
                                                <%--                                                <div class="form-group row">--%>
                                                <%--                                                    <label class="col-sm-3 col-form-label">Tổng tiền cả ship:--%>
                                                <%--                                                        <div id="tb" style="color: crimson;float: right"></div>--%>
                                                <%--                                                    </label>--%>
                                                <%--                                                    <div class="col-sm-9">--%>
                                                <%--                                                        <form:input class="form-control" id="tongTienShip"--%>
                                                <%--                                                                    path="tongTien" readonly="true"/>--%>
                                                <%--                                                    </div>--%>
                                                <%--                                                </div>--%>
                                                <%--                                            </div>--%>
                                        </div>
                                    </c:if>
                                    <c:if test="${hoaDon.loai==0}">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Tổng tiền đơn hàng:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" id="tongTienShip"
                                                                    path="tongTien" readonly="true"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Trạng thái:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:select path="tinhTrang" class="form-control">
                                                            <form:option value="0">Đang chờ</form:option>
                                                            <form:option value="1">Đã xác nhận</form:option>
                                                            <form:option value="3">Chờ thanh toán</form:option>
                                                            <form:option value="2">Đã thanh toán</form:option>
                                                            <form:option value="8">Đã hủy</form:option>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <br>
                                    <div style="text-align: center">
                                        <button type="submit" class="btn btn-primary mr-2" id="toastr-success-top-right"
                                                id="toastr-success-top-right"
                                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            Cập nhật thông tin
                                        </button>
                                    </div>

                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 grid-margin">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div>
                        <div class="card-body" style="text-align: center">
                            <c:if test="${(hoaDon.tinhTrang==0||hoaDon.tinhTrang==1||hoaDon.tinhTrang==3) && (hoaDon.tinhTrangGiaoHang==1|| hoaDon.tinhTrangGiaoHang==0)}">
                                <a
                                        class="btn btn-secondary"
                                        data-bs-toggle="modal"
                                        data-bs-target="#newSanPham">Thêm sản phẩm</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 grid-margin">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div>
                        <div class="card-body">
                            <h3 style="text-align: center;">Hóa đơn chi tiết</h3>
                            <br>
                            <div class="search">
                                <div class="input-group" style="width: 30%; float: right">
                                    <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                           aria-label="Bạn tìm gì..." name="search" id="search">
                                    <div class="input-group-append">
                                        <button class="btn btn-sm btn-primary" type="button" id="button-search">
                                            Search
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table id="example2" class="display">
                                    <thead>
                                    <tr>
                                        <th>Tên Sản Phẩm</th>
                                        <th>Ảnh</th>
                                        <th>Hãng</th>
                                        <th>Màu Sắc</th>
                                        <th>Ram</th>
                                        <th>Dung Lượng Bộ Nhớ</th>
                                        <th>Số IMEI</th>
                                        <th>Đơn Giá</th>
                                        <th>Thành tiền</th>
                                        <c:if test="${(hoaDon.tinhTrang==0 || hoaDon.tinhTrang==1 || hoaDon.tinhTrang==3) && (hoaDon.tinhTrangGiaoHang==1 || hoaDon.tinhTrangGiaoHang==0) && listHoaDonChiTiet.size()>1}">
                                            <th></th>
                                        </c:if>

                                    </tr>
                                    </thead>
                                    <tbody id="table-search">
                                    <i class="mdi mdi-border-color"></i>
                                    <c:forEach items="${listHoaDonChiTiet}" var="hdct">
                                        <tr>
                                            <td>${hdct.imei.chiTietSanPham.sanPham.ten}</td>
                                            <td align="center">
                                                <img src="/uploads/${hdct.imei.chiTietSanPham.urlAnh}" width="40"
                                                     height="40">
                                            </td>
                                            <td>${hdct.imei.chiTietSanPham.sanPham.hangSanPham.ten}</td>
                                            <td>${hdct.imei.chiTietSanPham.mauSac.ten}</td>
                                            <td>${hdct.imei.chiTietSanPham.ram.dungLuong}</td>
                                            <td>${hdct.imei.chiTietSanPham.rom.dungLuong}</td>
                                            <td>${hdct.imei.soImei}</td>
                                            <td>
                                                <script>
                                                    var donGia = ${hdct.donGia};
                                                    document.write(donGia.toLocaleString('vi-VN'));
                                                </script>
                                                VND
                                            </td>

                                            <td>
                                                <script>
                                                    var tongTien = ${hdct.donGia * hdct.soLuong};
                                                    document.write(tongTien.toLocaleString('vi-VN'));
                                                </script>
                                                VND
                                            </td>
                                            <c:if test="${(hoaDon.tinhTrang==0 || hoaDon.tinhTrang==1 || hoaDon.tinhTrang==3) && (hoaDon.tinhTrangGiaoHang==1 || hoaDon.tinhTrangGiaoHang==0)&& listHoaDonChiTiet.size()>1}">
                                                <td>
                                                    <button class="btn  btn-icon-text"><a
                                                            href="/hoa-don/delete-hoa-don-chi-tiet/${hdct.id}"
                                                            onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                                    ><img src="/uploads/delete.png" width="24px" height="24px"></a>
                                                    </button>
                                                </td>
                                            </c:if>
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
    </div>
</div>
<c:if test="${thongBaoHoaDon != null}">
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
                            <h4 style="color: red;margin: 10px;text-align: center">${thongBaoHoaDon}</h4>
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
<c:if test="${thongBaoHoaDon1 != null}">
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
                            <h4 style="color: #10ae05;margin: 10px;text-align: center">${thongBaoHoaDon1}</h4>
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
<div class="modal fade" id="newSanPham" tabindex="-1" aria-labelledby="modal-1-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl">
        <div class="modal-content">
            <div class="modal-body">
                <div class="col-lg-12 grid-margin stretch-card">
                    <div class="card">
                        <div>
                            <div class="card-body">
                                <h4 class="card-title" style="float: left">Sản phẩm</h4>
                                <%--            Tìm kiếm               --%>
                                <div class="input-group"
                                     style="width: 30%; float: right">
                                    <button class="btn btn-sm btn-primary"
                                            type="button" data-bs-toggle="modal"
                                            data-bs-target="#qrCam">Quét QR
                                    </button>
                                    <input type="text"
                                           class="form-control"
                                           name="search-imei"
                                           id="imeiSearchInput"
                                           placeholder="Tìm kiếm IMEI">
                                    <div class="input-group-append">
                                        <button class="btn btn-sm btn-primary"
                                                type="button"
                                                id="searchImei"
                                        >Tìm kiếm
                                        </button>
                                    </div>
                                </div>
                                <br>
                                <br>
                                <%--           kết thúc tìm kiếm         --%>
                                <div class="table-responsive">
                                    <div>
                                        <table style="min-width: 1200px; color: black;text-align: center">
                                            <thead>
                                            <tr>
                                                <th>Tên Sản Phẩm</th>
                                                <th>Ảnh</th>
                                                <th>Hãng</th>
                                                <th>Màu Sắc</th>
                                                <th>Ram</th>
                                                <th>Bộ Nhớ</th>
                                                <th>Đơn Giá Gốc</th>
                                                <th>Đơn Giá Khuyến Mãi</th>
                                                <th>Số IMEI</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody class="san_pham_search" style="text-align: center"
                                                   id="banglocthaydoi">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="qrCam" tabindex="-1" aria-labelledby="modal-1-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-body">
                <div class="ban-hang d-flex justify-content-center align-items-center">
                    <video
                            style="border: 1px solid"
                            id="video"
                            autoplay="true"
                            width="200px"
                            height="120px"
                    ></video>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-bs-toggle="modal"
                        data-bs-target="#newSanPham">
                    Quay về
                </button>
                <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">
                    Close
                </button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        $('#modalError').modal('show');
    });
</script>
<script>
    $(document).ready(function () {
        $('#modalSuccess').modal('show');
    });
</script>
<script>

    document.getElementById("phiShipDonHang").addEventListener("keyup", function () {
        tinhTienThua();
    });

    function tinhTienThua() {
        var tongTien = parseFloat(document.getElementById("tongTienDonHang").value);
        var tienKhachDua = parseFloat(document.getElementById("phiShipDonHang").value);
        var check = document.getElementById("phiShipDonHang").value;
        var tien = tienKhachDua + tongTien;

        var ketQuaElement = document.getElementById("tongTienShip");
        if (check.trim() == '') {
            ketQuaElement.value = tongTien.toFixed(2);
        } else {
            ketQuaElement.value = tien.toFixed(2);
        }

    }
</script>
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

</script>
<script>
    $('button[id^="button-search"]').on('click', async function (e) {
            const btn = $(this);
            const search = $("#search").val();
            const url = "http://localhost:8080/hoa-don/search-hdct-update?search=" + search;
            try {
                const resp = await fetch(url);
                const data = await resp.json();
                console.log(data)
                // Hiển thị dữ liệu tìm kiếm
                let html = ``;
                for (let i = 0; i < data.length; i++) {
                    const hdct = data[i];
                    if ((hdct.hoaDon.tinhTrang == 0 || hdct.hoaDon.tinhTrang == 1 || hdct.hoaDon.tinhTrang == 3) && (hdct.hoaDon.tinhTrangGiaoHang == 1 || hdct.hoaDon.tinhTrangGiaoHang == 0) && data.length > 1) {
                        const tr = `
                        <tr>
                            <td>` + hdct.imei.chiTietSanPham.sanPham.ten + `</td>
                            <td align="center"><img src="/uploads/` + hdct.imei.chiTietSanPham.urlAnh + `" width="40" height="40"></td>
                            <td>` + hdct.imei.chiTietSanPham.sanPham.hangSanPham.ten + `</td>
                            <td>` + hdct.imei.chiTietSanPham.mauSac.ten + `</td>
                            <td>` + hdct.imei.chiTietSanPham.ram.dungLuong + `</td>
                            <td>` + hdct.imei.chiTietSanPham.rom.dungLuong + `</td>
                            <td>` + hdct.imei.soImei + `</td>
                            <td>` + new Intl.NumberFormat("vi-VN").format(hdct.donGia) + ` VND</td>
                            <td>` + new Intl.NumberFormat("vi-VN").format(hdct.donGia * hdct.soLuong) + ` VND</td>
                            <td>
                                <button class="btn  btn-icon-text"><a
                                href="/hoa-don/delete-hoa-don-chi-tiet/` + hdct.id + `"
                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                <img src="/uploads/delete.png" width="24px" height="24px"></a>
                                </button>
                            </td>
                        </tr>
                    `;
                        html += tr;
                    } else {
                        const tr = `
                        <tr>
                            <td>` + hdct.imei.chiTietSanPham.sanPham.ten + `</td>
                            <td align="center"><img src="/uploads/` + hdct.imei.chiTietSanPham.urlAnh + `" width="40" height="40"></td>
                            <td>` + hdct.imei.chiTietSanPham.sanPham.hangSanPham.ten + `</td>
                            <td>` + hdct.imei.chiTietSanPham.mauSac.ten + `</td>
                            <td>` + hdct.imei.chiTietSanPham.ram.dungLuong + `</td>
                            <td>` + hdct.imei.chiTietSanPham.rom.dungLuong + `</td>
                            <td>` + hdct.imei.soImei + `</td>
                            <td>` + new Intl.NumberFormat("vi-VN").format(hdct.donGia) + ` VND</td>
                            <td>` + new Intl.NumberFormat("vi-VN").format(hdct.donGia * hdct.soLuong) + ` VND</td>
                        </tr>
                        `;
                        html += tr;
                    }
                }
                $("#table-search").html(html);
            } catch
                (err) {
                console.error(err)
            }
        }
    );
</script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    $('button[id^="searchImei"]').on('click', async function (e) {
        const btn = $(this);
        const parentModal = btn.closest('.modal'); // Lấy modal cha gần nhất của nút "Tìm kiếm" được nhấn
        const search = parentModal.find("#imeiSearchInput").val();
        const idCTSPInputElement = $(document).find('#idCTSPham').val();
        const url = "http://localhost:8080/ban-hang/search-imei?search-imei=" + search;
        console.log(idCTSPInputElement)
        console.log(url)
        if (search === "") {
            let html = `
                <br>
                <tr>
                    <td colspan="10" style="text-align: center; color: red"><strong>Vui lòng nhập IMEI trước khi tìm kiếm.</strong></td>
                </tr>
                <br>`;
            parentModal.find(".san_pham_search").html(html);
            return;
        }
        try {
            const resp = await fetch(url);
            const data = await resp.json();
            console.log(data)
            if (data.length === 0) {
                let html = `
                <br>
                <tr>
                    <td colspan="10" style="text-align: center; color: red"><strong>IMEI đang chờ xử lý hoặc đã bán!</strong></td>
                </tr>
                <br>`;
                parentModal.find(".san_pham_search").html(html);
            } else {
                // Hiển thị dữ liệu tìm kiếm
                let html = ``;
                for (let i = 0; i < data.length; i++) {
                    const imei = data[i];
                    if (imei.chiTietSanPham.khuyenMai) {
                        productPrice = imei.chiTietSanPham.giaBan - imei.chiTietSanPham.giaBan * imei.chiTietSanPham.khuyenMai.soTienGiam / 100;
                    } else {
                        productPrice = imei.chiTietSanPham.giaBan;
                    }
                    const tr = `
                        <br>
                        <tr>
                            <td>` + imei.chiTietSanPham.sanPham.ten + `</td>
                            <td align="center"><img src="/uploads/` + imei.chiTietSanPham.urlAnh + `" width="40" height="40"></td>
                            <td>` + imei.chiTietSanPham.sanPham.hangSanPham.ten + `</td>
                            <td>` + imei.chiTietSanPham.mauSac.ten + `</td>
                            <td>` + imei.chiTietSanPham.ram.dungLuong + `</td>
                            <td>` + imei.chiTietSanPham.rom.dungLuong + `</td>
                            <td>` + new Intl.NumberFormat("vi-VN").format(imei.chiTietSanPham.giaBan) + ` VND</td>
                            <td>` + new Intl.NumberFormat("vi-VN").format(productPrice) + ` VND</td>
                            <td>` + imei.soImei + `</td>
                            <td><a class="btn btn-warning btn-icon-text" href="/hoa-don/them-imei/` + imei.id + `">Thêm IMEI</a></td>
                        </tr>
                        <br>
                        `;
                    html += tr;
                }
                parentModal.find(".san_pham_search").html(html);
            }
        } catch (err) {
            console.error(err)
        }
    });
</script>
<script>
    $('button[id^="searchSanPham"]').on('click', async function (e) {
        const btn = $(this);
        const parentModal = btn.closest('.modal'); // Lấy modal cha gần nhất của nút "Tìm kiếm" được nhấn
        const search = parentModal.find("#sanPhamSearchInput").val();
        const url = "http://localhost:8080/ban-hang/search-san-pham?search-san-pham=" + search;
        try {
            const resp = await fetch(url);
            const data = await resp.json();
            console.log(data)
            // Hiển thị dữ liệu tìm kiếm
            let html = ``;
            for (let i = 0; i < data.length; i++) {
                const ctsp = data[i];
                if (ctsp.khuyenMai) {
                    productPrice = ctsp.giaBan - ctsp.giaBan * ctsp.khuyenMai.soTienGiam / 100;
                } else {
                    productPrice = ctsp.giaBan;
                }
                const tr = `
            <tr>
                <td style="display:none;">` + ctsp.id + `</td>
                <td>` + ctsp.sanPham.ten + `</td>
                <td align="center"><img src="/uploads/` + ctsp.urlAnh + `" width="40" height="40"></td>
                <td>` + ctsp.sanPham.hangSanPham.ten + `</td>
                <td>` + ctsp.mauSac.ten + `</td>
                <td>` + ctsp.ram.dungLuong + `</td>
                <td>` + ctsp.rom.dungLuong + `</td>
                <td>` + productPrice + `</td>
                <td>` + ctsp.soLuong + `</td>
                <td>
                    <a class="btn btn-warning btn-icon-text"
                    data-bs-toggle="modal" data-bs-target="#nhapImei">Nhập IMEI</a>
                </td>
            </tr>
            `;
                html += tr;
            }
            parentModal.find(".san_pham_search").html(html);
            const aTags = document.querySelectorAll('.btn-warning.btn-icon-text');

            aTags.forEach(aTag => {
                aTag.addEventListener('click', () => {
                    // Lấy ID của sản phẩm
                    const productId = aTag.closest('tr').querySelector('td:first-child').textContent;

                    // Lưu ID của sản phẩm vào input
                    const input = document.querySelector('#idCTSPham');
                    input.value = productId;
                });
            });

        } catch (err) {
            console.error(err)
        }
    });
</script>
<script>
    function loadInterface(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('banglocthaydoi');
                content.innerHTML = data;

                loadScripts();
                const aTags = document.querySelectorAll('.btn-warning.btn-icon-text');

                aTags.forEach(aTag => {
                    aTag.addEventListener('click', () => {
                        // Lấy ID của sản phẩm
                        const productId = aTag.closest('tr').querySelector('td:first-child').textContent;

                        // Lưu ID của sản phẩm vào input
                        const input = document.querySelector('#idCTSPham');
                        input.value = productId;
                    });
                });
                // loadSelect2();
            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
    }

    function loadScripts() {
        const scriptsToLoad = [
            '../../../vendor/datatables/js/jquery.dataTables.min.js',
            '../../../js/plugins-init/datatables.init.js'


        ];

        const head = document.head || document.getElementsByTagName('head')[0];

        function loadScript(index) {
            if (index < scriptsToLoad.length) {
                const script = document.createElement('script');
                script.src = scriptsToLoad[index];
                script.onload = function () {
                    loadScript(index + 1);
                };
                head.appendChild(script);
                // loadSelect2();
            }
        }

        // Bắt đầu quá trình tải script
        loadScript(0);
    }

    function clickcombobox() {
        var x1 = encodeURIComponent(document.getElementById("hangds1").value);
        var x2 = encodeURIComponent(document.getElementById("camds1").value);
        var x3 = encodeURIComponent(document.getElementById("mands1").value);
        var x4 = encodeURIComponent(document.getElementById("mauds1").value);
        var x5 = encodeURIComponent(document.getElementById("ramds1").value);
        var x6 = encodeURIComponent(document.getElementById("romds1").value);
        // var x7 = encodeURIComponent(document.getElementById("pinds1").value);
        var x8 = encodeURIComponent(document.getElementById("dungds1").value);
        var x9 = encodeURIComponent(document.getElementById("chipds1").value);
        var x10 = encodeURIComponent(document.getElementById("sands1").value);

        var link = '/ban-hang/loc/ban-hang-tai-quay/' + x1 + '/' + x2 + '/' + x3 + '/' + x4 + '/' + x5 + '/' + x6 + '/' + 'null' + '/' + x8 + '/' + x9 + '/' + x10;
        // document.getElementById("vt").innerHTML=link
        loadInterface(link);
        // document.getElementById("demo").innerHTML = "You selected: " + x;
    }


    function loadSelect2() {
        // Gọi .select2() cho các phần tử sau khi tất cả các tệp script đã được nạp
        $('#hangds1').select2({
            theme: 'bootstrap-5'
        });
        $('#camds1').select2({
            theme: 'bootstrap-5'
        });
        $('#mands1').select2({
            theme: 'bootstrap-5'
        });
        $('#mauds1').select2({
            theme: 'bootstrap-5'
        });
        $('#ramds1').select2({
            theme: 'bootstrap-5'
        });
        $('#romds1').select2({
            theme: 'bootstrap-5'
        });

        $('#pinds1').select2({
            theme: 'bootstrap-5'
        });
        $('#dungds1').select2({
            theme: 'bootstrap-5'
        });

        $('#chipds1').select2({
            theme: 'bootstrap-5'
        });

        $('#sands1').select2({
            theme: 'bootstrap-5'
        });

        // Gọi .select2() cho các phần tử khác ở đây (tương tự)
    }

    loadSelect2();
</script>
<script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
<script src="../../js/scan-hoa-don.js"></script>
</html>
