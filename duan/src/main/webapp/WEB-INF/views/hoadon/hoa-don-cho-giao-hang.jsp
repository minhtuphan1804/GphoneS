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
    <link rel="stylesheet" href="../../../vendor/toastr/css/toastr.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <%--thắng làm--%>
    <style>


        .notifications {
            position: absolute;
            top: 30px;
            right: 20px;
        }

        .toast1 {

            position: relative;
            padding: 10px;
            color: #fff;
            margin-bottom: 10px;
            width: 400px;
            display: grid;
            grid-template-columns: 70px 1fr 70px;
            border-radius: 5px;
            --color: #0abf30;
            background-image: linear-gradient(
                    to right, #0abf3055, #22242f 30%
            );
            animation: show 0.3s ease 1 forwards
        }

        .toast1 i {
            color: var(--color);
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: x-large;
        }

        .toast1 .title {
            font-size: x-large;
            font-weight: bold;
        }

        .toast1 span, .toast1 i:nth-child(3) {
            color: #fff;
            opacity: 0.6;
        }

        @keyframes show {
            0% {
                transform: translateX(100%);
            }
            40% {
                transform: translateX(-5%);
            }
            80% {
                transform: translateX(0%);
            }
            100% {
                transform: translateX(-10%);
            }
        }

        .toast1::before {
            position: absolute;
            bottom: 0;
            left: 0;
            background-color: seagreen;
            width: 100%;
            height: 3px;
            content: '';
            box-shadow: 0 0 10px var(--color);
            animation: timeOut 5s linear 1 forwards
        }

        @keyframes timeOut {
            to {
                width: 0;
            }
        }

        .toast1.error {
            --color: #f24d4c;
            background-image: linear-gradient(
                    to right, #f24d4c55, #22242F 30%
            );
        }

        .toast1.warning {
            --color: #e9bd0c;
            background-image: linear-gradient(
                    to right, #e9bd0c55, #22242F 30%
            );
        }

        .toast1.info {
            --color: #3498db;
            background-image: linear-gradient(
                    to right, #3498db55, #22242F 30%
            );
        }

        .dataTables_filter {
            display: block;
        }

        .dataTables_info {
            display: block;
        }
    </style>
    <%--hết thắng làm--%>
