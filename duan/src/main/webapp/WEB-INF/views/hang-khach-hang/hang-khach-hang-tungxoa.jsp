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
    Hạng khách hàng từng xóa
</P>
<br>

<div>
    <ul class="nav nav-tabs border-top"
        id="setting-panel" role="tablist">
        <li class="nav-item">
            <a href="/hang-khach-hang/hien-thi"
               role="tab"
               class="nav-link" >
                Thông tin hạng khách hàng
            </a>
        </li>
        <li class="nav-item">

            <a class="nav-link active"
               id="description-tab"
            <%--                   data-toggle="tab"--%>
               href="/hang-khach-hang/hang-khach-hang-tung-xoa" role="tab"
               aria-controls="description"
               aria-selected="true">
                Hạng khách hàng tung xóa
            </a>
        </li>
    </ul>
</div>


<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">

        <div class="card">
            <div class="card-body">



<a href="/hang-khach-hang/hang-khach-hang-tungxoa/khoi-phuc-het" class="btn btn-danger" style="float: right"  onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Khôi phục hết</a>

<form action="/hang-khach-hang/tim-kiem-tung-xoa" method="post" style="margin-right: 10cm;">
    <div class="input-group" style="width: 30%; float: right">
        <input style="height: 1cm" type="text" class="form-control" name="matk" placeholder="Mã hoặc tên">
        <div class="input-group-append">
            <button type="submit" class="btn btn-sm btn-primary"  onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Tìm kiếm</button>
        </div>
    </div>
</form>

<%--<h2>Danh sách hạng khách hàng xóa </h2>--%>

                <div class="col-sm-12">
                    <div class="card-box table-responsive">
<table class="table table-striped">
    <thead>
    <tr>
        <th>STT</th>
        <th>Mã</th>
        <th>Tên</th>
        <th>Điểm tối thiểu</th>
        <th>Ngày tạo</th>
        <th>Ngày cập nhật</th>
        <th>Tình trạng   </th>
        <th>Mô tả        </th>
        <th>Chức năng</th>

    </tr>
    </thead>

    <c:forEach items="${dulieu}" var="ht" varStatus="stt">
        <tr>
            <td>${stt.index+1}</td>
            <td>${ht.ma}</td>

            <td>${ht.ten} </td>
            <td>${ht.diem_toi_thieu}</td>
            <td>${ht.ngayTao}</td>
            <td>${ht.ngayCapNhat}</td>
            <td style="color: red">${ht.tt()}</td>
            <td>${ht.moTa}</td>
            <td>
                <a href="/hang-khach-hang/hang-khach-hang-tungxoa/khoi-phuc/${ht.id}" class="btn btn-success" onclick="return tb()">Khôi phục</a>

            </td>
        </tr>
    </c:forEach>
</table>

                    </div>
                </div>

<br>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-lg">
            <li class="page-item"><a class="page-link" href="/hang-khach-hang/hang-khach-hang-tung-xoa?num=0">  < </a></li>

            <c:forEach begin="1" end="${total}" varStatus="status">
                <li class="page-item">
                    <a href="/hang-khach-hang/hang-khach-hang-tung-xoa?num=${status.index-1}"
                       class="page-link">${status.index}</a>
                </li>
            </c:forEach>

            <li class="page-item"><a class="page-link" href="/hang-khach-hang/hang-khach-hang-tung-xoa?num=${total-1}"> > </a></li>
        </ul>
    </nav>





    <P id="bc" style="color: crimson"></P>

            </div>
        </div>
    </div>
</div>


<script>


    function viewadd() {
        document.getElementById("idhkh").value='';
        document.getElementById("mahkh").value='';
        document.getElementById("tenhkh").value='';
        document.getElementById("diemtoithieuhkh").value='';
        document.getElementById("motahkh").value='';
        document.getElementById("formlink").action="/hang-khach-hang/add";
        document.getElementById("bthkh").innerHTML="Thêm hạng khách hàng";
    }
    function viewud(ma) {

        <c:forEach items="${service.getALL0()}" var="ht"  >

        if("${ht.ma}"===ma.trim()){
            document.getElementById("idhkh").value='${ht.id}';
            document.getElementById("mahkh").value='${ht.ma}';
            document.getElementById("tenhkh").value='${ht.ten}';
            document.getElementById("diemtoithieuhkh").value='${ht.diem_toi_thieu}';
            document.getElementById("motahkh").value='${ht.moTa}';
            document.getElementById("formlink").action="/hang-khach-hang/update";
            document.getElementById("bthkh").innerHTML="Update hạng khách hàng";

        }

        </c:forEach>

    }
</script>
<script>
    function checkhkh() {
        var tenhkh=document.getElementById("tenhkh").value;
        var diemtoithieuhkh=document.getElementById("diemtoithieuhkh").value;
        var motahkh=document.getElementById("motahkh").value;

        if(
            tenhkh.trim().length<6 || tenhkh==''
        ){
            document.getElementById("bthkh").type="button";
            document.getElementById("tenhkh1").innerHTML="Không để trống ,Tên ít nhất 6 ký tự";
            return  false;
        }else {
            document.getElementById("tenhkh1").innerHTML="";
            if(

                diemtoithieuhkh <= 0 || diemtoithieuhkh % 1 != 0
            ){
                document.getElementById("bthkh").type="button";
                document.getElementById("diemtoithieuhkh1").innerHTML="Phải là số nguyên dương ";
                return  false;
            }else {
                document.getElementById("diemtoithieuhkh1").innerHTML="";
                if(
                    motahkh.trim()===''
                ){
                    document.getElementById("bthkh").type="button";
                    document.getElementById("motahkh1").innerHTML="Không để trống ";
                    return  false;
                }else {
                    document.getElementById("bthkh").type="submit";
                    return true;
                }
            }
        }



    }
</script>
<script>
    if ("${tong}" <= 0) {
        document.getElementById("bang").style.display = "none"
        document.getElementById("bc").innerText = "Hết để xóa rồi "
    } else {
        document.getElementById("bang").style.display = ""
        document.getElementById("bc").innerText = ""
    }

    function tb() {
        var dtt = document.getElementById("dtt").value;
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