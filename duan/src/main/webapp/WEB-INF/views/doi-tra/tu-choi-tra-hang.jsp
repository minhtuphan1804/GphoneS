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
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/doi-tra/hien-thi" role="tab"
            >Chờ xác nhận đổi trả</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="/doi-tra/hien-thi-tu-choi-tra"
               role="tab" aria-controls="description" aria-selected="true">Từ chối đổi trả </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/doi-tra/thanh-cong" role="tab">Đổi trả thành công </a>
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
                        <h4 class="card-title" style="float: left">Danh sách hóa đơn ttừ chối đổi trả
                        </h4>
                    </div>
                    <br>
                    <br>

                    <div class="loc" style="color:black;">
                        <form:form action="/doi-tra/loc1" method="post" modelAttribute="doiTra">
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
                                    Lọc hóa đơn
                                </BUTTON>
                            </div>
                        </form:form>
                    </div>
                    <br>
                    <div class="search">
                        <form action="/doi-tra/search1" method="post">
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
                        <table id="example" class="display" style="color: black; width: 1500px">
                            <thead>
                            <tr>
                                <th>Mã đổi trả</th>
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
                                    <td>${doitra.hoaDon.khachHang.hoTen}</td>
                                    <td>${doitra.hoaDon.sdt}</td>
                                    <td>${doitra.nhanVien.hoTen}</td>
                                    <td>
                                        <c:if test="${doitra.tinhTrang == 0}">Đang chờ</c:if>
                                        <c:if test="${doitra.tinhTrang == 2}">Đã xác nhận đổi trả</c:if>
                                        <c:if test="${doitra.tinhTrang == 1}">Đã từ chối</c:if>
                                    </td>
                                    <td>
                                        <a href="/doi-tra/tai-doi-tra/${doitra.id}"
                                           class="btn btn-success btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-reload btn-icon-prepend"></i>
                                            Tái đổi trả
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>
