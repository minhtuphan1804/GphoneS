<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
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
    <div>
        <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
            <li class="nav-item">
                <a class="nav-link" href="/camera/hien-thi" role="tab" onclick="return myFunction4()">Thông tin Chip </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/camera/view-add" role="tab" onclick="return myFunction1()">Thêm thông tin Camera</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/camera/hien-thi-delete" role="tab" onclick="return myFunction3()">Camera đã xoá</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                   aria-controls="description" aria-selected="true">Update</a>
            </li>
        </ul>
    </div>
    <div>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
                <div>
                    <form:form action="/camera/update/${camera.id}" method="post" modelAttribute="camera">
                        <div class="card">
                            <div class="card-body" style="color: black">
                                <h4 class="card-title">Update Camera</h4>
                                <form class="forms-sample">
                                    <div class="form-group">
                                        <form:label path="thongSo"><b>Thông số Camera:</b></form:label>
                                        <form:input class="form-control" placeholder="" path="thongSo"/>
                                        <form:errors path="thongSo" cssStyle="color: red"/>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="moTa"><b>Mô tả:</b></form:label>
                                        <form:input class="form-control" placeholder="" path="moTa"/>
                                        <form:errors path="moTa" cssStyle="color: red"/>
                                    </div>
                                    <div style="text-align: center">
                                        <button type="submit" class="btn btn-primary mr-2"
                                                onclick="return myFunction2()">
                                            Cập Nhật Thông Tin
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function myFunction1() {
        let text = "Bạn chắc chắn muốn sang trang thêm thông tin";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction2() {
        let text = "Bạn chắc chắn muốn sửa";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction3() {
        let text = "Bạn chắc chắn muốn sang trang trạng thái";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction4() {
        let text = "Bạn chắc chắn muốn về lại trang hiển thị";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction5() {
        let text = "Bạn chắc chắn muốn sang trang thêm thông tin";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
</html>