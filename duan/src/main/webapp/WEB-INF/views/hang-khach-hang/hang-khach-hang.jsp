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
    <P style="font-size: 50px;text-align: center">
       Hạng khách hàng
    </P>
<br>


    <div>
        <ul class="nav nav-tabs border-top"
            id="setting-panel" role="tablist">
            <li class="nav-item">
                <a class="nav-link active"
                   id="description-tab"
                <%--                   data-toggle="tab"--%>
                   href="/hang-khach-hang/hien-thi" role="tab"
                   aria-controls="description"
                   aria-selected="true">
                    Thông tin hạng khách hàng
                </a>
            </li>
            <li class="nav-item">
                <a href="/hang-khach-hang/hang-khach-hang-tung-xoa"
                   role="tab"
                   class="nav-link" >
                    Hạng khách hàng từng xóa
                </a>
            </li>
        </ul>
    </div>


    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">

            <div class="card">
                <div class="card-body">




                <form:form action="/hang-khach-hang/add" method="post" modelAttribute="HKHHangKhachHang" id="formlink">



                    <h4 style="font-size: 30px">Thêm Hạng khách hàng</h4>
                    <br>



                        <table class="table" style="height: 100%">


                            <tr>
                                <th >

                                    <form:label class="form-label" path="ten">Tên hạng: <form:errors path="ten" cssStyle="color: red"></form:errors>
                                    </form:label>
                                    <form:input id="tenhkh" class="form-control" placeholder="" path="ten" value=""/>

                                </th>


                            </tr>
                            <tr>
                                <th   >

                                    <form:label class="form-label" path="diem_toi_thieu">Điểm tối thiểu:
                                        <form:errors path="diem_toi_thieu" cssStyle="color: red"></form:errors>
                                    </form:label>
                                    <form:input id="diemtoithieuhkh" class="form-control" placeholder="" type="number" path="diem_toi_thieu" />

                                </th>
                            </tr>
                            <tr>
                                <th  >



                                        <form:label class="form-label" path="moTa">Mô tả:
                                            <form:errors path="moTa" cssStyle="color: red"></form:errors>
                                        </form:label>
                                        <form:textarea id="motahkh" class="form-control" placeholder="" path="moTa" />

                                </th>
                            </tr>




                        </table>




                    <div align="center"> <button type="submit" class="btn btn-warning" id="bthkh"  onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">ADD</button></div>


    </form:form>




<br>
    <br>



    <form action="/hang-khach-hang/tim-kiem" method="post" style="margin-left: 1cm;">
        <div class="input-group" style="width: 30%; float: right">
            <input style="height: 1cm" type="text" class="form-control" name="matk" placeholder="Mã hoặc Tên">
            <div class="input-group-append">
                <button type="submit" class="btn btn-sm btn-primary">Tìm kiếm</button>
            </div>
        </div>
    </form>


    <h2>Danh sách Hạng khách hàng</h2>

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
                <td style="color: #62c462">${ht.tt()}</td>
                <td>${ht.moTa}</td>
                <td>
                    <a href="/hang-khach-hang/remove/${ht.id}" class="btn btn-success" onclick="return tb()">Xóa</a>



                    <a href="/hang-khach-hang/view-update/${ht.id}"  class="btn btn-success"  onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Xem và Cập nhật</a>
                </td>
            </tr>
        </c:forEach>
    </table>

                        </div>
                    </div>
    <br>

    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-lg">
            <li class="page-item"><a class="page-link" href="/hang-khach-hang/hien-thi?num=0"> < </a></li>

            <c:forEach begin="1" end="${total}" varStatus="status">
                <li class="page-item">
                    <a href="/hang-khach-hang/hien-thi?num=${status.index-1}"
                       class="page-link">${status.index}</a>
                </li>
            </c:forEach>

            <li class="page-item"><a class="page-link" href="/hang-khach-hang/hien-thi?num=${total-1}">  > </a></li>
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


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
</html>