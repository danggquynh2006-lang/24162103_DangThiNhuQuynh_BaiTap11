<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head><title>Lịch sử đặt hàng</title></head>
<body>

<h2 style="color: #6a4c93; border-bottom: 2px solid #e6e6fa; padding-bottom: 10px;">Lịch Sử Đặt Hàng</h2>

<%-- Bo loc trang thai --%>
<div style="margin: 15px 0; display: flex; flex-wrap: wrap; gap: 8px;">
    <a href="${pageContext.request.contextPath}/orders"
       style="padding: 6px 14px; border-radius: 20px; text-decoration: none; font-size: 13px; font-weight: bold;
              ${empty selectedStatus ? 'background:#6a4c93;color:#fff;' : 'background:#f3e8ff;color:#6a4c93;'}">
       Tất cả
    </a>
    <c:forEach var="st" items="${statusList}">
        <a href="${pageContext.request.contextPath}/orders?status=${fn:escapeXml(st)}"
           style="padding: 6px 14px; border-radius: 20px; text-decoration: none; font-size: 13px; font-weight: bold;
                  ${st == selectedStatus ? 'background:#6a4c93;color:#fff;' : 'background:#f3e8ff;color:#6a4c93;'}">
           ${st}
        </a>
    </c:forEach>
</div>

<c:if test="${empty orders}">
    <p style="color: #999;">Không có đơn hàng nào ở trạng thái này.</p>
</c:if>

<c:if test="${not empty orders}">
<table style="width: 100%; border-collapse: collapse; margin-top: 10px;">
    <thead>
        <tr style="background-color: #b19cd9; color: white; text-align: left;">
            <th style="padding: 10px;">Mã đơn</th>
            <th>Ngày đặt</th>
            <th>Người nhận</th>
            <th>Tổng tiền</th>
            <th>Trạng thái</th>
            <th>Chi tiết</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="o" items="${orders}">
            <tr style="border-bottom: 1px solid #e6e6fa;">
                <td style="padding: 10px;">#${o.orderId}</td>
                <td><fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy"/></td>
                <td>${o.fullName}</td>
                <td><fmt:formatNumber value="${o.totalAmount}" type="number" groupingUsed="true"/>&nbsp;đ</td>
                <td>
                    <c:choose>
                        <c:when test="${o.status == 'Đơn hàng hủy'}">
                            <span style="background:#fde2e2;color:#d9534f;padding:4px 10px;border-radius:12px;font-size:12px;font-weight:bold;">${o.status}</span>
                        </c:when>
                        <c:when test="${o.status == 'Đã giao'}">
                            <span style="background:#e2f7e2;color:#2e8b2e;padding:4px 10px;border-radius:12px;font-size:12px;font-weight:bold;">${o.status}</span>
                        </c:when>
                        <c:when test="${o.status == 'Đơn hàng hoàn'}">
                            <span style="background:#fff3cd;color:#8a6d00;padding:4px 10px;border-radius:12px;font-size:12px;font-weight:bold;">${o.status}</span>
                        </c:when>
                        <c:otherwise>
                            <span style="background:#e8e3fb;color:#6a4c93;padding:4px 10px;border-radius:12px;font-size:12px;font-weight:bold;">${o.status}</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/order/detail?id=${o.orderId}" style="color:#8a4f9d;text-decoration:none;font-weight:bold;">Xem</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</c:if>

</body>
</html>