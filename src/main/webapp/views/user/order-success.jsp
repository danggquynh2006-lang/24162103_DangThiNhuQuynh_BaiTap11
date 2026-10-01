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
    <p>Trạng thái: <span style="background:#e8e3fb;color:#6a4c93;padding:4px 10px;border-radius:12px;font-size:13px;font-weight:bold;">${order.status}</span></p>

    <div style="margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/home" style="display: inline-block; background: #b19cd9; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; font-weight: bold;">Về Trang Chủ</a>
        <a href="${pageContext.request.contextPath}/orders" style="display: inline-block; margin-left: 10px; background: #8a4f9d; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; font-weight: bold;">Xem Lịch Sử Đơn Hàng</a>
    </div>
</div>

</body>
</html>