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
    <!-- Favicon icon -->
</head>
<body>
<div style="border: 1px solid white;">
    <div>
        <ul class="nav nav-tabs border-top"
            id="setting-panel" role="tablist">
            <li class="nav-item">
                <a href="/khach-hang/hien-thi"
                   role="tab"
                   class="nav-link">
                    Thông tin khách hàng
                </a>
            </li>
            <li class="nav-item">
                <a href="/khach-hang/view-add"
                   role="tab"
                   class="nav-link">
                    Thêm khách hàng
                </a>
            </li>


            </li>
            <li class="nav-item">
                <a href="/khach-hang/khach-hang-tung-xoa"
                   role="tab"
                   class="nav-link">
                    Khách hàng từng xóa
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link active"
                   id="description-tab"
                <%--                   data-toggle="tab"--%>
                   href="/khach-hang/view-update/${kh.id}"
                   role="tab"
                   aria-controls="description"
                   aria-selected="true">
                    Sửa khách hàng
                </a>
        </ul>
    </div>


    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">

            <div class="card">
                <div class="card-body">
                    <form:form action="/khach-hang/update" method="post" modelAttribute="kh"
                               enctype="multipart/form-data">
                        <P style="display: none">
                            <form:input path="id"></form:input>
                        </P>
                        <div style="display: none">
                            <input style="" type="text" name="checkanh" value="cu" id="cucheck">
                            <br>
                        </div>
                        <div align="center">
                            <br>
                            <label style="border: 5px solid white;width: 150px;height: 150px;border-radius:50% 50% 50% 50%;"
                                   for="anhmoi">
                                <img id="preview-image-2" class="preview-image" src="../../../uploads/${kh.anh}" alt=""
                                     width="100%" height="100%"
                                     style="border-radius:50% 50% 50% 50%;border: 2px solid #8c8c8c">

                                <br><br>
                                ẢNH
                            </label>
                            <br>
                            <div style="display: none">
                                <input type="file" name="images" accept="image/jpeg, image/png" id="anhmoi">
                            </div>
                        </div>
                        <div style="display: none">
                            <form:input path="anh"/>
                            <form:input path="ngayTao" type="date"/>
                            <form:input class="form-control" placeholder="" path="matKhau"/>
                            <form:input class="form-control" placeholder="" path="taiKhoan"/>
                        </div>
                        <form class="form-sample">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label class="col-sm-3 col-form-label" path="hoTen">Họ tên:
                                            <form:errors path="hoTen" cssStyle="color: red"></form:errors>
                                        </form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="hoTen"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label class="col-sm-3 col-form-label"
                                                    path="gioiTinh">Giới Tính:</form:label>
                                        <div class="col-sm-9">
                                            <div class="form-control">
                                                <form:radiobutton path="gioiTinh" value="true"/>Nam
                                                <form:radiobutton path="gioiTinh" value="false"
                                                                  cssStyle="margin-left: 1cm"/> Nữ
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label class="col-sm-3 col-form-label" path="email">Email:
                                            <form:errors path="email" cssStyle="color: red"></form:errors>
                                        </form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="email"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label class="col-sm-3 col-form-label" path="sdt">Sdt:
                                            <form:errors path="sdt" cssStyle="color: red"></form:errors>
                                        </form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="sdt"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label class="col-sm-3 col-form-label" path="ngaySinh">
                                            Ngày sinh:
                                            <div id="tb" style="color: crimson;float: right"></div>
                                        </form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="ngaySinh" type="date"
                                                        id="ns"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label class="col-sm-3 col-form-label" path="taiKhoan">Tài khoản:
                                            <form:errors path="taiKhoan" cssStyle="color: red"></form:errors>
                                        </form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="taiKhoan"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label class="col-sm-3 col-form-label" path="diem">Điểm:
                                            <form:errors path="diem" cssStyle="color: red"></form:errors>
                                        </form:label>
                                        <div class="col-sm-9">
                                            <form:input class="form-control" placeholder="" path="diem" type="number"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <form:label class="col-sm-3 col-form-label"
                                                    path="hangKhachHang">Hãng khách hàng:
                                            <form:errors path="hangKhachHang" cssStyle="color: red"/>
                                        </form:label>
                                        <div class="col-sm-9">
                                            <form:select class="form-control"
                                                         path="hangKhachHang"
                                                         items="${hkh}"
                                                         itemValue="id"
                                                         itemLabel="ten"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div style="text-align: center">
                                        <BUTTON type="submit" class="btn btn-warning" style="" id="bt"
                                                onclick="return thongbao()">Thêm Thông Tin
                                        </BUTTON>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <%----%>


    <!-- The Modal -->
    <div class="modal" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Hãng khách hàng</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal">X</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">

                    <form:form action="/khach-hang/them-hang-khach-hang-update" method="post"
                               modelAttribute="KHHangKhachHang">
                    <input name="idkh" value="${kh.id}" type="text" style="display: none">
                    <table class="table">

                        <thead>
                        <tr>
                            <th>

                                <form:label class="form-label"
                                            path="ten">Tên hạng:<label id="tenhkh1" style="color: red"></label>
                                </form:label>

                                <form:input id="tenhkh" class="form-control" placeholder="" path="ten"/>


                            </th>
                        </tr>
                        <tr>
                            <th>

                                <form:label class="form-label"
                                            path="diem_toi_thieu">Điểm tối thiểu:<label id="diemtoithieuhkh1" style="color: red"></label>
                                </form:label>
                                <form:input id="diemtoithieuhkh" class="form-control" placeholder=""
                                            path="diem_toi_thieu"/>


                            </th>
                        </tr>
                        <tr>
                            <th>

                                <form:label class="form-label"
                                            path="moTa">Mô tả:<label id="motahkh1" style="color: red"></label>
                                </form:label>
                                <form:input id="motahkh" class="form-control" placeholder="" path="moTa"/>


                            </th>
                        </tr>
                        </thead>
                    </table>


                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-warning" id="bthkh" onclick="return checkhkh()">Thêm hãng khách
                        hàng
                    </button>
                </div>
                </form:form>
            </div>
        </div>
    </div>


