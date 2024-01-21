<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>GPhoneS Store </title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>

</head>
<body>
<section style="text-align: center">
    <div class="row outer-border border border-secondary" style="height: 47px; background-color: #f5f5f5">
        <div class="col-1">
            <form action="/ban-hang/add-hoa-don" method="post" style="text-align: center;margin-top: 6px">
                <button type="submit"><img src="/uploads/plus.png"></button>
            </form>
        </div>
        <div class="col-11">
            <div class="row">
                <c:forEach items="${listHoaDon}" var="hd" varStatus="i">
                    <div class="col-2">
                        <div class="btn-group ">
                            <a href="/ban-hang/thong-tin-hoa-don/${hd.id}" class="btn btn-info"
                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                               style="width: 120%;height: 47px"><label style="margin: 4px">Hóa Đơn
                                Chờ ${i.index+1}</label></a>
                            <a href="/ban-hang/remove/${hd.id}" class="btn btn-danger" style="width: 10px;height: 47px"
                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                <label style="padding-top:5px;padding-right: 20px;margin-right: 20px"
                                >X</label>
                            </a>
                        </div>
                    </div>
                </c:forEach>
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
                            <div class="col-12" style="align-items: center;margin-top: 20px">
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
                            <div class="col-12" style="align-items: center;margin-top: 20px">
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
    <br>
    <c:if test="${HoaDon!=null}">
        <div class="ban-hang" style="align-items: center">
            <video
                    style="border: 1px solid"
                    id="video"
                    autoplay="true"
                    width="200px"
                    height="120px"
            ></video>
        </div>
        <div class="row">
            <div class="col-12">
                <a href="/ban-hang/modal-san-pham"
                   class="btn btn-secondary"
                   data-bs-toggle="modal"
                   data-bs-target="#newSanPham">Thêm sản phẩm</a>
            </div>
        </div>
        <br>
        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card shadow p-3 mb-5 bg-body-tertiary rounded">
                <div class="card-body">
                    <h4 class="card-title" style="float: left">Danh sách Hóa đơn chi tiết</h4>
                        <%--            Tìm kiếm               --%>
                    <div class="row">
                        <div class="col-8"><strong
                                style="float: right;color:red;margin-top: 10px">${thongBaoHoaDonChiTiet}</strong></div>
                        <div class="col-4">
                            <div class="input-group" style="width: 100%; float: right">
                                <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                       aria-label="Bạn tìm gì..." name="search-hoa-don-chi-tiet"
                                       id="search-hoa-don-chi-tiet">
                                <div class="input-group-append">
                                    <button class="btn btn-sm btn-primary" type="button"
                                            id="button-search-hoa-don-chi-tiet">Search
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                        <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example2" class="display" style="color: black">
                            <thead>
                            <tr>
                                <th>Ảnh</th>
                                <th>Tên Sản Phẩm</th>
                                <th>Hãng</th>
                                <th>Màu Sắc</th>
                                <th>Ram</th>
                                <th>Dung Lượng Bộ Nhớ</th>
                                <th>Số IMEI</th>
                                <th>Đơn Giá</th>
                                <th>Thành tiền</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="table-search-hoa-don-chi-tiet">
                            <c:forEach items="${listHoaDonChiTiet}" var="hdct" varStatus="i">
                                <tr>
                                    <td align="center">
                                        <img src="/uploads/${hdct.imei.chiTietSanPham.urlAnh}" width="40" height="40">
                                    </td>
                                    <td>${hdct.imei.chiTietSanPham.sanPham.ten}</td>
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
                                    <td>
                                        <button class="btn  btn-icon-text"><a
                                                href="/ban-hang/delete-hoa-don-chi-tiet/${hdct.id}"
                                                style="text-decoration: none;color: black"
                                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                        ><img src="/uploads/delete.png" width="24px" height="24px"></a>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <c:if test="${not listHoaDonChiTiet.isEmpty()}">
            <div class="row">
                <div class="col-12">
                    <form:form action="/ban-hang/xac-nhan/${HoaDon.id}" modelAttribute="HoaDon" method="post">
                        <div class="col-12 grid-margin " style="color: black">
                            <div class="card shadow p-3 mb-5 bg-body-tertiary rounded">
                                <div class="card-body">
                                    <h4 class="card-title">Thông Tin Hóa Đơn</h4>
                                    <form class="form-sample">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label"
                                                                path="ma">Mã Hóa Đơn:</form:label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" path="ma" readonly="true"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label"
                                                                path="nhanVien">Nhân Viên:</form:label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control"
                                                               value="${HoaDon.nhanVien.hoTen}" readonly>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label"
                                                                path="khachHang">Khách Hàng:</form:label>
                                                    <div class="col-sm-9">
                                                        <div class="row">
                                                            <div class="col-10">
                                                                <form:select path="khachHang" class="form-control"
                                                                             id="selectKhachHang">
                                                                    <c:if test="${HoaDon.khachHang== null}">
                                                                        <option selected disabled value="1">
                                                                            Khách hàng
                                                                        </option>
                                                                    </c:if>
                                                                    <c:forEach items="${listKhachHang}" var="kh">
                                                                        <option value="${kh.id}">${kh.hoTen}
                                                                            - ${kh.sdt}</option>
                                                                    </c:forEach>
                                                                </form:select>
                                                                <label id="nullKH1" style="color: red"></label>
                                                            </div>
                                                            <div class="col-2">
                                                                <a href="/ban-hang/modal-khach-hang"
                                                                   class="btn"
                                                                   data-bs-toggle="modal"
                                                                   data-bs-target="#newKhachHang"><img
                                                                        src="/uploads/plus.png" alt=""></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label"
                                                                path="sdt">Số điện thoại:</form:label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" path="sdt" id="sdthd"/>
                                                        <label id="sdthd1" style="color: red"></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label" path="tongTien"
                                                                for="tienCanThanhToan">Tổng tiền:</form:label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" path="tongTien" readonly="true"
                                                                    value="${tong}"
                                                                    id="tienCanThanhToan"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Tiền Khách Gửi:</label>
                                                    <div class="col-sm-9">
                                                        <input class="form-control" type="text" id="tienKhachDua"/>
                                                        <label id="tienKhachDua1" style="color: red"></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label" for="ketQua">Tiền
                                                        Thừa:</label>
                                                    <div class="col-sm-9">
                                                        <input class="form-control" type="text" id="ketQua" readonly/>
                                                        <label id="ketQua1" style="color: red"></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label"
                                                                path="ghiChu">Ghi Chú:</form:label>
                                                    <div class="col-sm-9">
                                                        <form:textarea class="form-control" path="ghiChu"/>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <button type="submit" class="btn btn-primary"
                                                        id="bthd" onclick="return checkhd()">Xác Nhận
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </c:if>
    </c:if>
