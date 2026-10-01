<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>Chi tiết đơn hàng</title></head>
<body>

<h2 style="color: #6a4c93; border-bottom: 2px solid #e6e6fa; padding-bottom: 10px;">Chi Tiết Đơn Hàng #${order.orderId}</h2>

<div style="margin: 15px 0; line-height: 2;">
    <p><strong>Ngày đặt:</strong> <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy"/></p>
    <p><strong>Người nhận:</strong> ${order.fullName}</p>
    <p><strong>Số điện thoại:</strong> ${order.phone}</p>
    <p><strong>Địa chỉ:</strong> ${order.address}</p>
    <p><strong>Phương thức thanh toán:</strong> ${order.paymentMethod}</p>
    <p><strong>Trạng thái:</strong> <span style="background:#e8e3fb;color:#6a4c93;padding:4px 10px;border-radius:12px;font-size:13px;font-weight:bold;">${order.status}</span></p>
</div>

<table style="width: 100%; border-collapse: collapse; margin-top: 10px;">
    <thead>
        <tr style="background-color: #b19cd9; color: white; text-align: left;">
            <th style="padding: 10px;">Sản phẩm</th>
            <th>Đơn giá</th>
            <th>Số lượng</th>
            <th>Thành tiền</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="d" items="${order.details}">
            <tr style="border-bottom: 1px solid #e6e6fa;">
                <td style="padding: 10px;">
                    <img src="${pageContext.request.contextPath}/images/${not empty d.video.poster ? d.video.poster : 'gucci.jpg'}"
                         style="width: 50px; height: 50px; object-fit: cover; border-radius: 6px; vertical-align: middle; margin-right: 10px;">
                    ${d.video.title}
                </td>
                <td><fmt:formatNumber value="${d.price}" type="number" groupingUsed="true"/>&nbsp;đ</td>
                <td>${d.quantity}</td>
                <td><fmt:formatNumber value="${d.price * d.quantity}" type="number" groupingUsed="true"/>&nbsp;đ</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div style="text-align: right; margin-top: 15px; font-size: 18px; color: #6a4c93;">
    <strong>Tổng cộng: <fmt:formatNumber value="${order.totalAmount}" type="number" groupingUsed="true"/> đ</strong>
</div>

<a href="${pageContext.request.contextPath}/orders" style="display: inline-block; margin-top: 20px; background: #b19cd9; color: white; padding: 8px 15px; text-decoration: none; border-radius: 5px; font-weight: bold;">Quay lại lịch sử</a>

</body>
</html>