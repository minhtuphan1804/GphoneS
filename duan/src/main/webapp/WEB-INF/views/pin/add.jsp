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
    <!-- Favicon icon -->
</head>

<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/pin/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin Pin</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thêm thông tin
                pin</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/pin/hien-thi-delete" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;"> Pin đã xoá</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form:form action="/pin/add" method="post" modelAttribute="pin">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Thêm Pin</h4>
                    <form class="forms-sample">
                        <div class="form-group">
                            <form:label path="loaiPin"><b>Loại Pin:</b></form:label>
                            <form:input path="loaiPin" class="form-control"></form:input>
                            <form:errors path="loaiPin"></form:errors>
                        </div>
                        <div class="form-group">
                            <form:label path="congNghePin"><b>Công nghệ Pin:</b></form:label>
                            <form:input path="congNghePin" class="form-control"></form:input>
                            <form:errors path="congNghePin"></form:errors>
                        </div>
                        <div class="form-group">
                            <form:label path="moTa"><b>Mô Tả:</b></form:label>
                            <form:textarea path="moTa" class="form-control"></form:textarea>
                            <form:errors path="moTa"></form:errors>
                        </div>
                        <div class="form-group">
                            <form:label path="dungLuongPin"><b>Dung Lượng:</b></form:label>
                            <div class="row">
                                <div class="col-11">
                                    <form:select path="dungLuongPin" items="${listDungLuongPin}" class="form-control"
                                                 id="selectDungLuongPin"
                                                 itemLabel="thongSo"
                                                 itemValue="id"></form:select>
                                </div>
                                <div class="col-1">
                                    <a type="button" data-bs-toggle="modal"
                                       data-bs-target="#exampleModalDungLuongPin" style="margin: 10px">
                                        <img src="/uploads/plus.png">
                                    </a>
                                </div>
                            </div>
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
        </form:form>
    </div>
</div>
</body>
<div class="modal fade" id="exampleModalDungLuongPin" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="exampleModalLabelPin">Add New Dung Lượng Pin</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form:form action="/pin/modal-add" method="post" modelAttribute="dungLuongPin">
                    <div class="mb-3">
                        <form:label path="thongSo"><b>Thông số:</b></form:label>
                        <form:input path="thongSo" class="form-control"></form:input>
                        <form:errors path="thongSo"></form:errors>
                    </div>
                    <div class="mb-3">
                        <form:label path="moTa"><b>Mô Tả:</b></form:label>
                        <form:textarea path="moTa" class="form-control"></form:textarea>
                        <form:errors path="moTa"></form:errors>
                    </div>
                    <div class="text-center">
                            <%--                        <button type="submit" class="btn btn-primary" onclick="validateForm()" >Thêm Thông Tin</button>--%>
                        <button type="submit" class="btn btn-primary mr-2" id="btnPin"
                                onclick="return validateFormPin()">
                            Thêm Thông Tin
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    $(document).ready(function () {
        $('#selectDungLuongPin').select2();
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</html>