</section>

<div class="modal fade" id="newKhachHang" tabindex="-1" aria-labelledby="khachHangLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <form:form modelAttribute="modalAddKhachHang"
                           action="/ban-hang/add-khach-hang" method="post">
                    <div class="col-12 grid-margin" style="color:#0b0b0b;">
                        <div class="card">
                            <div class="card-body">
                                <form class="form-sample">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label"
                                                            path="hoTen">Tên khách hàng:
                                                </form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder="" path="hoTen"
                                                                id="tenkh"/>
                                                    <label id="tenkh1" style="color: red"></label>
                                                    <form:errors path="hoTen" cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label"
                                                            path="email">Email:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="email" id="emailkh"/>
                                                    <label id="email1" style="color: red"></label>
                                                    <form:errors path="email"
                                                                 cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label"
                                                            path="sdt">SĐT:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder="" path="sdt"
                                                                id="sdtkh"/>
                                                    <label id="sdtkh1" style="color: red"></label>
                                                    <form:errors path="sdt"
                                                                 cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label"
                                                            path="ngaySinh">Ngày Sinh Khách Hàng:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="ngaySinh" type="date" required="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label"
                                                            path="diem">Điểm:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                type="number"
                                                                path="diem" id="diemkh"/>
                                                    <label id="diem1" style="color: red"></label>
                                                    <form:errors path="diem"
                                                                 cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label"
                                                            path="gioiTinh">Giới Tính:</form:label>
                                                <div class="col-sm-9">
                                                    <div class="form-control">
                                                        <form:radiobutton path="gioiTinh"
                                                                          value="true" checked="true"/>Nam
                                                        <form:radiobutton path="gioiTinh"
                                                                          value="false" cssStyle="margin-left: 2cm"/>Nữ
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label"
                                                            path="taiKhoan" for="tkkh">Tài Khoản:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="taiKhoan" id="tkkh" readonly="true"/>
                                                    <form:errors path="taiKhoan"
                                                                 cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="col-sm-3 col-form-label"
                                                            path="hangKhachHang">Hạng Khách Hàng:</form:label>
                                                <div class="col-sm-9">
                                                    <form:select path="hangKhachHang" class="form-control">
                                                        <form:options items="${listHangKhachHang}"
                                                                      itemLabel="ten"
                                                                      itemValue="id"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div style="text-align: center">
                                                <button type="submit" class="btn btn-primary mr-2"
                                                        id="btkh" onclick="return checkhkh()">
                                                    Thêm khách hàng
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    Close
                </button>
            </div>
        </div>
    </div>
