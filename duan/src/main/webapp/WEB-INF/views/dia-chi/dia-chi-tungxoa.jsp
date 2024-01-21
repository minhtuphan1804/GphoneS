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




    <P style="font-size: 50px;text-align: center">
        Địa chỉ từng xóa
    </P>
<br>




    <div>
        <ul class="nav nav-tabs border-top"
            id="setting-panel" role="tablist">
            <li class="nav-item">
                <a href="/dia-chi/hien-thi"
                   role="tab"
                   class="nav-link" >
                    Thông tin dịa chỉ
                </a>
            </li>
            <li class="nav-item">

                <a class="nav-link active"
                   id="description-tab"
                <%--                   data-toggle="tab"--%>
                   href="/dia-chi/dia-chi-tung-xoa" role="tab"
                   aria-controls="description"
                   aria-selected="true">

                    Địa chỉ từng xóa
                </a>
            </li>
        </ul>
    </div>




    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">

            <div class="card">
                <div class="card-body">





                    <a href="/dia-chi/dia-chi-tungxoa/khoi-phuc-het" class="btn btn-danger" style="float: right"  onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Khôi phục hết</a>

                    <form action="/dia-chi/tim-kiem-tung-xoa" method="post" style="margin-right: 10cm;">
                        <div class="input-group" style="width: 30%; float: right">
                            <input style="height: 1cm" type="text" class="form-control" name="matk" placeholder="Mã hoặc địa chỉ">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-sm btn-primary"  onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Tìm kiếm</button>
                            </div>
                        </div>
                    </form>

                    <h2>Danh sách địa chỉ xóa </h2>
                    <div class="col-sm-12">
                        <div class="card-box table-responsive">

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã</th>
                            <th>Địa chỉ</th>
                            <th>Quận</th>
                            <th>Huyện</th>
                            <th>Thành phố</th>
                            <th>Ngày tạo</th>
                            <th>Ngày cập nhật</th>
                            <th>Tình trạng</th>
                            <th>Mô tả</th>
                            <th>Khách hàng</th>

                            <th>Chức năng</th>
                        </tr>
                        </thead>

                        <c:forEach items="${dulieu}" var="ht" varStatus="stt">
                            <tr>
                                <td>${stt.index+1}</td>
                                <td>${ht.ma}</td>
                                <td>${ht.diaChi} </td>
                                <td>${ht.quan}</td>
                                <td>${ht.huyen}</td>
                                <td>${ht.thanhPho}</td>

                                <td>${ht.ngayTao}</td>
                                <td>${ht.ngayCapNhat}</td>


                                <td style="color: red">${ht.tt()}</td>
                                <td> ${ht.moTa} </td>
                                <td> ${ht.khachHang.hoTen} </td>
                                <td>
                                    <a href="/dia-chi/dia-chi-tung-xoa/khoi-phuc/${ht.id}" class="btn btn-success" onclick="return tb()">Khôi phục</a>

                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                        </div>
                    </div>
<br>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center pagination-lg">
                            <li class="page-item"><a class="page-link" href="/dia-chi/dia-chi-tung-xoa?num=0"> < </a></li>

                            <c:forEach begin="1" end="${total}" varStatus="status">
                                <li class="page-item">
                                    <a href="/dia-chi/dia-chi-tung-xoa?num=${status.index-1}"
                                       class="page-link">${status.index}</a>
                                </li>
                            </c:forEach>

                            <li class="page-item"><a class="page-link" href="/dia-chi/dia-chi-tung-xoa?num=${total-1}">  > </a></li>
                        </ul>
                    </nav>





                    <P id="bc" style="color: crimson"></P>
                </div>
            </div>
        </div>
    </div>

<script>
    if ("${tong}" <= 0) {
        document.getElementById("bang").style.display = "none"
        document.getElementById("bc").innerText = "Hết để xóa rồi "
    } else {
        document.getElementById("bang").style.display = ""
        document.getElementById("bc").innerText = ""
    }

    function tb() {

        if (confirm("Bạn muốn dùng chức năng") == true) {


            return true;

        }
        return false;
    }

    function tbxd() {
        if (confirm("Bạn muốn dùng chức năng") == true) {
            return true;
        }
        return false;
    }
</script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
</body>


</html>