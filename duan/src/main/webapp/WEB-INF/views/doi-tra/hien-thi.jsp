<%@ page import="java.util.UUID" %>
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
</head>
<style>
    #detailTable {
        width: 100%;
        /* Điều chỉnh kích thước tối đa của bảng */
        background-color: #f5f5f5; /* Màu nền */
        border: 1px solid #ddd; /* Biên */
    }

    #detailTable th {
        background-color: blue; /* Màu nền của tiêu đề cột */
        color: #fff; /* Màu chữ tiêu đề cột */
    }

    #detailTable th, #detailTable td {
        padding: 10px; /* Khoảng cách bên trong ô */
        text-align: center; /* Căn giữa nội dung */
    }


</style>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="/doi-tra/hien-thi" role="tab"
               aria-controls="description" aria-selected="true">Chờ xác nhận đổi trả</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/doi-tra/hien-thi-tu-choi-tra" role="tab">Từ chối đổi trả </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/doi-tra/thanh-cong" role="tab">Đổi trả thành công </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-bs-toggle="modal" data-bs-target="#exampleModalTaoMoi" role="tab">Tạo mới phiếu đổi trả</a>
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
                        <h4 class="card-title" style="float: left">Danh sách hóa đơn chờ đổi trả
                        </h4>
                    </div>
                    <br>
                    <br>

                    <div class="loc" style="color:black;">
                        <form:form action="/doi-tra/loc0" method="post" modelAttribute="doiTra">
                            <div class="row" style="margin-top: 10px">
                                <div class="col-md-6">
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
                                <div class="col-md-6">
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
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Từ ngày đổi trả:</label>
                                        <div class="col-sm-8">
                                            <input type="date" name="startDate" class="form-control"
                                                   placeholder="Từ ngày">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Từ ngày tạo:</label>
                                        <div class="col-sm-8">
                                            <input type="date" name="ngayTao0" class="form-control"
                                                   placeholder="Đến ngày">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Đến ngày đổi trả:</label>
                                        <div class="col-sm-8">
                                            <input type="date" name="endDate" class="form-control"
                                                   placeholder="Từ ngày">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Đến ngày tạo:</label>
                                        <div class="col-sm-8">
                                            <input type="date" name="ngayTao1" class="form-control"
                                                   placeholder="Đến ngày">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div align="center">
                                <BUTTON type="submit" class="btn btn-warning" style="" id="bt">
                                    Lọc phiếu đổi trả
                                </BUTTON>
                            </div>
                        </form:form>
                    </div>
                    <br>
                    <div class="search">
                        <form action="/doi-tra/search0" method="post">
                            <div class="input-group" style="width: 30%; float: right">
                                <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                       aria-label="Bạn tìm gì..." name="search">
                                <div class="input-group-append">
                                    <button class="btn btn-sm btn-primary" type="submit">Tìm kiếm</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example3" class="display" style="color: black; width: 1500px;text-align: center">
                            <thead>
                            <tr>
                                <th>Mã phiếu đổi trả</th>
                                <th>Ngày tạo</th>
                                <th>Mã hóa đơn</th>
                                <th>Tên khách hàng</th>
                                <th>Số điện thoại khách hàng</th>
                                <th>Tên nhân viên</th>
                                <th>Tình trạng</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${list}" var="doitra">
                                <tr>
                                    <td>${doitra.ma}</td>
                                    <td>${doitra.ngayTao}</td>
                                    <td>${doitra.hoaDon.ma}</td>
                                    <td>${doitra.khachHang.hoTen}</td>
                                    <td>${doitra.hoaDon.sdt}</td>
                                    <td>${doitra.nhanVien.hoTen}</td>
                                    <td>
                                        <c:if test="${doitra.tinhTrang == 0}">Đang chờ</c:if>
                                        <c:if test="${doitra.tinhTrang == 2}">Đã xác nhận</c:if>
                                        <c:if test="${doitra.tinhTrang == 1}">Đã từ chối</c:if>
                                    </td>
                                    <td>
                                        <a href="/doi-tra/detail/${doitra.hoaDon.id}?doitraId=${doitra.id}&hoadonId=${doitra.hoaDon.id}"
                                           class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Detail
                                        </a>

                                        <a href="/doi-tra/huy/${doitra.id}"
                                           class="btn btn-danger btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="fas fa-times-circle"></i>
                                            Từ chối phiếu đổi trả
                                        </a>


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
<div class="modal fade" id="exampleModalTaoMoi" tabindex="-1" aria-labelledby="exampleModalLabelTaoMoi"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-xl modal-dialog-centered"> <!-- Sử dụng lớp 'modal-lg' để làm cho modal lớn hơn -->
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelAnh">Chọn hóa đơn đổi trả</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="card">
                    <div class="card-body">
                        <div class="input-group" style="width: 30%; float: right">
                            <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                   aria-label="Bạn tìm gì..." name="search-hoa-don" id="hoaDonSearchInput">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="button" id="searchHoaDon">Search
                                </button>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table id="example" class="display" style="color: black;min-width: 2300px">
                                <thead>
                                <tr>
                                    <th>Mã hóa đơn</th>
                                    <th>Ngày Thanh Toán</th>
                                    <th>Ngày Tạo</th>
                                    <th>Tên khách hàng</th>
                                    <th>Tên nhân viên</th>
                                    <th>Địa chỉ</th>
                                    <th>SĐT</th>
                                    <th>Tổng tiền</th>
                                    <th>Hình thức thanh toán</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody class="hoa_don_search">
                                <c:forEach items="${listt}" var="hoaDon">
                                    <tr>
                                        <td>${hoaDon.getMa()}</td>
                                        <td>${hoaDon.getNgayThanhToan()}</td>
                                        <td>${hoaDon.getNgayTao()}</td>
                                        <td>${hoaDon.getHoTenKhachHang()}</td>
                                        <td>${hoaDon.getHoTenNhanVien()}</td>
                                        <td>${hoaDon.getDiaChi()}-${hoaDon.getQuan()}-${hoaDon.getHuyen()}-${hoaDon.getThanhPho()}</td>
                                        <td>${hoaDon.getSDT()}</td>
                                        <td>
                                            <script>
                                                var donGia = ${hoaDon.tongTien};
                                                document.write(donGia.toLocaleString('vi-VN'));
                                            </script>
                                            VND
                                        </td>
                                        <td>
                                            <c:if test="${hoaDon.getHinhThuc() == 1}">Online</c:if>
                                            <c:if test="${hoaDon.getHinhThuc() == 0}">Tiền mặt</c:if>
                                        </td>
                                        <td>
                                            <a class="btn btn-warning btn-icon-text" tabindex="-1" role="button"
                                               data-hoadonid="${hoaDon.getId()}" onclick="showChiTietModal(this);">
                                                <i class="ti-file btn-icon-prepend"></i>
                                                Chọn
                                            </a>
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
<c:if test="${thongBao != null}">
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
                            <h4 style="color: #10ae05;margin: 10px;text-align: center">${thongBao}</h4>
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
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        $('#modalSuccess').modal('show');
    });
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
    $('button[id^="searchHoaDon"]').on('click', async function (e) {
        const btn = $(this);
        const parentModal = btn.closest('.modal'); // Lấy modal cha gần nhất của nút "Tìm kiếm" được nhấn
        const search = parentModal.find("#hoaDonSearchInput").val();
        const url = "http://localhost:8080/doi-tra/search-hoa-don?search-hoa-don=" + search;
        try {
            const resp = await fetch(url);
            const data = await resp.json();
            console.log(data)
            // Hiển thị dữ liệu tìm kiếm
            let html = ``;
            for (let i = 0; i < data.length; i++) {
                const hoaDon = data[i];
                const tr = `
            <tr>
                <td>` + hoaDon.ma + `</td>
                <td>` + hoaDon.ngayThanhToan + `</td>
                <td>` + hoaDon.ngayTao + `</td>
                <td>` + hoaDon.khachHang.hoTen + `</td>
                <td>` + hoaDon.nhanVien.hoTen + `</td>
                <td>` + hoaDon.diaChi.diaChi + `-` + hoaDon.diaChi.quan + `-` + hoaDon.diaChi.huyen + `-` + hoaDon.diaChi.thanhPho + `</td>
                <td>` + hoaDon.sdt + `</td>
                <td>` + hoaDon.tongTien + `</td>
                <td>` + (hoaDon.hinhThucThanhToan == 0 ? "Tiền mặt" : "Chuyển khoản") + `</td>
                <td>
                    <a class="btn btn-warning btn-icon-text" tabindex="-1" role="button"
                       data-hoadonid="` + hoaDon.id + ` " onclick="showChiTietModal(this);">
                        <i class="ti-file btn-icon-prepend"></i>
                        Chọn
                    </a>
                </td>
            </tr>
            `;
                html += tr;
            }
            parentModal.find(".hoa_don_search").html(html);
        } catch (err) {
            console.error(err)
        }
    });
