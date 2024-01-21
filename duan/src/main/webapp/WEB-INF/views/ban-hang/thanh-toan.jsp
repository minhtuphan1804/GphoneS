<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>


    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>GPhoneS Store</title>
    <link rel="stylesheet" href="../../../vendor/toastr/css/toastr.min.css">
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/ban-hang/thong-tin-hoa-don/${HoaDon.id}" role="tab">Quay lại</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thông tin hóa đơn</a>
        </li>
    </ul>
</div>
<div>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">
            <div>
                <div class="col-12 grid-margin">
                    <div class="card">
                        <div class="card-body">
                            <form:form action="/ban-hang/thanh-toan/${HoaDon.id}" modelAttribute="HoaDon" method="post">
                                <div class="col-12 grid-margin" style="color: black">
                                    <div class="card">
                                        <div class="card-body">
                                            <h4 class="card-title">Thông Tin Hóa Đơn</h4>
                                            <form class="form-sample">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group row">
                                                            <form:label class="col-sm-3 col-form-label"
                                                                        path="nhanVien">Nhân Viên:</form:label>
                                                            <div class="col-sm-9">
                                                                <form:select path="nhanVien"
                                                                             class="form-control">
                                                                    <form:options items="${listNhanVien}"
                                                                                  itemLabel="hoTen" itemValue="id"/>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group row">
                                                            <form:label class="col-sm-3 col-form-label"
                                                                        path="khachHang">Khách Hàng:</form:label>
                                                            <div class="col-sm-9">
                                                                <form:select path="khachHang"
                                                                             class="form-control">
                                                                    <form:options items="${listKhachHang}"
                                                                                  itemLabel="hoTen" itemValue="id"/>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group row">
                                                            <form:label class="col-sm-3 col-form-label"
                                                                        path="nguoiNhan">Người nhận hàng:</form:label>
                                                            <div class="col-sm-9">
                                                                <form:input class="form-control" path="nguoiNhan"
                                                                />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group row">
                                                            <form:label class="col-sm-3 col-form-label"
                                                                        path="diaChi">Địa chỉ:</form:label>
                                                            <div class="col-sm-9">
                                                                <div class="row">
                                                                    <div class="col-10">
                                                                        <form:select path="diaChi"
                                                                                     class="form-control"
                                                                                     id="selectDiaChi">
                                                                            <c:forEach items="${listDiaChi}" var="ht">
                                                                                <option value="${ht.id}">${ht.diaChi}
                                                                                    - ${ht.quan}
                                                                                    - ${ht.huyen}
                                                                                    - ${ht.thanhPho}</option>
                                                                            </c:forEach>
                                                                        </form:select>
                                                                    </div>
                                                                    <div class="col-2">
                                                                        <a
                                                                                data-bs-toggle="modal"
                                                                                data-bs-target="#newDiaChi"><img
                                                                                src="/uploads/plus.png" alt=""></a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group row">
                                                            <form:label class="col-sm-3 col-form-label"
                                                                        path="sdt">Số điện thoại:</form:label>
                                                            <div class="col-sm-9">
                                                                <form:input class="form-control" path="sdt"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group row">
                                                            <form:label class="col-sm-3 col-form-label" path="tongTien"
                                                                        for="tienCanThanhToan">Tổng tiền:</form:label>
                                                            <div class="col-sm-9">
                                                                <form:input class="form-control" path="tongTien" type="money"
                                                                            readonly="true"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group row">
                                                            <form:label class="col-sm-3 col-form-label"
                                                                        path="ghiChu">Ghi Chú:</form:label>
                                                            <div class="col-sm-9">
                                                                <form:textarea class="form-control" path="ghiChu"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group row">
                                                            <form:label class="col-sm-3 col-form-label"
                                                                        path="hinhThucThanhToan">Phương thức thanh toán:</form:label>
                                                            <div class="col-sm-9">
                                                                <form:select class="form-control"
                                                                             path="hinhThucThanhToan">
                                                                    <form:option value="0">Tiền mặt</form:option>
                                                                    <form:option value="1">Chuyển khoản</form:option>
                                                                </form:select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br>
                                                <div class="row">
                                                    <div class="col-12" style="text-align: center">
                                                        <button type="submit" class="btn btn-dark mb-2 "
                                                                id="toastr-success-top-center-thanh-toan">
                                                            Thanh Toán
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
                </div>
                <br>
                <div class="col-lg-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title" style="float: left">Danh sách Hóa đơn chi tiết</h4>
                            <%--            Tìm kiếm               --%>
                            <div class="row">
                                <div class="col-8"><strong
                                        style="float: right;color:red;margin-top: 10px">${thongBaoHoaDonChiTiet}</strong>
                                </div>
                                <div class="col-4">
                                    <form action="/ban-hang/search-hoa-don-chi-tiet-thanh-toan" method="post">
                                        <div class="input-group" style="width: 100%; float: right">
                                            <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                                   aria-label="Bạn tìm gì..." name="search-hoa-don-chi-tiet-thanh-toan">
                                            <div class="input-group-append">
                                                <button class="btn btn-sm btn-primary" type="submit">Search</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>

                            <%--           kết thúc tìm kiếm         --%>
                            <div class="table-responsive">
                                <table class="table table-striped" style="color: black" id="example" class="display">
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
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listHoaDonChiTiet}" var="hdct" varStatus="i">
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
<div class="modal fade" id="newDiaChi"
     tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <form:form
                        modelAttribute="modalAddDiaChi"
                        action="/ban-hang/add-dia-chi"
                        method="post">
                    <div class="card">
                        <div class="card-body">
                            <form class="form-sample">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group row">

                                            <form:label
                                                    class="col-sm-3 col-form-label"
                                                    path="diaChi">Địa Chỉ:<label id="diachi1" style="color: red"></label>
                                            </form:label>
                                            <div class="col-sm-9">
                                                <form:input cssStyle="width: 10cm"
                                                            class="form-control"
                                                            placeholder=""
                                                            path="diaChi"
                                                            id="diachi"/>
                                                <form:errors
                                                        path="diaChi"
                                                        cssStyle="color: red"/>
                                            </div>


                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group row">
                                            <form:label
                                                    class="col-sm-3 col-form-label"
                                                    path="thanhPho">Tỉnh/thành:<label id="thanhPhoo1" style="color: red"></label>
                                            </form:label>
                                            <div class="col-sm-9">
                                                <select name="" id="province" style="width: 10cm">
                                                </select>
                                                <form:input cssStyle="display: none"
                                                            class="form-control"
                                                            placeholder=""
                                                            path="thanhPho"
                                                            id="themdiachidathangthanhpho"
                                                />

                                                <form:errors
                                                        path="thanhPho"
                                                        cssStyle="color: red"/>

                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group row">
                                            <form:label
                                                    class="col-sm-3 col-form-label"
                                                    path="huyen">Quận/Huyện:<label id="huyenn1" style="color: red"></label>
                                            </form:label>
                                            <div class="col-sm-9">
                                                <select name="" id="district" style="width: 10cm">
                                                </select>
                                                <form:input cssStyle="display: none"
                                                            class="form-control"
                                                            placeholder=""
                                                            path="huyen"
                                                            id="themdiachidathangquan"
                                                />

                                                <form:errors
                                                        path="huyen"
                                                        cssStyle="color: red"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group row">

                                            <form:label
                                                    class="col-sm-3 col-form-label"
                                                    path="quan">Phường/xã:<label id="quann1" style="color: red"></label>
                                            </form:label>
                                            <div class="col-sm-9">
                                                <select name="" id="ward" style="width: 10cm">
                                                </select>
                                                <form:input cssStyle="display: none"
                                                            class="form-control"
                                                            placeholder=""
                                                            path="quan"
                                                            id="themdiachidathanghuyen"
                                                />
                                                <form:errors
                                                        path="quan"
                                                        cssStyle="color: red"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div style="text-align: center">
                                            <button type="submit"
                                                    class="btn btn-primary mr-2"
                                                    id="btkh"
                                                    onclick="return checkhkh()">
                                                Thêm
                                                địa
                                                chỉ
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </form:form>


                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
                        integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
                        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.26.1/axios.min.js"
                        integrity="sha512-bPh3uwgU5qEMipS/VOmRqynnMXGGSRv+72H/N260MQeXZIK4PG48401Bsby9Nq5P5fz7hy5UGNmC/W1Z51h2GQ=="
                        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

                <script src="/jsbanhang/API.js"></script>
            </div>


            <div class="modal-footer">
                <button type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal">
                    Close
                </button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script>
    function myFunction4() {
        let text = "Bạn chắc chắn muốn thanh toán hóa đơn không";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function checkhkh() {
        var diaChi = document.getElementById("diachi").value;
        var quann = document.getElementById("themdiachidathanghuyen").value;
        var huyenn = document.getElementById("themdiachidathangquan").value;
        var thanhPhoo = document.getElementById("themdiachidathangthanhpho").value;

        if (
            diaChi == ''
        ) {
            document.getElementById("btkh").type = "button";
            document.getElementById("diachi1").innerHTML = "Không để trống thông tin ";
            return false;
        } else {
            document.getElementById("diachi1").innerHTML = "";
            if (quann == '') {
                document.getElementById("quann1").innerHTML = "Không để trống thông tin";
                document.getElementById("btkh").type = "button";
                return true;
            } else {
                document.getElementById("quann1").innerHTML = "";
                if (huyenn == '') {
                    document.getElementById("huyenn1").innerHTML = "Không để trống thông tin";
                    document.getElementById("btkh").type = "button";
                    return true;
                } else {
                    document.getElementById("huyenn1").innerHTML = "";
                    if (thanhPhoo == '') {
                        document.getElementById("btkh").type = "button";
                        document.getElementById("thanhPhoo1").innerHTML = "Không để trống thông tin";
                        return false;
                    } else {
                        document.getElementById("thanhPhoo1").innerHTML = "";
                        document.getElementById("btkh").type = "submit";
                        return true;
                    }
                }
            }
        }
    }
</script>
<script src="../../../vendor/global/global.min.js"></script>
<script src="../../../js/quixnav-init.js"></script>

<!-- Toastr -->
<script src="../../../vendor/toastr/js/toastr.min.js"></script>

<!-- All init script -->
<script src="../../../js/plugins-init/toastr-init.js"></script>
<%--API--%>

</html>