</body>

<script>


    const imageInput = document.getElementById('anhmoi');

    const previewImage2 = document.getElementById('preview-image-2');

    imageInput.addEventListener('change', function () {

        const file = imageInput.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                previewImage2.src = e.target.result;
            };
            reader.readAsDataURL(file);
        } else {
            previewImage2.src = '';
        }
        document.getElementById('cucheck').value = 'moi';
    });
</script>
<script>


    function checkhkh() {
        var tenhkh = document.getElementById("tenhkh").value;
        var diemtoithieuhkh = document.getElementById("diemtoithieuhkh").value;
        var motahkh = document.getElementById("motahkh").value;

        if (
            tenhkh.trim().length < 6 || tenhkh == ''
        ) {
            document.getElementById("bthkh").type = "button";
            document.getElementById("tenhkh1").innerHTML = "Không để trống ,Tên ít nhất 6 ký tự";
            return false;
        } else {
            document.getElementById("tenhkh1").innerHTML = "";
            if (

                diemtoithieuhkh <= 0 || diemtoithieuhkh % 1 != 0
            ) {
                document.getElementById("bthkh").type = "button";
                document.getElementById("diemtoithieuhkh1").innerHTML = "Phải là số nguyên dương ";
                return false;
            } else {
                document.getElementById("diemtoithieuhkh1").innerHTML = "";
                if (
                    motahkh.trim() === ''
                ) {
                    document.getElementById("bthkh").type = "button";
                    document.getElementById("motahkh1").innerHTML = "Không để trống ";
                    return false;
                } else {
                    document.getElementById("bthkh").type = "submit";
                    return true;
                }
            }
        }


    }
</script>
<script>

    function thongbao() {
        var ns = document.getElementById("ns").value;
        if (confirm("Bạn muốn dùng trức năng") == true) {
            if (ns.trim() === '') {
                document.getElementById("tb").innerHTML = "Không để trống ngày sinh";
                document.getElementById("bt").type = "button"
                return false;
            } else {
                document.getElementById("bt").type = "submit";
                return true;
            }
        } else {
            return false;
        }


    }

    // var checkbox = document.getElementsByName("checkanh");
    // for (var i = 0; i < checkbox.length; i++){
    //     if (checkbox[i].checked === true){
    //         if (checkbox[i].value === 'cu'){
    //             document.getElementById('anhcu').style.display=""
    //             document.getElementById('anhmoi').style.display="none"
    //         }else {
    //             document.getElementById('anhcu').style.display="none"
    //             document.getElementById('anhmoi').style.display=""
    //         }}}


    // document.body.addEventListener('change', function (e) {
    //     let target = e.target;
    //
    //     switch (target.id) {
    //         case 'cucheck':
    //             document.getElementById('anhcu').style.display=""
    //             document.getElementById('anhmoi').style.display="none"
    //             break;
    //         case 'moicheck':
    //             document.getElementById('anhcu').style.display="none"
    //             document.getElementById('anhmoi').style.display=""
    //             break;
    //
    //     }
    // });
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
</html>