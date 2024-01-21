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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- SweetAlert2 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.2.0/sweetalert2.all.min.js"></script>

    <style>
        /* Tiêu đề */
        p{
            color: black;
        }
        h1 {
            color: black;
            font-size: 2em;
            font-weight: bold;
            margin-top: 20px;
        }

        /* Bảng */
        table {
            color: black;
            border-collapse: collapse;
            width: 100%;
        }

        th {
            font-size: 18px;
            background-color: #ccc;
            text-align: left;
            padding: 10px;
        }

        td {
            padding: 10px;
        }

        /* Phần nổi bật */
        .bold {
            font-weight: bold;
        }
    </style>
    <!-- Favicon icon -->
</head>
<body>
<div>    <H2> Các chính sách tại cửa hàng</H2>
    <br>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
<%--        <li class="nav-item">--%>
<%--            <a class="nav-link active" id="description-tab" data-toggle="tab" href="/imei/chinh-sach" role="tab"--%>
<%--               aria-controls="description" aria-selected="true" style="color: blue">Chính sách đổi - trả sản phẩm</a>--%>
<%--        </li>--%>
    <li class="nav-item">
        <a class="nav-link" href="/imei/chinh-sach" role="tab" style="color: black"
        >Chính sách đổi - trả sản phẩm</a>
    </li>
        <li class="nav-item">
            <a class="nav-link" href="/imei/chinh-sach-xu-ly-imei-loi" role="tab" style="color: blue"
            >Chính sách xử lý imei lỗi</a>
        </li>
    </ul>
</div>
<br>
<div class="card-body">

                    <h3>Chính sách xử lý imei lỗi (điện thoại lỗi) của cửa hàng</h3>

                    <table>
                        <tr>
                            <th>Nội dung/Loại lỗi</th>
                            <th>Cách xử lý của cửa hàng</th>
<%--                            <th>Cách xử lý của nhà sản xuất</th>--%>
                        </tr>
                        <tr>
                            <td><strong>Cửa hàng yêu cầu nhà sản xuất chuyển lại máy mới hoàn toàn cho cửa hàng để cửa hàng tiếp tục bán</strong></td>
                            <td>
                                Nếu lỗi phần cứng, lỗi phần mềm hoặc lỗi thiết kế là do nhà sản xuất gây ra, cửa hàng sẽ chuyển máy lỗi về nhà sản xuất và yêu cầu nhà sản xuất chuyển lại máy mới hoàn toàn cho cửa hàng.
                            </td>
                        <%--    <td>
                                Nhà sản xuất đồng ý đổi điện thoại mới cho cửa hàng sao cho phù hợp với chính sách bảo hành của nhà sản xuất (Cửa hàng có hợp đồng hợp tác kinh doanh với các nhà sản xuất)
                            </td>--%>


                        </tr>
                        <tr>
                            <td><strong>Lỗi phần cứng</strong></td>
                            <td>
                               Cửa hàng sẽ kiểm tra lỗi(Nếu cửa hàng đủ năng lực để kiểm tra) và gửi máy về nhà sản xuất và theo dõi quá trình xử lý của nhà sản xuất. Khi nhà sản xuất xử lý xong máy, cửa hàng điện thoại sẽ nhận máy mới
                            </td>
<%--                            <td>--%>
<%--                                Kiểm tra lỗi và xác định nguyên nhân gây ra lỗi. Nếu lỗi là do nhà sản xuất gây ra, nhà sản xuất sẽ chịu trách nhiệm sửa chữa và thay thế máy mới cho cửa hàng.--%>
<%--                            </td>--%>

                        </tr>

                        <tr>
                            <td><strong>Lỗi phần mềm</strong></td>
                            <td>
                               Cửa hàng gửi máy về nhà sản xuất. Nếu lỗi phần mềm là do nhà sản xuất gây ra thì khi nhà sản xuất xử lý xong máy, cửa hàng điện thoại sẽ nhận máy mới
                            </td>
<%--                            <td>--%>
<%--                                Nếu lỗi phần mềm là do nhà sản xuất gây ra Nếu lỗi là do nhà sản xuất gây ra,  nhà sản xuất sẽ chịu trách nhiệm sửa chữa và thay thế máy mới cho cửa hàng.--%>
<%--                            </td>--%>

                        </tr>

                        <tr>
                            <td><strong>Lỗi thiết kế</strong></td>

                            <td>
                                Kiểm tra lỗi và xác định nguyên nhân gây ra lỗi và gửi máy về nhà sản xuất và theo dõi quá trình xử lý của nhà sản xuất.  Khi nhà sản xuất xử lý xong máy, cửa hàng điện thoại sẽ nhận máy mới
                            </td>
<%--                            <td>--%>
<%--                                Kiểm tra lỗi và xác định nguyên nhân gây ra lỗi. Nếu lỗi là do nhà sản xuất gây ra, nhà sản xuất sẽ chịu trách nhiệm sửa chữa và thay thế máy mới cho cửa hàng.--%>
<%--                            </td>--%>
                        </tr>


                    </table>



                </div>

</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</html>
