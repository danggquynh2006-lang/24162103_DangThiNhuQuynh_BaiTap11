<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác Thực OTP - Tím Pastel</title>
    <style>
        body { background-color: #f8f5fc; font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .verify-card { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 15px rgba(177,156,217,0.3); width: 350px; border-top: 5px solid #b19cd9; }
        h2 { color: #6a4c93; text-align: center; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; color: #555; }
        input[type="text"] { width: 100%; padding: 10px; border: 1px solid #d8bfd8; border-radius: 6px; box-sizing: border-box; text-align: center; font-size: 18px; letter-spacing: 5px; }
        button { width: 100%; padding: 10px; background-color: #b19cd9; border: none; color: white; font-weight: bold; border-radius: 6px; cursor: pointer; transition: 0.3s; }
        button:hover { background-color: #9370db; }
        .error { color: #d9534f; text-align: center; font-size: 14px; margin-bottom: 10px; }
        .note { font-size: 12px; color: #666; text-align: center; margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="verify-card">
        <h2>Xác Thực OTP</h2>
        <div class="note">Mã xác nhận đã được gửi vào email của bạn.</div>
        <% if(request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="verify" method="post">
            <div class="form-group">
                <label>Nhập mã OTP (6 số):</label>
                <input type="text" name="otp" maxlength="6" required>
            </div>
            <button type="submit">Xác Nhận</button>
        </form>
    </div>
</body>
</html>