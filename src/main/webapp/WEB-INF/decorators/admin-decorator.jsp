<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/> - Trang Quản Trị</title>
    <style>
        html, body { height: 100%; margin: 0; padding: 0; }
        body { background-color: #f3eefc; font-family: Arial, sans-serif; color: #4a2e4a; display: flex; flex-direction: column; }
        header { background-color: #6a4c93; color: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 4px 6px rgba(106,76,147,0.3); }
        nav a { color: white; text-decoration: none; margin: 0 15px; font-weight: bold; }
        nav a:hover { text-decoration: underline; }
        .admin-badge { background: #ffdd57; color: #6a4c93; font-weight: bold; padding: 4px 10px; border-radius: 4px; margin-right: 10px; }
        .main-content { flex: 1; max-width: 1200px; width: 100%; margin: 20px auto; padding: 20px; background: white; border-radius: 12px; box-shadow: 0 2px 10px rgba(106,76,147,0.15); box-sizing: border-box; }
        footer { background-color: #6a4c93; color: white; text-align: center; padding: 15px 0; margin-top: auto; font-weight: bold; }
    </style>
    <sitemesh:write property="head"/>
</head>
<body>
    <%-- Decorator vai tro ADMIN --%>
    <header>
        <h2>Trang Quản Trị</h2>
        <nav>
            <span class="admin-badge">ADMIN</span>
            <a href="${pageContext.request.contextPath}/home">Trang Chủ</a>
            <a href="${pageContext.request.contextPath}/admin/videos">Quản Lý Video</a>
            <span>Xin chào, ${not empty sessionScope.account.fullname ? sessionScope.account.fullname : sessionScope.account.username}</span>
            <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
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