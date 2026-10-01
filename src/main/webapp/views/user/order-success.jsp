<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>Đặt hàng thành công</title></head>
<body>

<div style="text-align: center; padding: 40px;">
    <h2 style="color: #6a4c93;">Đặt hàng thành công!</h2>
    <p>Mã đơn hàng: <strong>#${order.orderId}</strong></p>
    <p>Phương thức thanh toán: Thanh toán khi nhận hàng (COD)</p>
    <p>Tổng tiền: <strong><fmt:formatNumber value="${order.totalAmount}" type="number" groupingUsed="true"/> đ</strong></p>
    <p>Giao đến: ${order.fullName} - ${order.phone} - ${order.address}</p>
    <a href="${pageContext.request.contextPath}/home" style="display: inline-block; margin-top: 20px; background: #b19cd9; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; font-weight: bold;">Về Trang Chủ</a>
</div>

</body>
</html>