</head>
<body>
<c:if test="${thongBaoLoc != null}">
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
                            <h4 style="color: #10ae05;margin: 10px;text-align: center">${thongBaoLoc}</h4>
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
<button id="error" style="display: none">Error</button>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn
                chưa xác nhận</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi-xac-nhan" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn đã xác nhận</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="/hoa-don/hien-thi-cho-giao"
               role="tab"
               aria-controls="description" aria-selected="true">Hóa đơn chờ giao
                hàng</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi-dang-giao" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn đang giao
                hàng</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi-hoan-tat" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn giao hàng
                hoàn tất</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/hien-thi-da-huy" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Hóa đơn đã hủy</a>
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
                                    <a href="/hoa-don/export-excel" class="dropdown-item" tabindex="-1">Theo ngày thanh
                                        toán</a>
                                    <a href="/hoa-don/export-excel-ngay-nhan" class="dropdown-item" tabindex="-1">Theo
                                        ngày nhận</a>
                                    <a href="/hoa-don/export-excel-ngay-ship" class="dropdown-item" tabindex="-1">Theo
                                        ngày ship</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <div class="loc" style="color:black;">
                        <form:form action="/hoa-don/loc-cho-giao-hang" method="post" modelAttribute="hoaDon">
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
                                            <select name="loaiHoaDon" class="form-control select2"
                                                    style="font-weight: bold; width: 100%" id="selectLoai1">
                                                <option selected disabled>Loại hóa đơn</option>
                                                <option value="0">Tại quầy</option>
                                                <option value="1">Online</option>
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
<%--                    <div class="search">--%>
<%--                        <form action="/hoa-don/search-cho-giao-hang" method="post">--%>
<%--                            <div class="input-group" style="width: 30%; float: right">--%>
<%--                                <input type="text" class="form-control" placeholder="Bạn tìm gì..."--%>
<%--                                       aria-label="Bạn tìm gì..." name="search">--%>
<%--                                <div class="input-group-append">--%>
<%--                                    <button class="btn btn-sm btn-primary" type="submit">Search</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                    </div>--%>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example" class="display" style="color: black; width: 2000px">
                            <thead>
                            <tr>
                                <th>Mã hóa đơn</th>
                                <th>Ngày Tạo</th>
                                <th>Tên khách hàng</th>
                                <th>Tên nhân viên</th>
                                <th>Tổng tiền</th>
                                <th>Loại HĐ</th>
                                <th>Hình thức thanh toán</th>
                                <th>Mã giao dịch</th>
                                <th>Ngày ship</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listHoaDon}" var="hoaDon">
                                <tr>
                                    <td>${hoaDon.ma}</td>
                                    <td>${hoaDon.ngayTao}</td>
                                    <td>${hoaDon.khachHang.hoTen}</td>
                                    <td>${hoaDon.nhanVien.hoTen}</td>
                                    <td>
                                        <script>
                                            var donGia = ${hoaDon.tongTien};
                                            document.write(donGia.toLocaleString('vi-VN'));
                                        </script>
                                        VND
                                    </td>
                                    <td><c:if test="${hoaDon.loai == 1}">HĐ online</c:if>
                                        <c:if test="${hoaDon.loai == 0}">HĐ quầy</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${hoaDon.hinhThucThanhToan == 1}">Online</c:if>
                                        <c:if test="${hoaDon.hinhThucThanhToan == 0}">Tiền mặt</c:if>
                                    </td>
                                    <td>${hoaDon.maGiaoDich}</td>
                                    <td>${hoaDon.ngayShip}</td>
                                    <td>
                                        <a href="/hoa-don/detail/${hoaDon.id}" class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Detail</a>
                                        <c:if test="${hoaDon.loai== 1 && (hoaDon.tinhTrangGiaoHang == 1 || hoaDon.tinhTrangGiaoHang == 0) && hoaDon.nhanVien!= null && hoaDon.tinhTrang != 9}">
                                            <a href="/hoa-don/xac-nhan-giao-hang-cho-giao/${hoaDon.id}"
                                               id="toastr-success-top-right-hoa-don-cho-giao"
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
                                        <c:if test="${hoaDon.loai== 1 && (hoaDon.tinhTrangGiaoHang == 0 ||hoaDon.tinhTrangGiaoHang == 1 || hoaDon.tinhTrangGiaoHang == 2) && hoaDon.hinhThucThanhToan == 0}">

                                            <a href="/hoa-don/xac-nhan-huy-cho-giao/${hoaDon.id}"
                                               id="toastr-success-top-right-hoa-don-cho-giao-2"
                                               class="btn btn-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Hủy đơn online thanh toán khi nhận hàng</a>

                                        </c:if>
                                        <c:if test="${hoaDon.loai== 1 && (hoaDon.tinhTrangGiaoHang == 0|| hoaDon.tinhTrangGiaoHang == 1 || hoaDon.tinhTrangGiaoHang == 2) && hoaDon.hinhThucThanhToan == 1}">

                                            <a href="/hoa-don/xac-nhan-huy-hoan-tien-cho-giao/${hoaDon.id}"
                                               id="toastr-success-top-right-hoa-don-cho-giao-3"
                                               class="btn btn-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Hủy đơn online thanh toán VNPay</a>
                                        </c:if>
                                        <c:if test="${hoaDon.loai== 0 && (hoaDon.tinhTrang == 0|| hoaDon.tinhTrang == 1|| hoaDon.tinhTrang == 3)}">
                                            <a href="/hoa-don/huy-cho-giao/${hoaDon.id}"
                                               id="toastr-success-top-right-hoa-don-cho-giao-4"
                                               class="btn btn-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Hủy đơn tại quầy</a>
                                        </c:if>
                                        <c:if test="${(hoaDon.tinhTrang == 0|| hoaDon.tinhTrang == 1|| hoaDon.tinhTrang == 3 &&
                                         hoaDon.tinhTrangGiaoHang != 2 && hoaDon.tinhTrangGiaoHang != 3 || (hoaDon.tinhTrang == 2 && hoaDon.loai==1  && hoaDon.tinhTrangGiaoHang != 2 && hoaDon.tinhTrangGiaoHang != 3))
                                          && hoaDon.tinhTrang != 9 && hoaDon.khachHang!= null && hoaDon.nhanVien!= null }">
                                            <a href="/hoa-don/view-update-cho-giao/${hoaDon.id}"
                                               class="btn btn-warning btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="ti-reload btn-icon-prepend"></i>
                                                Update thông tin</a>
                                        </c:if>
                                        <c:if test="${(hoaDon.tinhTrang == 2||hoaDon.tinhTrang == 3) && hoaDon.nhanVien!= null}">
                                            <a href="/hoa-don/xuat-pdf-cho-giao/${hoaDon.id}"
                                               id="toastr-success-top-right-hoa-don-cho-giao-5"
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
    $('#selectTrangThai1').select2({
        theme: 'bootstrap-5'
    });
    $('#selectTrangThai2').select2({
        theme: 'bootstrap-5'
    });
    $('#selectLoai1').select2({
        theme: 'bootstrap-5'
    });

    function soSanhNgayNhan(ngayNhan) {
        const ngayHienTai = Date.now();
        const soNgay = (ngayHienTai - new Date(ngayNhan).getTime()) / (1000 * 60 * 60 * 24);

        return soNgay <= 7;
    }
</script>
<script>
    let error = document.getElementById('error');
    let notifications = document.querySelector('.notifications');

    function createToast() {
        let newToast = document.createElement('div');
        newToast.innerHTML = `
            <div class="toast1 Success" style="height: 2cm;">
                <i class="fa-solid fa-circle-exclamation"></i>
                <div class="content">
                    <div class="title" style="font-size:20px">${batthongbaobenhoadon}</div>

                </div>
                <i class="fa-solid fa-xmark" onclick="(this.parentElement).remove()"></i>
            </div>`;
        notifications.appendChild(newToast);
        newToast.timeOut = setTimeout(
            () => newToast.remove(), 5000
        )
    }

    error.onclick = function () {
        let type = 'error';
        let icon = 'fa-solid fa-circle-exclamation';
        let title = 'Error';
        let text = 'This is a error toast.';
        createToast(type, icon, title, text);
    }

    <c:if test="${batthongbaobenhoadon !=null}">document.getElementById('error').click()
    </c:if>
</script>
<script src="../../../vendor/global/global.min.js"></script>
<script src="../../../js/quixnav-init.js"></script>
<script src="../../../vendor/toastr/js/toastr.min.js"></script>

<!-- All init script -->
<script src="../../../js/plugins-init/toastr-init.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- Toastr -->

</html>
