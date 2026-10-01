<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập - Tím Pastel</title>
    <style>
        body { background-color: #f8f5fc; font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .login-card { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(177,156,217,0.3); width: 350px; border-top: 5px solid #b19cd9; }
        h2 { color: #6a4c93; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; color: #555; }
        input[type="text"], input[type="password"] { width: 100%; padding: 10px; border: 1px solid #d8bfd8; border-radius: 6px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #b19cd9; border: none; color: white; font-weight: bold; border-radius: 6px; cursor: pointer; transition: 0.3s; }
        button:hover { background-color: #9370db; }
        .error { color: #d9534f; text-align: center; font-size: 14px; margin-bottom: 10px; }
        .link { text-align: center; margin-top: 15px; font-size: 14px; }
        .link a { color: #9370db; text-decoration: none; }
    </style>
</head>
<body>
    <div class="login-card">
        <h2>Đăng Nhập</h2>
        <% if(request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="login" method="post">
            <div class="form-group">
                <label>Tài khoản:</label>
                <input type="text" name="username" required>
            </div>
            <div class="form-group">
                <label>Mật khẩu:</label>
                <input type="password" name="password" required>
            </div>
            <button type="submit">Đăng Nhập</button>
        </form>
        <div class="link">
            Chưa có tài khoản? <a href="register">Đăng ký ngay</a>
        </div>
    </div>
</body>
</html>