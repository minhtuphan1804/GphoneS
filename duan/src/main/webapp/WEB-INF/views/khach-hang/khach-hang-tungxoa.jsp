<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>--%>
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

            <a class="nav-link active"
               id="description-tab"
            <%--                   data-toggle="tab"--%>
               href="/khach-hang/khach-hang-tung-xoa" role="tab"
               aria-controls="description"
               aria-selected="true">

                Khách hàng từng xóa
            </a>
        </li>
        <li class="nav-item">
            <a href="/khach-hang/view-add"
               role="tab"
               class="nav-link">
                Thêm khách hàng
            </a>
        </li>
    </ul>
</div>

<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">

        <div class="card">
            <div class="card-body">
                <a href="/khach-hang/khoi-phuc-het" class="btn btn-danger" style="float: right"
                   onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Khôi phục
                    hết</a>
                <form action="/khach-hang/tim-kiem-tung-xoa" method="post" style="margin-right: 10cm;">
                    <div class="input-group" style="width: 30%; float: right">
                        <input style="height: 1cm" type="text" class="form-control" name="matk"
                               placeholder="Mã hoặc tên">
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-sm btn-primary"
                                    onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                Tìm kiếm
                            </button>
                        </div>
                    </div>
                </form>
                <br>
                <div class="col-sm-12">
                    <div class="card-box table-responsive">
                        <table id="example" class="display" style="width: 2500px;color: black">
                            <thead>
                            <tr>
                                <th>Ảnh</th>
                                <th>Ngày tạo</th>
                                <th>Mã</th>
                                <th>Tên</th>
                                <th>Giới tính</th>
                                <th>Email</th>
                                <th>Sdt</th>
                                <th>Ngày sinh</th>
                                <th>Ngày cập nhật</th>
                                <th>Tình trạng</th>
                                <th>Điểm</th>
                                <th>Hãng khách hàng</th>
                                <th>Chức năng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${dulieu}" var="ht" varStatus="stt">
                                <tr>

                                    <td align="center">
                                        <img src="../../../uploads/${ht.anh}" width="40" height="40"
                                             style="border-radius:50% 50% 50% 50%">

                                    </td>
                                    <td>${ht.ngayTao}</td>
                                    <td>${ht.ma}</td>
                                    <td>${ht.hoTen} </td>
                                    <td>${ht.goitinh()}</td>
                                    <td>${ht.email}</td>
                                    <td>${ht.sdt}</td>
                                    <td> ${ht.dateok()}</td>
                                    <td>${ht.ngayCapNhat}</td>
                                    <td>${ht.tt()}</td>
                                    <td>${ht.diem}</td>
                                    <td>${ht.hangKhachHang.ten}</td>

                                    <td>
                                        <a href="/khach-hang/khoi-phuc/${ht.id}" class="btn btn-success"
                                           onclick="return tbxd()">Khôi phục
                                        </a>
                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>


    if ("${tong}" <= 0) {
        document.getElementById("bang").style.display = "none"
        document.getElementById("bc").innerText = "Hết để xóa rồi cu"
    } else {
        document.getElementById("bang").style.display = ""
        document.getElementById("bc").innerText = ""
    }


    function thongbao() {
        var ns = document.getElementById("ns").value;
        if (confirm("Bạn muốn dùng trức năng") == true) {
            if (ns.trim() === '') {
                document.getElementById("tb").innerHTML = "Không để trống ngày sinh";
                document.getElementById("bt").type = "button"
                return false;
            } else {
                document.getElementById("bt").type = "submit"
                return true;
            }


        }
    }


    function tbxd() {
        if (confirm("Bạn muốn dùng chức năng") == true) {
            return true;
        }
        return false;
    }


    // function ktmd() {
    //     var kt = document.getElementById("vocuc").innerHTML
    //     if (kt.trim() === '') {
    //         document.getElementById("ktvocuc").innerHTML = "ko de trong"
    //         document.getElementById("btkt").type = "button"
    //         return false;
    //     } else {
    //         document.getElementById("btkt").type = "submit"
    //         return true;
    //     }
    // }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
</body>

</html>