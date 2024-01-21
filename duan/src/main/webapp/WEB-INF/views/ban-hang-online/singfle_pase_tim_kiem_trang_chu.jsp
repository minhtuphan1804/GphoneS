<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<body>

<c:forEach items="${listsp}" var="ht" varStatus="stt">
    <a href="/ban-hang-online/chi-tiet-san-pham/${ht.id}" >
        <img src="../../../uploads/${ht.urlAnh}" width="30" height="30" style="border-radius:50% 50% 50% 50%;">
        <label style=" font-weight: normal;margin-left: 10px">${ht.sanPham.ten},màu:${ht.mauSac.ten},dung lượng:${ht.rom.dungLuong},..</label>
            <%--    <label style=" font-weight: normal;margin-left: 10px">${ht.basoOchammotlam()}vnd</label>--%>
        <c:if test="${banhangonline.tonggiamgia(ht.id)>0}">
            <label style=" font-weight: normal;margin-left: 10px">
                <label style="font-weight: bold">Đơn
                    giá:</label>
                                    ${banhangonline.sotienkhidagiam(ht.id)}
<%--                <script>--%>
<%--                    document.write(--%>
<%--                        Number('${banhangonline.sotienkhidagiam(ht.id)}'.replace(/,/g, '')).toLocaleString('de-DE')--%>
<%--                    );--%>
<%--                </script>--%>
                đ -
                <del class="product-old-price">
                                        ${ht.basoOchammotlam()}
<%--                    <script>--%>
<%--                        document.write(--%>
<%--                            Number('${ht.basoOchammotlam()}'.replace(/,/g, '')).toLocaleString('de-DE')--%>
<%--                        );--%>
<%--                    </script>--%>
                    đ</del>
            </label>
        </c:if>
        <c:if test="${banhangonline.tonggiamgia(ht.id)<=0}">
            <label style=" font-weight: normal;margin-left: 10px">
                <label style="font-weight: bold">Đơn
                    giá:</label>
                            ${ht.basoOchammotlam()}
<%--                <script>--%>
<%--                    document.write(--%>
<%--                        Number('${ht.basoOchammotlam()}'.replace(/,/g, '')).toLocaleString('de-DE')--%>
<%--                    );--%>
<%--                </script>--%>
                đ
            </label>
        </c:if>
    </a>
    <br>





</c:forEach>
</body>

</html>