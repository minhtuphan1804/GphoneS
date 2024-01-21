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
    <title>GPhoneS Store </title>
    <!-- Favicon icon -->
    <style>
        container {
            width: 100%;
        }

        .row-1 {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            text-align: center;
        }
        .row-2 {
            margin-top: 40px;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            text-align: center;
        }

        .col-0, .col-1, .col-2, .col-3 {
            color: black;
            background-color:#8fe3f2;
            width: 25%;
            padding: 10px;
            margin-right: 1cm; /* Khoảng cách 1cm giữa các cột */
        }
        .col-4, .col-5, .col-6{
            color: black;
            background-color:#8fe3f2;
            width: 33.33%;
            padding: 10px;
            margin-right: 1cm; /* Khoảng cách 1cm giữa các cột */
        }

        .col-sm:hover {
            transform: scale(1.1); /* Phóng to cột khi hover */
            transition: transform 0.2s ease-in-out; /* Chuyển đổi kích thước cột một cách mượt mà khi hover */

        }
        .col-sm:last-child {
            margin-right: 0; /* Xóa khoảng cách bên phải của cột cuối cùng */

        }
        table {

            border-collapse: collapse;
        }
        #example{
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<div class="main" style="margin: -10px 10px 10px 10px">
    <div class="banner" style="margin-left: -15px; margin-top: -10px">
        <img src="../uploads/banner3.png" alt="Banner của trang web" width="1200px" height="250px">

    </div>
    <div  class="container" style="margin-top: 20px">
        <div class="row row-1">
            <div class="col-sm col-0" style="text-align: center">
                <p style="font-size: 20px; font-weight: bold"> Số sản phẩm trong kho</p>
                <p>Số mặt hàng: {}</p>

                <img src="../uploads/icondienthoai.jpg" height="50%" width="50%">

            </div>
            <div class="col-sm col-1">
                <p style="font-size: 20px; font-weight: bold">  Đơn hàng</p>
                <p>Số đơn hàng: {}</p>
                <img src="../uploads/donhang.png" height="50%" width="50%">

            </div>
            <div class="col-sm col-2">
                <p style="font-size: 20px; font-weight: bold">  Khách hàng</p>
                <p>Số khách hàng: {}</p>
                <img src="../uploads/iconkh.png" height="50%" width="50%">

            </div>
            <div class="col-sm col-3">
                <p style="font-size: 20px; font-weight: bold"> Bài Viết</p>
                <p>Số bài viết: {}</p>
                <img src="../uploads/iconsach.png" height="50%" width="50%">
            </div>
        </div>

        <div class="row row-2">
            <div class="col-sm col-4" style="text-align: center">
                <p style="font-size: 20px; font-weight: bold; border-bottom: 2px solid blue"> Đơn hàng mới</p>
                <img src="../uploads/icontop10do.png" height="50%" width="50%">

                <div class="table-active" style="margin-top: 20px">
                    <table id="example">
                        <tr><th>STT</th>
                            <th>Đơn hàng</th>
                            <th>Mô tả</th>
                        </tr>
                        <thead>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-sm col-5" style="text-align: center">
                <p style="font-size: 20px; font-weight: bold; border-bottom: 2px solid blue"> Sản phẩm bán chạy</p>
                <img src="../uploads/icontop10.png" height="50%" width="50%">
            </div>
            <div class="col-sm col-6" style="text-align: center">
                <p style="font-size: 20px; font-weight: bold; border-bottom: 2px solid blue"> Đơn hàng chưa sử lý</p>
                <div style="width: 100px; margin-bottom: 5px; height: 100px; border-radius: 50%; background-color: #d3edc5;">
                    <p style="text-align: center;padding-top: 35px;font-size: 20px; font-weight: bold">100</p>
                </div>
            </div>

        </div>

    </div>
</div>
</body>

</html>
