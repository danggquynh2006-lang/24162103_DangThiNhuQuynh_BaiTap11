<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Quản lý video</title></head>
<body>

<div style="background: #fff; padding: 25px; border-radius: 10px; border: 1px solid #e6e6fa;">
    <h2 style="color: #6a4c93; margin-top: 0;">Quản Trị Dữ Liệu Videos</h2>

    <a href="${pageContext.request.contextPath}/admin/video/add" style="background: #b19cd9; color: white; padding: 8px 15px; text-decoration: none; border-radius: 5px; font-weight: bold; display: inline-block; margin-bottom: 15px;">+ Thêm Video Mới</a>

    <table style="width: 100%; border-collapse: collapse; margin-top: 5px;">
        <thead>
            <tr style="background-color: #b19cd9; color: white; text-align: left;">
                <th style="padding: 10px;">Mã Video</th>
                <th>Tiêu Đề</th>
                <th>Category</th>
                <th>Views</th>
                <th>Trạng Thái</th>
                <th>Hành Động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="v" items="${listVideo}">
                <tr style="border-bottom: 1px solid #e6e6fa;">
                    <td style="padding: 10px;">${v.videoId}</td>
                    <td>${v.title}</td>
                    <td>${v.category != null ? v.category.categoryName : v.categoryId}</td>
                    <td>${v.views}</td>
                    <td>${v.active ? 'Active' : 'Locked'}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/video/edit?id=${v.videoId}" style="color: #8a4f9d; text-decoration: none; font-weight: bold; margin-right: 12px;">Sửa</a>
                        <a href="${pageContext.request.contextPath}/admin/video/delete?id=${v.videoId}" style="color: #d9534f; text-decoration: none; font-weight: bold;" onclick="return confirm('Bạn có chắc muốn xóa video này không?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty listVideo}">
                <tr><td colspan="6" style="padding: 15px; text-align: center; color: #999;">Chưa có video nào.</td></tr>
            </c:if>
        </tbody>
    </table>

    <%-- Phân trang 6 video / trang --%>
    <div style="text-align: center; margin-top: 20px; font-weight: bold; color: #8a4f9d;">
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/admin/videos?page=${currentPage - 1}" style="margin: 0 5px; text-decoration: none; color: #8a4f9d;">&lt;&lt; Trước</a>
        </c:if>
        <c:forEach begin="1" end="${totalPages}" var="p">
            <a href="${pageContext.request.contextPath}/admin/videos?page=${p}"
               style="margin: 0 5px; text-decoration: none; ${p == currentPage ? 'color:#fff;background:#b19cd9;padding:4px 10px;border-radius:4px;' : 'color:#8a4f9d;'}">${p}</a>
        </c:forEach>
        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/admin/videos?page=${currentPage + 1}" style="margin: 0 5px; text-decoration: none; color: #8a4f9d;">Sau &gt;&gt;</a>
        </c:if>
    </div>
</div>

</body>
</html>