<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>Giỏ hàng</title></head>
<body>

<h2 style="color: #6a4c93; border-bottom: 2px solid #e6e6fa; padding-bottom: 10px;">Giỏ Hàng Của Bạn</h2>

<c:if test="${empty cartItems}">
    <p style="color: #999;">Giỏ hàng của bạn đang trống. <a href="${pageContext.request.contextPath}/home">Tiếp tục mua sắm</a></p>
</c:if>

<c:if test="${not empty cartItems}">
<table style="width: 100%; border-collapse: collapse; margin-top: 10px;">
    <thead>
        <tr style="background-color: #b19cd9; color: white; text-align: left;">
            <th style="padding: 10px;">Sản phẩm</th>
            <th>Đơn giá</th>
            <th>Số lượng</th>
            <th>Thành tiền</th>
            <th>Hành động</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${cartItems}">
            <tr style="border-bottom: 1px solid #e6e6fa;">
                <td style="padding: 10px;">
                    <img src="${pageContext.request.contextPath}/images/${not empty item.video.poster ? item.video.poster : 'gucci.jpg'}"
                         style="width: 60px; height: 60px; object-fit: cover; border-radius: 6px; vertical-align: middle; margin-right: 10px;">
                    ${item.video.title}
                </td>
                <td><fmt:formatNumber value="${item.video.price}" type="number" groupingUsed="true"/>&nbsp;đ</td>
                <td>
                    <form action="${pageContext.request.contextPath}/cart/update" method="post" style="display: inline;">
                        <input type="hidden" name="cartId" value="${item.cartId}">
                        <input type="number" name="quantity" value="${item.quantity}" min="1" max="10"
                               style="width: 55px; padding: 4px; border: 1px solid #d8bfd8; border-radius: 4px;">
                        <button type="submit" style="background: #b19cd9; color: white; border: none; padding: 4px 8px; border-radius: 4px; cursor: pointer;">Cập nhật</button>
                    </form>
                </td>
                <td><fmt:formatNumber value="${item.subTotal}" type="number" groupingUsed="true"/>&nbsp;đ</td>
                <td>
                    <a href="${pageContext.request.contextPath}/cart/delete?cartId=${item.cartId}"
                       style="color: #d9534f; text-decoration: none; font-weight: bold;"
                       onclick="return confirm('Xóa sản phẩm này khỏi giỏ hàng?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div style="text-align: right; margin-top: 20px; font-size: 18px; color: #6a4c93;">
    <strong>Tổng cộng: <fmt:formatNumber value="${cartTotal}" type="number" groupingUsed="true"/> đ</strong>
</div>

<div style="text-align: right; margin-top: 15px;">
    <a href="${pageContext.request.contextPath}/checkout" style="background: #6a4c93; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; font-weight: bold;">Tiến hành thanh toán</a>
</div>
</c:if>

</body>
</html>