</div>
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
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>--%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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
    function myFunction1() {
        let text = "Bạn chắc chắn muốn thêm";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction2() {
        let text = "Bạn chắc chắn muốn sửa";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction3() {
        let text = "Bạn chắc chắn muốn xóa";
        let kt = confirm(text);
        if (kt == true) {
            confirm("Xóa thành công");
            return true
        } else {
            return false;
        }
    }

    function myFunction4() {
        let text = "Bạn chắc chắn muốn xác nhận hóa đơn không";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    document.getElementById("tienKhachDua").addEventListener("keyup", function () {
        tinhTienThua();
    });

    function tinhTienThua() {
        var tongTien = parseFloat(document.getElementById("tienCanThanhToan").value);
        var tienKhachDua = parseFloat(document.getElementById("tienKhachDua").value);
        var tienThua = tienKhachDua - tongTien;

        var ketQuaElement = document.getElementById("ketQua");
        if (tienThua >= 0) {
            ketQuaElement.value = tienThua.toFixed(2);
        } else {
            ketQuaElement.value = "Khách đưa không đủ tiền.";
        }
    }

    function checkhd() {
        var sdthd = document.getElementById("sdthd").value;
        var tien = document.getElementById("tienKhachDua").value;
        var ketQua = document.getElementById("ketQua").value;
        var nullKH = document.getElementById("selectKhachHang").value;
        var tongTien = document.getElementById("tienCanThanhToan").value;
        var regex = /^0\d{9}$/;

        if (!regex.test(sdthd)) {
            document.getElementById("sdthd1").innerHTML = "SDT phải 10 số và bắt đầu là số 0";
            document.getElementById("bthd").type = "button";
            return false;
        } else {
            document.getElementById("sdthd1").innerHTML = "";
            if (tien.trim() === '') {
                document.getElementById("tienKhachDua1").innerHTML = "Tiền khách đưa không được để trống";
                document.getElementById("bthd").type = "button";
                return false;
            } else {
                if (tien % 1 !== 0 || tien < 0) {
                    document.getElementById('tienKhachDua1').innerHTML = 'Tiền khách đưa phải là số nguyên dương';
                    document.getElementById('bthd').type = 'button';
                    return false
                } else if ((parseFloat(tien) - parseFloat(toString(tongTien))) > 0) {
                    document.getElementById('tienKhachDua1').innerHTML = 'Tiền khách đưa đang quá lớn';
                    document.getElementById('bthd').type = 'button';
                    return false;
                } else {
                    document.getElementById('tienKhachDua1').innerHTML = '';
                    if (ketQua % 1 !== 0 && ketQua < 0 || ketQua == 'Khách đưa không đủ tiền.') {
                        document.getElementById('bthd').type = 'button';
                        return false;
                    } else {
                        if (nullKH == 1) {
                            document.getElementById('nullKH1').innerHTML = 'Chưa chọn khách hàng';
                            document.getElementById('bthd').type = 'button';
                            return false;
                        } else {
                            document.getElementById('nullKH1').innerHTML = '';
                            document.getElementById('bthd').type = 'summit';
                            return true;
                        }
                    }

                }
            }
        }

    }


    document.getElementById("emailkh").addEventListener("keyup", function () {
        taiKhoan();
    });

    function taiKhoan() {
        var email = document.getElementById("emailkh").value;
        var tk = email;

        var ketQuaElement = document.getElementById("tkkh");
        if (email == '') {
            ketQuaElement.value = "Hãy nhập email";
        } else {
            ketQuaElement.value = tk;
        }
    }

    function checkhkh() {
        var tenhkh = document.getElementById("tenkh").value;
        var sdtkh = document.getElementById("sdtkh").value;
        var email = document.getElementById("emailkh").value;
        var diem = document.getElementById("diemkh").value;

        if (
            tenhkh.trim().length < 6 || tenhkh == ''
        ) {
            document.getElementById("btkh").type = "button";
            document.getElementById("tenkh1").innerHTML = "Không để trống ,Tên ít nhất 6 ký tự";
            return false;
        } else {
            document.getElementById("tenkh1").innerHTML = "";
            var regex = /^0\d{9}$/;
            var regexemail = /^([a-z0-9_.-]+)@([\da-z.-]+).([a-z.]{2,6})$/

            if (!regex.test(sdtkh)) {
                document.getElementById("sdtkh1").innerHTML = "SDT phải 10 số và bắt đầu là số 0";
                document.getElementById("btkh").type = "button";
                return false;
            } else {
                document.getElementById("sdtkh1").innerHTML = "";
                if (email == '') {
                    document.getElementById("email1").innerHTML = "email không để trống";
                    document.getElementById("btkh").type = "button";
                    return false;
                } else if (!regexemail.test(email)) {
                    document.getElementById("email1").innerHTML = "email chưa đúng";
                    document.getElementById("btkh").type = "button";
                    return false;
                } else {
                    document.getElementById("email1").innerHTML = "";
                    if (diem == '') {
                        document.getElementById("btkh").type = "button";
                        document.getElementById("diem1").innerHTML = "Điểm ít nhất là 1";
                        return false;
                    } else {
                        document.getElementById("diem1").innerHTML = "";
                        document.getElementById("btkh").type = "submit";
                        return true;
                    }
                }
            }
        }
    }
</script>
<script>
    $('div[id^="nhapImei_"]').on('show.bs.modal', async function (e) {
        const ctspId = e.currentTarget.id.split("_")[1];
        const url = "http://localhost:8080/ban-hang/them-san-pham/" + ctspId;
        console.log(ctspId, url);
        try {
            const resp = await fetch(url);
            const data = await resp.json();
            console.log(data)
            let html = '';
            for (let i = 0; i < data.length; i++) {
                const imei = data[i];
                const tr = `
            <tr>
                <td>` + (i + 1) + `</td>
                <td>` + imei.soImei + `</td>
                <td>` + (imei.tinhTrang == 0 ? "Chưa bán" : "Đã bán") + `</td>
                <td><a href="/ban-hang/them-imei/` + imei.id + `">Thêm IMEI</a></td>
            </tr>
            `;
                html += tr;
            }

            $("#listImei_" + ctspId).html(html);
        } catch (err) {
            console.error(err)
        }
    });
</script>
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
                            <td><a class="btn btn-warning btn-icon-text" href="/ban-hang/them-imei/` + imei.id + `">Thêm IMEI</a></td>
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
                let productPrice;
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
<script>
    $('button[id^="button-search-hoa-don-chi-tiet"]').on('click', async function (e) {
        const btn = $(this);
        const search = $("#search-hoa-don-chi-tiet").val();
        const url = "http://localhost:8080/ban-hang/search-hoa-don-chi-tiet?search-hoa-don-chi-tiet=" + search;
        try {
            const resp = await fetch(url);
            const data = await resp.json();
            console.log(data)
            // Hiển thị dữ liệu tìm kiếm
            let html = ``;
            for (let i = 0; i < data.length; i++) {
                const hdct = data[i];
                const tr = `
            <tr>
                <td align="center"><img src="/uploads/` + hdct.imei.chiTietSanPham.urlAnh + `" width="40" height="40"></td>
                <td>` + hdct.imei.chiTietSanPham.sanPham.ten + `</td>
                <td>` + hdct.imei.chiTietSanPham.sanPham.hangSanPham.ten + `</td>
                <td>` + hdct.imei.chiTietSanPham.mauSac.ten + `</td>
                <td>` + hdct.imei.chiTietSanPham.ram.dungLuong + `</td>
                <td>` + hdct.imei.chiTietSanPham.rom.dungLuong + `</td>
                <td>` + hdct.imei.soImei + `</td>
                <td>` + hdct.donGia + `</td>
                <td>` + hdct.donGia + `</td>
                <td>` + hdct.donGia * hdct.soLuong + `</td>
                <td>
                    <button class="btn  btn-icon-text"><a
                    href="/ban-hang/delete-hoa-don-chi-tiet/` + hdct.id + `"
                    style="text-decoration: none;color: black"
                    onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                    ><img src="/uploads/delete.png" width="24px" height="24px"></a>
                    </button>
                </td>
            </tr>
            `;
                html += tr;
            }
            $("#table-search-hoa-don-chi-tiet").html(html);
        } catch (err) {
            console.error(err)
        }
    });
</script>

<script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
<script src="../../js/scan-qr.js"></script>
</html>
