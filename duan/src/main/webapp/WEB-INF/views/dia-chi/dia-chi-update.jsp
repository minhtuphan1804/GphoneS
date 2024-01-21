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


<br>

    <P style="font-size: 50px;text-align: center">
        Địa chỉ
    </P>

<br>


<div>
    <ul class="nav nav-tabs border-top"
        id="setting-panel" role="tablist">
        <li class="nav-item">
            <a href="/dia-chi/hien-thi"
               role="tab"
               class="nav-link" >
                Thông tin Địa chỉ
            </a>
        </li>
        <li class="nav-item">

            <a class="nav-link active"
               id="description-tab"
            <%--                   data-toggle="tab"--%>
               href="/dia-chi/view-update/${dc.id}"
               role="tab"
               aria-controls="description"
               aria-selected="true">
                Update địa chỉ
            </a>
        </li>
        <li class="nav-item">
            <a href="/dia-chi/dia-chi-tung-xoa"
               role="tab"
               class="nav-link" >
                Địa chỉ từng xóa
            </a>
    </ul>
</div>


<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">

        <div class="card">
            <div class="card-body">

                <form:form action="/dia-chi/update" method="post" modelAttribute="dc" id="formlink">


                    <div style="display: none">
                        <form:input  path="id" />
                        <form:input   path="ma" />
                    </div>

                    <h4 style="font-size: 30px">UPDATE địa chỉ :
                        <LABEL style="text-align: center;margin-left:12cm;font-size: 20px">MÃ ĐỊA CHỈ :<label style="color: red;">${dc.ma}</label></LABEL>
                    </h4>
                    <br>
                    <table class="table" style="height: 100%">
                        <tr >
                            <th  >
                                <form:label class="form-label" path="diaChi">Địa chỉ: <form:errors path="diaChi" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:input id="tenhkh" class="form-control" placeholder="" path="diaChi" value=""/>
                            </th>

                            <th >

                                <form:label class="form-label" path="quan">Quận:
                                    <form:errors path="quan" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:input id="diemtoithieuhkh" class="form-control" placeholder=""  path="quan" />


                            </th>
                        </tr>
                        <tr>
                            <th >

                                <form:label class="form-label" path="huyen">Huyện: <form:errors path="huyen" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:input id="tenhkh" class="form-control" placeholder="" path="huyen" value=""/>



                            </th>

                            <th   >

                                <form:label class="form-label" path="thanhPho">Thành phố:
                                    <form:errors path="thanhPho" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:input id="diemtoithieuhkh" class="form-control" placeholder=""  path="thanhPho" />



                            </th>
                        </tr>
                        <tr>

                            <th  >
                                <form:label class="form-label" path="moTa">Mô tả:
                                    <form:errors path="moTa" cssStyle="color: red"></form:errors>
                                </form:label>
                                <form:textarea id="motahkh" class="form-control" placeholder="" path="moTa" />
                            </th>

                            <th>
                                <div  style="float: right;width: 15%;height: 50px;margin-right: 40px;margin-top: 23px">
                                    <button type="button"
                                            style="height: 100%"
                                            class="btn btn-primary"
                                            data-bs-toggle="modal"
                                            data-bs-target="#myModal">
                                        Thêm nhanh
                                    </button>
                                </div>

                                <div style="width: 75%;height: 60px;margin-bottom: 15px">
                                    <form:label class="form-label" path="khachHang">Khách hàng:
                                        <form:errors path="khachHang" cssStyle="color: red" />
                                    </form:label>
                                    <form:select  class="form-control"
                                                  path="khachHang"
                                                  items="${kh}"
                                                  itemValue="id"
                                                  itemLabel="KHMT"   ></form:select>

                                </div>

                            </th>
                        </tr>

                    </table>
                    <div align="center"> <button type="submit" class="btn btn-warning" id="bthkh"  onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">UPDATE</button></div>

                </form:form>

            </div>
        </div>
    </div>
</div>




























<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Thêm nhanh khách hàng</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal">X</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">

                <form:form action="/dia-chi/add-khach-hang/${dc.id}" method="post" modelAttribute="khmodal" >

                <table class="table">

                    <thead>
                    <tr>
                        <th >

                            <div class="form-floating mb-3 mt-3">
                                <form:label class="form-label" path="hoTen">Tên khách hàng:<label id="tenkh1" style="color: red"></label>
                                </form:label>
                                <form:input id="tenkh" class="form-control" placeholder="" path="hoTen" />

                            </div>

                        </th>
                    </tr>
                    <tr>
                        <th >

                            <div class="form-floating mb-3 mt-3">
                                <form:label class="form-label" path="gioiTinh">Giới Tính:</form:label>
                                <div class="form-control">
                                    <form:radiobutton path="gioiTinh" value="true"  checked="checked" />Nam
                                    <form:radiobutton path="gioiTinh" value="false"  cssStyle="margin-left: 1cm"/> Nữ
                                </div>

                            </div>

                        </th>
                    </tr>
                    <tr>
                        <th >

                            <div class="form-floating mb-3 mt-3">
                                <form:label class="form-label" path="sdt">Số điện thoại:<label id="sdtkh1" style="color: red"></label>
                                </form:label>
                                <form:input id="sdtkh" class="form-control" placeholder="" path="sdt" type="number" />

                            </div>

                        </th>
                    </tr>
                    </thead>
                </table>



            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="submit" class="btn btn-warning" id="btkh" onclick="return checkhkh()" >Thêm  khách hàng</button>
            </div>
            </form:form>
        </div>
    </div>
</div>

<script>
    function checkhkh() {
        var tenhkh=document.getElementById("tenkh").value;

        var sdtkh=document.getElementById("sdtkh").value;

        if(
            tenhkh.trim().length<6 || tenhkh==''
        ){
            document.getElementById("btkh").type="button";
            document.getElementById("tenkh1").innerHTML="Không để trống ,Tên ít nhất 6 ký tự";
            return  false;
        }else {
            document.getElementById("tenkh1").innerHTML="";
            var regex = /^0\d{9}$/;

            if( regex.test(sdtkh)){
                document.getElementById("sdtkh1").innerHTML="";

                document.getElementById("btkh").type="submit";
                return true;
            }else {

                document.getElementById("bthkh").type="button";
                document.getElementById("sdtkh1").innerHTML="SDT phảo 10 số và bắt đầu là số 0";
                return  false;
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