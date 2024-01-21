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
    <title>GPhoneS Store</title>
    <!-- Favicon icon -->
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/rom/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin Rom</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thêm thông tin
                Rom</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/rom/hien-thi-tung-xoa" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Rom đã xoá</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form:form action="/rom/add" method="post" modelAttribute="dulieuxem">
            <div class="card">
                <div class="card-body" style="color: black">
                    <h4 class="card-title">Thêm Rom</h4>
                    <form class="forms-sample">
                        <div class="form-group">
                            <form:label path="dungLuong"><b>Dung lượng:</b></form:label>
                            <form:input type="text" class="form-control" id="dungLuong" path="dungLuong"
                                        />
                            <form:errors path="dungLuong" cssStyle="color: red"></form:errors>
                        </div>

                        <div class="form-group">
                            <form:label path="moTa"><b>Mô Tả:</b></form:label>
                            <form:textarea class="form-control" id="moTa" path="moTa" rows="3"></form:textarea>
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
        </form:form>
    </div>
</div>
</body>
</html>
