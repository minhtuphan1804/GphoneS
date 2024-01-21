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
            <a class="nav-link" href="/dung-luong-pin/hien-thi" role="tab"  onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin dung lượng Pin</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thêm thông tin dung lượng pin</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/dung-luong-pin/hien-thi-delete" role="tab">Dung lượng Pin đã xoá</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form:form action="/dung-luong-pin/add" method="post" modelAttribute="dungLuongPin">
            <div class="card">
                <div class="card-body" style="color: black">
                    <h4 class="card-title">Thêm dung lượng pin</h4>
                    <form class="forms-sample">
                        <div class="form-group">
                            <form:label path="thongSo"><b>Thông số:</b></form:label>
                            <form:input path="thongSo" class="form-control"></form:input>
                            <form:errors path="thongSo" cssStyle="color: red"></form:errors>
                        </div>
                        <div class="form-group">
                            <form:label path="moTa"><b>Mô Tả:</b></form:label>
                            <form:textarea path="moTa" class="form-control"></form:textarea>
                            <form:errors path="moTa"></form:errors>
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
</html>
