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
            <a class="nav-link" href="/pin/view-add" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm thông tin
                Pin</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/pin/hien-thi-delete" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Pin đã xoá</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Update thông tin Pin</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form:form action="/pin/update/${pin.id}" method="post" modelAttribute="pin">
            <%--    <div class="col-md-6 grid-margin stretch-card" >--%>
            <div class="card">
                <div class="card-body" style="color: black">
                    <h4 class="card-title">Update Pin</h4>
                    <form class="forms-sample">
                        <div class="form-group" style="display: none">
                            <form:label path="ma"><b>Mã:</b></form:label>
                            <form:input path="ma" class="form-control"/>
                            <form:errors path="ma"/>
                        </div>
                        <div class="form-group">
                            <form:label path="loaiPin"><b>Loại Pin:</b></form:label>
                            <form:input path="loaiPin" class="form-control"/>
                            <form:errors path="loaiPin"/>
                        </div>
                        <div class="form-group">
                            <form:label path="congNghePin"><b>Công nghệ Pin:</b></form:label>
                            <form:input path="congNghePin" class="form-control"/>
                            <form:errors path="congNghePin"/>
                        </div>
                        <div class="form-group" style="display: none">
                            <form:label path="ngayTao"><b>Ngày tạo:</b></form:label>
                            <form:input path="ngayTao" class="form-control"/>
                            <form:errors path="ngayTao"/>
                        </div>
                        <div class="form-group">
                            <form:label path="moTa"><b>Mô Tả:</b></form:label>
                            <form:textarea path="moTa" class="form-control"/>
                            <form:errors path="moTa"/>
                        </div>
                        <div class="form-group">
                            <form:label path="dungLuongPin"><b>Dung Lượng:</b></form:label>
                            <form:select path="dungLuongPin" items="${dungLuongPin}" class="form-control"
                                         itemLabel="thongSo"
                                         itemValue="id"/>
                        </div>
                        <div style="text-align: center">
                            <button type="submit" class="btn btn-primary mr-2"
                                    onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                Cập Nhật Thông Tin
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <%--    </div>--%>
        </form:form>
    </div>
</div>
</body>
<script src="../../vendors/js/vendor.bundle.base.js"></script>
<script src="../../js/off-canvas.js"></script>
<script src="../../js/hoverable-collapse.js"></script>
<script src="../../js/template.js"></script>
<script src="../../js/settings.js"></script>
<script src="../../js/todolist.js"></script>
</html>
