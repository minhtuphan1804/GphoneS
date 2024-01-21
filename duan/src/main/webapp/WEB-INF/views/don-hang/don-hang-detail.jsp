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
            <a href="/don-hang/hien-thi" class="nav-link"
               tabindex="-1"
               role="button">
                Trang Hóa đơn</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Xem đơn hàng</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
        <div class="col-12 grid-margin">
            <h3 style="text-align: center">Hóa đơn</h3>
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div>
                        <div class="card-body">
                            <div>
                                <div class="container mt-5" style="color: black">
                                    <div class="status-bar">
                                        <%--                                    <div class="status">--%>
                                        <%--                                        <div class="pill ${donHang.tinhTrang == 0 ? 'pending' : 'default'}">Đang chờ--%>
                                        <%--                                        </div>--%>
                                        <%--                                    </div>--%>
                                        <%--                                    <div class="status">--%>
                                        <%--                                        <div class="pill ${donHang.tinhTrang == 1 ? 'confirmed' : 'default'}">Đã xác--%>
                                        <%--                                            nhận--%>
                                        <%--                                        </div>--%>
                                        <%--                                    </div>--%>
                                        <div class="status">
                                            <div class="pill ${donHang.tinhTrang == 3&& donHang.tinhTrangGiaoHang==0 ? 'waiting' : 'default'}">
                                                Chờ thanh
                                                toán
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${donHang.tinhTrang == 2&& donHang.tinhTrangGiaoHang==0 ? 'paid' : 'default'}">
                                                Đã thanh toán
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${(donHang.tinhTrang == 2|| donHang.tinhTrang==3) && donHang.tinhTrangGiaoHang==1 ? 'pending' : 'default'}">
                                                Chờ giao hàng
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${(donHang.tinhTrang == 2|| donHang.tinhTrang==3) && donHang.tinhTrangGiaoHang==2 ? 'confirmed' : 'default'}">
                                                Đang giao hàng
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${(donHang.tinhTrang == 2|| donHang.tinhTrang==3) && donHang.tinhTrangGiaoHang==3 ? 'paid' : 'default'}">
                                                Giao hàng hoàn tất
                                            </div>
                                        </div>
                                        <div class="status">
                                            <div class="pill ${donHang.tinhTrang == 8 || donHang.tinhTrangGiaoHang==8? 'canceled' : 'default'}">
                                                Đã hủy
                                            </div>
                                        </div>
                                    </div>
                                    <br><br>
                                    <form:form action="/hoa-don/detail/${donHang.id}" method="get"
                                               modelAttribute="donHang">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Mã:</label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" id="form-control"
                                                                    readonly="true"
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
                                                        <input type="text" class="form-control" id="khachHangInput"
                                                               name="khachHang"
                                                               value="${donHang.khachHang.hoTen}" readonly>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Nhân viên:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" id="nhanVienInput"
                                                               name="nhanVien"
                                                               value="${donHang.nhanVien.hoTen}" readonly>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">SĐT nhận hàng:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder="" readonly="true"
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
                                                        <input type="text" class="form-control" id="diaChiInput"
                                                               name="diaChi"
                                                               value="${donHang.diaChi.diaChi}" readonly>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Hình thức TT:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder="" readonly="true"
                                                                    path="hinhThucThanhToan"
                                                                    value="${donHang.hinhThucThanhToan == 0 ? 'Tiền mặt' : 'Chuyển khoản'}"
                                                        />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Phí ship:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input path="phiShip" type="number" class="form-control"
                                                                    id="phiShipDonHang"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Người nhận:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input path="nguoiNhan" type="text" class="form-control"
                                                                    readonly="true"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Tổng tiền:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder="" readonly="true"
                                                                    path="tongTien"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Ngày tạo:
                                                        <div id="tb" style="color: crimson;float: right"></div>
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input readonly="true" class="form-control" type="date"
                                                                    value="${donHang.ngayTao}"
                                                                    placeholder=""
                                                                    path="ngayTao"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Ngày nhận:
                                                        <div style="color: crimson;float: right"></div>
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input readonly="true" class="form-control" type="date"
                                                                    value="${donHang.ngayNhan}"
                                                                    placeholder=""
                                                                    path="ngayNhan"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Ngày ship:
                                                        <div style="color: crimson;float: right"></div>
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input readonly="true" class="form-control" type="date"
                                                                    value="${donHang.ngayShip}"
                                                                    placeholder=""
                                                                    path="ngayShip"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        </form>
                                    </form:form>
                                </div>
                            </div>
                            <br>
                            <h3 style="text-align: center;">Hóa đơn chi tiết</h3>
                            <br>
                            <div class="search">
                                <div class="input-group" style="width: 30%; float: right">
                                    <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                           aria-label="Bạn tìm gì..." name="search" id="search">
                                    <div class="input-group-append">
                                        <button class="btn btn-sm btn-primary" type="button" id="button-search">Search
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table id="example" class="display" style="color: black">
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
                                            <td>${hdct.donGia}</td>
                                            <td>${hdct.donGia * hdct.soLuong}</td>
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
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    $('button[id^="button-search"]').on('click', async function (e) {
        const btn = $(this);
        const search = $("#search").val();
        const url = "http://localhost:8080/don-hang/search-hdct?search=" + search;
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
                <td>` + hdct.imei.chiTietSanPham.sanPham.ten + `</td>
                <td align="center"><img src="/uploads/` + hdct.imei.chiTietSanPham.urlAnh + `" width="40" height="40"></td>
                <td>` + hdct.imei.chiTietSanPham.sanPham.hangSanPham.ten + `</td>
                <td>` + hdct.imei.chiTietSanPham.mauSac.ten + `</td>
                <td>` + hdct.imei.chiTietSanPham.ram.dungLuong + `</td>
                <td>` + hdct.imei.chiTietSanPham.rom.dungLuong + `</td>
                <td>` + hdct.imei.soImei + `</td>
                <td>` + hdct.donGia + `</td>
                <td>` + hdct.donGia * hdct.soLuong + `</td>
            </tr>
            `;
                html += tr;
            }
            $("#table-search").html(html);
        } catch (err) {
            console.error(err)
        }
    });
</script>
</html>
