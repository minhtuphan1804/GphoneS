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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../../../vendor/toastr/css/toastr.min.css">
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <!-- Favicon icon -->
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin chi tiết
                sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/hien-thi-het-hang" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Sản phẩm hết
                hàng</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true" role="tab">Sản phẩm ngừng kinh doanh</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/view-add" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm mới chi tiết
                sản phẩm</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form action="/chi-tiet-san-pham/locngungban" method="post" onsubmit="return checkLoc()">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Lọc chi tiết sản phẩm
                    </h4>
                    <form class="forms-sample">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="hang" class="form-control" style="font-weight: bold; width: 100%"
                                            id="selectHang">
                                        <option selected disabled>Hãng</option>
                                        <c:forEach items="${listHang}" var="hang" varStatus="i">
                                            <option value="${hang.id}">${hang.ten}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="dungLuongPin" class="form-control"
                                            style="font-weight: bold; width: 100%" id="selectPin">
                                        <option selected disabled>Dung Lượng Pin</option>
                                        <c:forEach items="${dungLuongPin}" var="pin" varStatus="i">
                                            <option value="${pin.id}">${pin.thongSo}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="chip" class="form-control" style="font-weight: bold; width: 100%"
                                            id="selectChip">
                                        <option selected disabled>Chip</option>
                                        <c:forEach items="${listChip}" var="chip" varStatus="i">
                                            <option value="${chip.id}">${chip.ten}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="manHinh" class="form-control" style="font-weight: bold; width: 100%"
                                            id="selectManHinh">
                                        <option selected disabled>Màn Hình</option>
                                        <c:forEach items="${listManHinh}" var="man" varStatus="i">
                                            <option value="${man.id}">${man.thongSo}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="idSanPham" class="form-control" style="font-weight: bold; width: 100%"
                                            id="selectSanPham">
                                        <option selected disabled>Sản Phẩm</option>
                                        <c:forEach items="${listSanPham}" var="sp" varStatus="i">
                                            <option value="${sp.id}">${sp.ten}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="rom" class="form-control" style="font-weight: bold; width: 100%"
                                            id="selectRom">
                                        <option selected disabled>Rom</option>
                                        <c:forEach items="${listRom}" var="rom" varStatus="i">
                                            <option value="${rom.id}">${rom.dungLuong}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="ram" class="form-control" style="font-weight: bold; width: 100%"
                                            id="selectRam">
                                        <option selected disabled>Ram</option>
                                        <c:forEach items="${listRam}" var="ram" varStatus="i">
                                            <option value="${ram.id}">${ram.dungLuong}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="camera" class="form-control" style="font-weight: bold; width: 100%"
                                            id="selectCamera">
                                        <option selected disabled>Camera</option>
                                        <c:forEach items="${listCamera}" var="cam" varStatus="i">
                                            <option value="${cam.id}">${cam.thongSo}</option>
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

        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title" style="float: left">Danh sách Chi tiết sản phẩm hết hàng
                        <div class="dropdown-wrapper">
                            <div class="dropdown">
                                <button type="button" class="btn btn-primary dropdown-toggle"
                                        data-toggle="dropdown">
                                    <i class="ti-export btn-icon-prepend"></i>
                                    Xuất Excel
                                </button>
                                <div class="dropdown-menu">
                                    <a href="/chi-tiet-san-pham/export-excel" class="dropdown-item" tabindex="-1"
                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Sản
                                        phẩm còn kinh doanh</a>
                                    <a href="/chi-tiet-san-pham/export-excel-chi-tiet-san-pham-ngung-kinh-doanh"
                                       onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                       class="dropdown-item"
                                       tabindex="-1">Sản phẩm ngừng kinh doanh
                                    </a>
                                    <div class="dropdown-submenu">
                                        <a href="#" class="dropdown-item dropdown-toggle" data-toggle="dropdown">Hãng
                                            sản phẩm</a>
                                        <div class="dropdown-menu">
                                            <c:forEach items="${listHang}" var="hang">
                                                <a href="/chi-tiet-san-pham/export-excel-chi-theo-hang/${hang.id}"
                                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                                   class="dropdown-item" tabindex="-1">${hang.ten}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="dropdown-submenu">
                                        <a href="#" class="dropdown-item dropdown-toggle" data-toggle="dropdown">Loại
                                            Chip</a>
                                        <div class="dropdown-menu">
                                            <c:forEach items="${listChip}" var="chip">
                                                <a href="/chi-tiet-san-pham/export-excel-chi-theo-chip/${chip.id}"
                                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                                   class="dropdown-item" tabindex="-1">${chip.ten}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="dropdown-submenu">
                                        <a href="#" class="dropdown-item dropdown-toggle"
                                           data-toggle="dropdown">Màn hình</a>
                                        <div class="dropdown-menu">
                                            <c:forEach items="${listManHinh}" var="man">
                                                <a href="/chi-tiet-san-pham/export-excel-chi-theo-man/${man.id}"
                                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                                   class="dropdown-item" tabindex="-1">${man.thongSo}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="dropdown-submenu">
                                        <a href="#" class="dropdown-item dropdown-toggle"
                                           data-toggle="dropdown">Dung lượng pin</a>
                                        <div class="dropdown-menu">
                                            <c:forEach items="${dungLuongPin}" var="pin">
                                                <a href="/chi-tiet-san-pham/export-excel-chi-theo-pin/${pin.id}"
                                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                                   class="dropdown-item" tabindex="-1">${pin.thongSo}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="dropdown-submenu">
                                        <a href="#" class="dropdown-item dropdown-toggle"
                                           data-toggle="dropdown">Ram</a>
                                        <div class="dropdown-menu">
                                            <c:forEach items="${listRam}" var="ram">
                                                <a href="/chi-tiet-san-pham/export-excel-chi-theo-ram/${ram.id}"
                                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                                   class="dropdown-item" tabindex="-1">${ram.dungLuong}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="dropdown-submenu">
                                        <a href="#" class="dropdown-item dropdown-toggle"
                                           data-toggle="dropdown">Rom</a>
                                        <div class="dropdown-menu">
                                            <c:forEach items="${listRom}" var="rom">
                                                <a href="/chi-tiet-san-pham/export-excel-chi-theo-rom/${rom.id}"
                                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                                   class="dropdown-item" tabindex="-1">${rom.dungLuong}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="dropdown-submenu">
                                        <a href="#" class="dropdown-item dropdown-toggle"
                                           data-toggle="dropdown">Camera</a>
                                        <div class="dropdown-menu">
                                            <c:forEach items="${listCamera}" var="cam">
                                                <a href="/chi-tiet-san-pham/export-excel-chi-theo-cam/${cam.id}"
                                                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                                   class="dropdown-item" tabindex="-1">${cam.thongSo}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </h4>

                    <%--            Tìm kiếm               --%>
                    <form action="/chi-tiet-san-pham/search-da-xoa" method="post">
                        <div class="input-group" style="width: 30%; float: right">
                            <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                   aria-label="Bạn tìm gì..." name="search">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="submit"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
                                >Search
                                </button>
                            </div>
                        </div>
                        <div style="float: right">
                            <a href="/chi-tiet-san-pham/khoi-phuc-tat-ca" class="btn btn-danger btn-icon-text"
                               tabindex="-1"
                               id="toastr-success-top-right-chi-tiet-san-pham"
                               role="button"
                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                <i class="ti-reload btn-icon-prepend"></i>
                                Khôi phục tất cả</a>
                        </div>


                    </form>


                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example" class="display" style="color: black;width: 2000px">
                            <thead>
                            <tr>
                                <th scope="col">Ảnh sản phẩm</th>
                                <th scope="col">Ngày tạo</th>
                                <th scope="col">Ngày cập nhật</th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Màu sắc</th>
                                <th scope="col">Chip</th>
                                <th scope="col">Ram</th>
                                <th scope="col">Rom</th>
                                <th scope="col">Pin</th>
                                <th scope="col">Giá bán</th>
                                <th scope="col">Tình trạng</th>
                                <th scope="col">Năm bảo hành</th>
                                <th scope="col">Số lượng tồn</th>
                                <th scope="col">Mô tả</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listCTSP}" var="ctsp" varStatus="index">


                                <tr>
                                    <td align="center">
                                        <img src="/uploads/${ctsp.urlAnh}" width="40" height="40">
                                    </td>
                                    <td>${ctsp.ngayTao}</td>
                                    <td>${ctsp.ngayCapNhat}</td>
                                    <td>${ctsp.sanPham.ten}</td>
                                    <td>${ctsp.mauSac.ten}</td>
                                    <td>${ctsp.chip.ten}</td>
                                    <td>${ctsp.ram.dungLuong}</td>
                                    <td>${ctsp.rom.dungLuong}</td>
                                    <td>${ctsp.pin.dungLuongPin.thongSo}</td>
                                    <td>
                                        <script>
                                            var donGia = ${ctsp.giaBan};
                                            document.write(donGia.toLocaleString('vi-VN'));
                                        </script>
                                        VND
                                    </td>
                                    <td>${ctsp.tinhTrang==0?"Còn kinh doanh":"Ngừng kinh doanh"}</td>
                                    <td>${ctsp.namBaoHanh}</td>
                                    <td>${ctsp.soLuong}</td>
                                    <td>${ctsp.moTa}</td>
                                    <td>

                                        <a href="/chi-tiet-san-pham/khoi-phuc/${ctsp.id}"
                                           class="btn btn-danger btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-reload btn-icon-prepend"></i>
                                            Khôi phục</a>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="../../../vendor/global/global.min.js"></script>
<script src="../../../js/quixnav-init.js"></script>
<script src="../../../vendor/toastr/js/toastr.min.js"></script>

<!-- All init script -->
<script src="../../../js/plugins-init/toastr-init.js"></script>
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