</script>
<script>
    function createDoiTra(hoadonId) {
        // Gọi Controller để tạo đổi trả mới và truyền hoadonId
        $.ajax({
            url: "/doi-tra/add-doi-tra?hoadonId=" + hoadonId,
            type: "POST",
            success: function (data) {
                // Xử lý kết quả nếu cần
            },
            error: function (error) {
                console.log("Lỗi khi tạo đổi trả mới: " + error);
            }
        });
    }
</script>

    <script>
        function showChiTietModal(button) {
        var hoadonId = $(button).data("hoadonid");
        createDoiTra(hoadonId);
        $("#exampleModalTaoMoi").on('hidden.bs.modal', function (e) {
        $("#detailTable tbody").empty();
        window.location.reload();
    }).modal('hide');
    }

        function createDoiTra(hoadonId) {
        $.ajax({
            url: "/doi-tra/add-doi-tra?hoadonId=" + hoadonId,
            type: "POST",
            success: function (data) {
                toastr.success("Đổi trả đã được tạo thành công", "Thành công", {
                    timeOut: 1500,
                    closeButton: true,
                    progressBar: true,
                    positionClass: "toast-top-right"
                });
                // window.location.href = "/doi-tra/hien-thi" ;
                setTimeout(function () {
                    window.location.href = "/doi-tra/hien-thi";
                }, 1500);
            },
            error: function (error) {
                console.log("Lỗi khi tạo đổi trả mới: " + error);
            }
        });
    }
</script>




<script>
    function showChonSanPhamModal() {
        // Hiển thị modal chọn sản phẩm
        $("#exampleModalChonSanPham").modal("show");
    }

</script>


<script>
    $(document).ready(function () {
        // Sự kiện click vào nút "Chọn sản phẩm"
        $(".chonSanPhamButton").on("click", function () {
            // Hiển thị modal mới
            $('#exampleModalChonSanPham').modal('show');
        });
    });
</script>

<script>
    var doitraId = document.querySelector('a[data-doitra-id]').dataset.doitraId;
    console.log(doitraId); // In giá trị ra console để kiểm tra
</script>
<script>
    var hoadonId = document.querySelector('a[data-hoadon-id]').dataset.hoadonId;
    console.log(hoadonId); // In giá trị ra console để kiểm tra
</script>
<script>
    var doitraId = "${doitra.id}";
    localStorage.setItem("doitraId", doitraId);
</script>

<script>
    var hoadonId = "${doitra.hoaDon.id}";
    localStorage.setItem("hoadonId", hoadonId);
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>
