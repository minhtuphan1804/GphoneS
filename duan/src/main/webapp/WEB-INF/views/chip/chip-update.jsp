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
    <!-- Favicon icon --></head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/chip/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin Chip </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chip/view-add" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm thông tin
                Chip</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chip/hien-thi-delete" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Chip đã xoá</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Update</a>
        </li>
    </ul>
</div>
<form:form action="/chip/update/${chip.id}" method="post" modelAttribute="chip">
    <%--    <div class="col-md-6 grid-margin stretch-card" >--%>
    <div class="card">
        <div class="card-body" style="color: black">
            <h4 class="card-title">Update Chip</h4>
            <form class="forms-sample">
                <div class="form-group" style="display: none">
                    <form:label path="ma"><b>Mã:</b></form:label>
                    <form:input path="ma" class="form-control"></form:input>
                    <form:errors path="ma" cssStyle="color: red"></form:errors>
                </div>
                <div class="form-group">
                    <form:label path="ten"><b>Tên:</b></form:label>
                    <form:input path="ten" class="form-control"></form:input>
                    <form:errors path="ten" cssStyle="color: red"></form:errors>
                </div>
                <div class="form-group">
                    <form:label path="loaiChip"><b>Loại Chip:</b></form:label>
                    <form:input path="loaiChip" class="form-control"></form:input>
                    <form:errors path="loaiChip" cssStyle="color: red"></form:errors>
                </div>
                <div class="form-group">
                    <form:label path="soNhan"><b>Số nhân:</b></form:label>
                    <form:input path="soNhan" class="form-control" type="number"></form:input>
                    <form:errors path="soNhan" cssStyle="color: red"></form:errors>
                </div>
                <div class="form-group" style="display: none">
                    <form:label path="ngayTao"><b>Mã:</b></form:label>
                    <form:input path="ngayTao" class="form-control"></form:input>
                    <form:errors path="ngayTao" cssStyle="color: red"></form:errors>
                </div>
                <div class="form-group">
                    <form:label path="moTa"><b>Mô Tả:</b></form:label>
                    <form:textarea path="moTa" class="form-control"></form:textarea>
                    <form:errors path="moTa" cssStyle="color: red"></form:errors>
                </div>
                <div style="text-align: center">
                    <button type="submit" class="btn btn-primary mr-2"
                            onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Cập Nhật Thông Tin
                    </button>
                </div>
            </form>
        </div>
    </div>
</form:form>
</body>

</html>
