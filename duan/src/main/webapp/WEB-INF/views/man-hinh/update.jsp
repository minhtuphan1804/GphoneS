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
                <a class="nav-link" href="/man-hinh/hien-thi" role="tab" onclick="myFunction4()">Thông tin màn hình </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/man-hinh/view-add" role="tab"
                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm thông tin
                    màn hình</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/man-hinh/hien-thi-delete" role="tab"
                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Màn hình đã
                    xoá</a>
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
                    <form:form action="/man-hinh/update/${manHinh.id}" method="post" modelAttribute="manHinh">
                        <div class="col-12 grid-margin">
                            <div class="card">
                                <div class="card-body" style="color: black">
                                    <h4 class="card-title">Update màn hình</h4>
                                    <form class="form-sample">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label" path="thongSo"><b>Thông
                                                        số
                                                        màn hình:</b></form:label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder="" path="thongSo"/>
                                                        <form:errors path="thongSo" cssStyle="color: red"></form:errors>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label" path="loaiCamUng"><b>Loại
                                                        cảm ứng màn hình:</b></form:label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder=""
                                                                    path="loaiCamUng"/>
                                                        <form:errors path="loaiCamUng"
                                                                     cssStyle="color: red"></form:errors>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label" path="tiLeKhungHinh"><b>Tỉ
                                                        lệ khung hình:</b></form:label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder=""
                                                                    path="tiLeKhungHinh"/>
                                                        <form:errors path="tiLeKhungHinh"
                                                                     cssStyle="color: red"></form:errors>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label" path="doPhanGiai"><b>Độ
                                                        phân
                                                        giải:</b></form:label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder=""
                                                                    path="doPhanGiai"/>
                                                        <form:errors path="doPhanGiai"
                                                                     cssStyle="color: red"></form:errors>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label" path="tanSoQuet"><b>Tần
                                                        số
                                                        quét:</b></form:label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder=""
                                                                    path="tanSoQuet"/>
                                                        <form:errors path="tanSoQuet"
                                                                     cssStyle="color: red"></form:errors>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label" path="congNghe"><b>Công
                                                        nghệ
                                                        màn hình:</b></form:label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder=""
                                                                    path="congNghe"/>
                                                        <form:errors path="congNghe"
                                                                     cssStyle="color: red"></form:errors>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group row">
                                                    <form:label class="col-sm-3 col-form-label" path="moTa"><b>Mô
                                                        tả:</b></form:label>
                                                    <div class="col-sm-12">
                                                        <form:input class="form-control" placeholder="" path="moTa"/>
                                                        <form:errors path="moTa" cssStyle="color: red"></form:errors>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div style="text-align: center">
                                                    <button type="submit" class="btn btn-primary mr-2"
                                                            onclick="return myFunction2()">
                                                        Cập Nhật Thông Tin
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
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
        let text = "Bạn chắc chắn muốn thêm";
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
        let text = "Bạn chắc chắn muốn xóa";
        let kt = confirm(text);
        if (kt == true) {
            confirm("Xóa thành công");
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