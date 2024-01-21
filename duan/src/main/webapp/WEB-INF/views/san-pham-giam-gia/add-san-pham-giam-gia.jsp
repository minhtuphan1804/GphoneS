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
    <title>Focus - Bootstrap Admin Dashboard </title>
    <!-- Favicon icon -->
</head>
<body>
<div class="container" style="border: 1px solid whitesmoke ; height: 1000px">
    <h1 style="text-align: center">Sản Phẩm Giảm Giá</h1>
    <br>
    <div style="border: 1px solid grey">
        <div>
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a href="/san-pham-giam-gia/hien-thi" role="tab" onclick="return myFunction4()">
                        <h6>Thông tin sản phẩm giảm giá</h6>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/san-pham-giam-gia/hien-thi-khoi-phuc" role="tab" onclick="return myFunction5()">
                        <h6>Khôi phục dữ liệu </h6>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" id="review-tab" data-toggle="tab" href="#review" role="tab"
                       aria-controls="review" aria-selected="true">
                        <h6>Thêm sản phẩm giảm giá</h6>
                    </a>
                </li>
            </ul>
        </div>
        <div>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
                    <div>
                        <form:form action="/san-pham-giam-gia/add" method="post" modelAttribute="sanphamgiamgia">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-floating mb-3 mt-3">

                                        <form:select path="chiTietSanPham" class="form-control" >
                                            <option selected disabled>Sản phẩm</option>
                                            <form:options items="${listCTSP}" itemLabel="sanPham.ten" itemValue="id"/>
                                        </form:select>
                                        <form:label class="form-label" path="chiTietSanPham">Sản phẩm:</form:label>
                                        <a type="button" href="/chi-tiet-san-pham/view-add">
                                            <img src="../img/plus.png">
                                        </a>
                                    </div>
                                    <div class="form-floating mb-3 mt-3">
                                        <form:label class="form-label" path="khuyenMai">Khuyến mãi:</form:label>
                                        <form:select path="khuyenMai" class="form-control">
                                            <form:options items="${listKM}" itemLabel="ten" itemValue="id"/>
                                        </form:select>


                                    </div>
                                    <div class="form-check mb-3 mt-3">
                                        <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                                        <form:radiobutton path="tinhTrang" value="0"/>Không hoạt động
                                        <form:radiobutton path="tinhTrang" value="1" checked="true"/> Hoạt động
                                        <form:errors path="tinhTrang" cssStyle="color: red"></form:errors>
                                    </div>
                                    <div class="form-floating mb-3 mt-3">
                                        <form:input class="form-control" placeholder="" path="moTa"/>
                                        <form:label class="form-label" path="moTa">Mô tả:</form:label>
                                        <form:errors path="moTa" cssStyle="color: red"></form:errors>                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12" style="text-align: center">
                                        <button type="submit" class="btn btn-success"
                                                id="btt" onclick="return myFunction1()">Add
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
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