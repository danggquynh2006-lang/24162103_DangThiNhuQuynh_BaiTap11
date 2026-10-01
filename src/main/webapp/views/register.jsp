<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Ký - Tím Pastel</title>
    <style>
        body { background-color: #f8f5fc; font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .reg-card { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(177,156,217,0.3); width: 380px; border-top: 5px solid #b19cd9; }
        h2 { color: #6a4c93; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; color: #555; }
        input { width: 100%; padding: 10px; border: 1px solid #d8bfd8; border-radius: 6px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #b19cd9; border: none; color: white; font-weight: bold; border-radius: 6px; cursor: pointer; }
        button:hover { background-color: #9370db; }
    </style>
</head>
<body>
    <div class="reg-card">
        <h2>Đăng Ký Tài Khoản</h2>
        <% if(request.getAttribute("error") != null) { %>
            <div style="color:#d9534f;text-align:center;margin-bottom:10px;"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="register" method="post">
            <div class="form-group">
                <label>Tài khoản:</label>
                <input type="text" name="username" required>
            </div>
            <div class="form-group">
                <label>Mật khẩu:</label>
                <input type="password" name="password" required>
            </div>
            <div class="form-group">
                <label>Email (Nhận mã OTP):</label>
                <input type="email" name="email" required>
            </div>
            <button type="submit">Đăng Ký & Nhận OTP</button>
        </form>
    </div>
</body>
</html>