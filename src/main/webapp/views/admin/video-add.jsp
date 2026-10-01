<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Thêm video</title></head>
<body>

<div style="background: #fff; padding: 25px; border-radius: 10px; border: 1px solid #e6e6fa; max-width: 600px; margin: 0 auto;">
    <h2 style="color: #6a4c93; margin-top: 0;">Thêm Video Mới</h2>

    <form action="${pageContext.request.contextPath}/admin/video/add" method="post" enctype="multipart/form-data">
        <div style="margin-bottom: 15px;">
            <label style="display: block; font-weight: bold; margin-bottom: 5px; color: #4a2e4a;">Mã Video:</label>
            <input type="text" name="videoId" required placeholder="Nhập mã video (VD: V18)" style="width: 100%; padding: 8px; border: 1px solid #d8bfd8; border-radius: 5px; box-sizing: border-box;">
        </div>

        <div style="margin-bottom: 15px;">
            <label style="display: block; font-weight: bold; margin-bottom: 5px; color: #4a2e4a;">Tiêu Đề Video:</label>
            <input type="text" name="title" required placeholder="Nhập tiêu đề video" style="width: 100%; padding: 8px; border: 1px solid #d8bfd8; border-radius: 5px; box-sizing: border-box;">
        </div>

        <div style="margin-bottom: 15px;">
            <label style="display: block; font-weight: bold; margin-bottom: 5px; color: #4a2e4a;">Category:</label>
            <select name="categoryId" required style="width: 100%; padding: 8px; border: 1px solid #d8bfd8; border-radius: 5px; box-sizing: border-box;">
                <c:forEach var="c" items="${categories}">
                    <option value="${c.categoryId}">${c.categoryName}</option>
                </c:forEach>
            </select>
        </div>

        <div style="margin-bottom: 15px;">
            <label style="display: block; font-weight: bold; margin-bottom: 5px; color: #4a2e4a;">Poster (tên file ảnh trong /images):</label>
            <input type="text" name="poster" placeholder="VD: dior.jpg" style="width: 100%; padding: 8px; border: 1px solid #d8bfd8; border-radius: 5px; box-sizing: border-box;">
        </div>

        <div style="margin-bottom: 15px;">
            <label style="display: block; font-weight: bold; margin-bottom: 5px; color: #4a2e4a;">Mô tả (Description):</label>
            <textarea name="description" rows="3" style="width: 100%; padding: 8px; border: 1px solid #d8bfd8; border-radius: 5px; box-sizing: border-box;"></textarea>
        </div>

        <div style="margin-bottom: 15px;">
            <label style="display: block; font-weight: bold; margin-bottom: 5px; color: #4a2e4a;">Lượt Xem (Views):</label>
            <input type="number" name="views" value="0" style="width: 100%; padding: 8px; border: 1px solid #d8bfd8; border-radius: 5px; box-sizing: border-box;">
        </div>

        <div style="margin-top: 20px;">
            <button type="submit" style="background: #b19cd9; color: white; border: none; padding: 10px 20px; font-weight: bold; border-radius: 5px; cursor: pointer;">Lưu Lại</button>
            <a href="${pageContext.request.contextPath}/admin/videos" style="margin-left: 10px; color: #6a4c93; text-decoration: none; font-weight: bold;">Hủy</a>
        </div>
    </form>
</div>

</body>
</html>