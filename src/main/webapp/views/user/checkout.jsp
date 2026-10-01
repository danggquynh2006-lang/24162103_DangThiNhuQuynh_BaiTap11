<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>Thanh toán</title></head>
<body>

<h2 style="color: #6a4c93; border-bottom: 2px solid #e6e6fa; padding-bottom: 10px;">Thanh Toán Đơn Hàng</h2>

<c:if test="${not empty error}">
    <p style="color: #d9534f; font-weight: bold;">${error}</p>
</c:if>

<div style="display: flex; gap: 30px; flex-wrap: wrap; margin-top: 15px;">
    <div style="flex: 1; min-width: 280px;">
        <h3 style="color: #6a4c93;">Thông tin nhận hàng</h3>
        <form action="${pageContext.request.contextPath}/checkout" method="post">
            <div style="margin-bottom: 15px;">
                <label style="display: block; font-weight: bold; margin-bottom: 5px;">Họ tên người nhận:</label>
                <input type="text" name="fullName" required style="width: 100%; padding: 8px; border: 1px solid #d8bfd8; border-radius: 5px; box-sizing: border-box;">
            </div>
            <div style="margin-bottom: 15px;">
                <label style="display: block; font-weight: bold; margin-bottom: 5px;">Số điện thoại:</label>
                <input type="text" name="phone" required style="width: 100%; padding: 8px; border: 1px solid #d8bfd8; border-radius: 5px; box-sizing: border-box;">
            </div>
            <div style="margin-bottom: 15px;">
                <label style="display: block; font-weight: bold; margin-bottom: 5px;">Địa chỉ nhận hàng:</label>
                <textarea name="address" rows="3" required style="width: 100%; padding: 8px; border: 1px solid #d8bfd8; border-radius: 5px; box-sizing: border-box;"></textarea>
            </div>
            <div style="margin-bottom: 15px;">
                <label style="font-weight: bold;">Phương thức thanh toán:</label>
                <p style="margin: 5px 0; padding: 8px; background: #f3e8ff; border-radius: 5px;">Thanh toán khi nhận hàng (COD)</p>
            </div>
            <button type="submit" style="background: #6a4c93; color: white; border: none; padding: 10px 20px; font-weight: bold; border-radius: 5px; cursor: pointer;">Đặt Hàng</button>
        </form>
    </div>

    <div style="flex: 1; min-width: 280px;">
        <h3 style="color: #6a4c93;">Đơn hàng của bạn</h3>
        <c:forEach var="item" items="${cartItems}">
            <div style="display: flex; justify-content: space-between; padding: 8px 0; border-bottom: 1px solid #e6e6fa;">
                <span>${item.video.title} x ${item.quantity}</span>
                <span><fmt:formatNumber value="${item.subTotal}" type="number" groupingUsed="true"/> đ</span>
            </div>
        </c:forEach>
        <div style="text-align: right; margin-top: 15px; font-size: 18px; color: #6a4c93;">
            <strong>Tổng cộng: <fmt:formatNumber value="${cartTotal}" type="number" groupingUsed="true"/> đ</strong>
        </div>
    </div>
</div>

</body>
</html>