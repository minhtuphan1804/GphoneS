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
            <a class="nav-link" href="/imei/hien-thi" role="tab"
               >
                Thông tin Imei</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thêm thông tin Imei</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/imei/hien-thi-da-ban" role="tab"
               >Imei đã bán</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/imei/hien-thi-da-xoa" role="tab"
               >Imei đã xoá</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/imei/hien-thi-imei-loi" role="tab"
               >Imei lỗi</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form:form action="/imei/add" method="post" modelAttribute="imei">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body" style="color: black">
                        <form class="forms-sample">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-12">
                                        <form:select path="chiTietSanPham" class="form-control"
                                                     cssStyle="font-weight: bold; width: 100%" id="selectSanPham" onchange="validateSelect()">
                                            <option selected disabled>Sản phẩm</option>
                                            <c:forEach items="${listCTSP}" var="ctsp">
                                                <option value="${ctsp.id}">${ctsp.sanPham.ten} - ${ctsp.mauSac.ten}
                                                    - ${ctsp.ram.dungLuong} - ${ctsp.rom.dungLuong} - ${ctsp.chip.ten} - ${ctsp.pin.dungLuongPin.thongSo}</option>
                                            </c:forEach>
                                        </form:select>

                                        <span class="text-danger">${tb}</span>
                                    </div>
                                    <div class="col-1">
                                        <a type="button" href="/chi-tiet-san-pham/view-add">
                                            <img src="/uploads/plus.png">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="ma">Ma:</form:label>
                                <form:input class="form-control" placeholder="" path="ma" value="${ma}"
                                            readonly="true"/>
                                <form:errors path="ma" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="soImei">Imei:</form:label>
                                <form:input class="form-control" placeholder="Imei" path="soImei"/>
                                <form:errors path="soImei" cssStyle="color: red"></form:errors>
                                <label style="color: red">${thongBao}</label>
                            </div>
                            <div class="form-group">
                                <form:label path="moTa"><b>Mô Tả:</b></form:label>
                                <form:textarea path="moTa" class="form-control"></form:textarea>
                                <form:errors path="moTa" cssStyle="color: red"></form:errors>
                            </div>
                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    Thêm Thông Tin
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </form:form>
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
    function validateSelect() {
        var selectElement = document.getElementById("selectSanPham");
        var selectedValue = selectElement.value;

        if (selectedValue === null || selectedValue === '' || selectedValue === 'Sản phẩm') {
            alert("Vui lòng chọn một sản phẩm!");
            // Thực hiện xử lý validate ở đây, có thể hiển thị thông báo lỗi hoặc thực hiện các hành động khác.
            return false; // Ngăn chặn form submit hoặc các hành động khác nếu không hợp lệ
        } else {
            // Sản phẩm đã được chọn, không có lỗi
            return true; // Có thể submit form hoặc thực hiện hành động khác
        }
    }
</script>
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
