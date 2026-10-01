<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/> - Perfume &amp; Video Store</title>
    <style>
        html, body { height: 100%; margin: 0; padding: 0; }
        body { background-color: #f8f5fc; font-family: Arial, sans-serif; color: #4a2e4a; display: flex; flex-direction: column; }
        header { background-color: #b19cd9; color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 4px 6px rgba(177,156,217,0.2); }
        nav a { color: white; text-decoration: none; margin: 0 15px; font-weight: bold; transition: 0.3s; }
        nav a:hover { color: #f3e8ff; text-decoration: underline; }
        .user-greeting { color: #fff; font-weight: bold; margin: 0 10px; background: rgba(255, 255, 255, 0.2); padding: 5px 10px; border-radius: 4px; }
        .main-content { flex: 1; max-width: 1200px; width: 100%; margin: 20px auto; padding: 20px; background: white; border-radius: 12px; box-shadow: 0 2px 10px rgba(177, 156, 217, 0.15); box-sizing: border-box; }
        footer { background-color: #b19cd9; color: white; text-align: center; padding: 15px 0; margin-top: auto; font-weight: bold; box-shadow: 0 -2px 6px rgba(177,156,217,0.2); }
    </style>
    <sitemesh:write property="head"/>
</head>
<body>
    <%-- Decorator vai tro USER. Menu "Trang quan tri" va "Gio Hang" chi hien khi da dang nhap --%>
    <header>
        <h2>Perfume Store &amp; Video</h2>
        <nav>
            <a href="${pageContext.request.contextPath}/home">Trang Chủ</a>
            <a href="${pageContext.request.contextPath}/home">Sản Phẩm</a>
            <c:choose>
                <c:when test="${sessionScope.account != null}">
                    <a href="${pageContext.request.contextPath}/cart">Giỏ Hàng</a>
                    <span class="user-greeting">Xin chào, ${not empty sessionScope.account.fullname ? sessionScope.account.fullname : sessionScope.account.username}</span>
                    <c:if test="${sessionScope.account.admin}">
                        <a href="${pageContext.request.contextPath}/admin/videos">Trang Quản Trị</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login">Đăng Nhập</a>
                </c:otherwise>
            </c:choose>
        </nav>
    </header>

    <div class="main-content">
        <sitemesh:write property="body"/>
    </div>

    <footer>
        Họ tên: Đặng Thị Như Quỳnh | MSSV: 24162103 | Mã đề: Đề số 03
    </footer>
</body>
</html>