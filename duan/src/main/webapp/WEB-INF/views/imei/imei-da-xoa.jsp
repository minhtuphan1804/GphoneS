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
    <link rel="stylesheet" href="../../../vendor/toastr/css/toastr.min.css">
    <title>GPhoneS Store </title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>
    <!-- Favicon icon -->
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
               href="/imei/hien-thi" role="tab"
            >Thông tin Imei</a>
        </li>
        <li class="nav-item">
            <a class="nav-link"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
               href="/imei/view-add" role="tab"
            >Thêm thông tin
                Imei</a>
        </li>
        <li class="nav-item">
            <a class="nav-link"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
               href="/imei/hien-thi-da-ban" role="tab"
            >Imei đã bán - chờ xử lý</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Imei đã xoá</a>
        </li>
        <li class="nav-item">
            <a class="nav-link"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"
               href="/imei/hien-thi-imei-loi" role="tab"
            >Imei lỗi</a>
        </li>
    </ul>
</div>
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
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form action="/imei/loc-da-xoa" method="post" onsubmit="return checkLoc()">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Lọc IMEI
                    </h4>
                    <form class="forms-sample">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <select name="dungLuongPin" class="form-control"
                                            style="font-weight: bold; width: 100%" id="selectPin">
                                        <option selected disabled>Chi tiết sản phẩm</option>
                                        <c:forEach items="${listCTSP}" var="ctsp" varStatus="i">
                                            <option value="${ctsp.id}">${ctsp.sanPham.ten} - ${ctsp.mauSac.ten}
                                                - ${ctsp.ram.dungLuong} - ${ctsp.rom.dungLuong} - ${ctsp.chip.ten} - ${ctsp.pin.dungLuongPin.thongSo}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
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
                    <h4 class="card-title" style="float: left">Danh sách Imei đã xóa</h4>
                    <%--            Tìm kiếm               --%>
                    <form action="/imei/search-off-2" method="post">
                        <div class="input-group" style="width: 30%; float: right">
                            <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                   aria-label="Bạn tìm gì..." name="search">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="submit">Search</button>
                            </div>
                        </div>
                        <div style="float: right">
                            <a href="/imei/khoi-phuc-tat-ca" class="btn btn-danger btn-icon-text"
                               tabindex="-1"
                               id="toastr-success-top-right-imei"
                               role="button"
                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                <i class="ti-reload btn-icon-prepend"></i>
                                Khôi phục tất cả</a>
                        </div>


                    </form>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table style="color: black;width: 1600px;" id="example" class="display">
                            <thead>
                            <tr>
                                <th scope="col">Mã</th>
                                <th scope="col">Ngày tạo</th>
                                <th scope="col">Ngày cập nhật</th>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">Số imei</th>
                                <th scope="col">Tình trạng</th>
                                <th scope="col">Lịch sử thay đổi</th>
                                <th scope="col">Mô tả</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listImei}" var="imei" varStatus="index">
                                <tr>
                                    <td>${imei.ma}</td>
                                    <td>${imei.ngayTao}</td>
                                    <td>${imei.ngayCapNhat}</td>
                                    <td>${imei.chiTietSanPham.sanPham.ten}-
                                            ${imei.chiTietSanPham.mauSac.ten}-
                                            ${imei.chiTietSanPham.ram.dungLuong}-
                                            ${imei.chiTietSanPham.rom.dungLuong}</td>
                                    <td>${imei.soImei}</td>
                                    <td>
                                        <c:if test="${imei.tinhTrang == 0}">Chưa bán</c:if>
                                        <c:if test="${imei.tinhTrang == 1}">Đã bán</c:if>
                                        <c:if test="${imei.tinhTrang == 2}">Đã hủy</c:if>
                                        <c:if test="${imei.tinhTrang == 3}">Chờ xử lý</c:if>
                                    </td>

                                    <td>${imei.lichSu}</td>
                                    <td>${imei.moTa}</td>
                                    <td>

                                        <a href="/imei/khoi-phuc/${imei.id}"
                                           class="btn btn-danger btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-reload btn-icon-prepend"></i>
                                            Khôi phục</a>
                                        <a href="/imei/show-qr/${imei.id}"
                                           class="btn btn-info btn-icon-text"
                                           data-bs-toggle="modal"
                                           data-bs-target="#showQR_${imei.id}">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 fill="currentColor" class="bi bi-qr-code-scan" viewBox="0 0 16 16">
                                                <path d="M0 .5A.5.5 0 0 1 .5 0h3a.5.5 0 0 1 0 1H1v2.5a.5.5 0 0 1-1 0v-3Zm12 0a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0V1h-2.5a.5.5 0 0 1-.5-.5ZM.5 12a.5.5 0 0 1 .5.5V15h2.5a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5v-3a.5.5 0 0 1 .5-.5Zm15 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1 0-1H15v-2.5a.5.5 0 0 1 .5-.5ZM4 4h1v1H4V4Z"/>
                                                <path d="M7 2H2v5h5V2ZM3 3h3v3H3V3Zm2 8H4v1h1v-1Z"/>
                                                <path d="M7 9H2v5h5V9Zm-4 1h3v3H3v-3Zm8-6h1v1h-1V4Z"/>
                                                <path d="M9 2h5v5H9V2Zm1 1v3h3V3h-3ZM8 8v2h1v1H8v1h2v-2h1v2h1v-1h2v-1h-3V8H8Zm2 2H9V9h1v1Zm4 2h-1v1h-2v1h3v-2Zm-4 2v-1H8v1h2Z"/>
                                                <path d="M12 9h2V8h-2v1Z"/>
                                            </svg>
                                            QR Code</a>
                                        <div class="modal fade" id="showQR_${imei.id}" tabindex="-1"
                                             aria-labelledby="exampleModalLabelQR"
                                             aria-hidden="true" data-backdrop="static">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h2 class="modal-title" id="exampleModalLabelQR">QR Code</h2>
                                                    </div>
                                                    <div class="modal-body">
                                                        <table class="table" id="table_id">
                                                            <tbody id="listImei_${imei.id}"
                                                                   class="imei_search">
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">Close
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
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
        $('div[id^="showQR_"]').on('show.bs.modal', async function (e) {
            const id = e.currentTarget.id.split("_")[1];
            const url = "http://localhost:8080/imei/show-qr/" + id;
            console.log(id, url);
            try {
                const resp = await fetch(url);
                const data = await resp.json();
                console.log(data)
                let html = '';
                for (let i = 0; i < data.length; i++) {
                    const imei = data[i];
                    const tr = `
                <tr>
                    <td align="center"><img src="/maqr/` + imei.maQr + `" width="300" height="300"></td>
                </tr>
            `;
                    html += tr;
                }

                $("#listImei_" + id).html(html);
            } catch (err) {
                console.error(err)
            }
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</html>
