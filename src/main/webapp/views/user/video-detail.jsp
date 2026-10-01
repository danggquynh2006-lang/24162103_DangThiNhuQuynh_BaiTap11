<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head><title>Chi tiết video</title></head>
<body>

<div style="background: #fff; padding: 25px; border-radius: 10px; border: 1px solid #e6e6fa; max-width: 900px; margin: 0 auto;">
    <h2 style="color: #6a4c93; border-bottom: 2px solid #e6e6fa; padding-bottom: 10px;">Chi Tiết Video</h2>

    <c:if test="${video == null}">
        <p style="color: #d9534f;">Không tìm thấy video.</p>
    </c:if>

    <c:if test="${video != null}">
    <div style="display: flex; gap: 30px; margin-top: 20px; flex-wrap: wrap;">
        <div style="flex: 1; min-width: 260px;">
            <img src="${pageContext.request.contextPath}/images/${not empty video.poster ? video.poster : 'gucci.jpg'}"
                 alt="Poster" style="width: 100%; height: 280px; object-fit: cover; border-radius: 8px;"
                 onerror="this.src='${pageContext.request.contextPath}/images/gucci.jpg'">
        </div>

        <div style="flex: 1; min-width: 260px; font-size: 15px; color: #333; line-height: 2;">
            <p><strong>Tiêu đề:</strong> ${video.title}</p>
            <p><strong>Mã video:</strong> ${video.videoId}</p>
            <p><strong>Category name:</strong> ${video.category != null ? video.category.categoryName : ''}</p>
            <p><strong>View:</strong> ${video.views}</p>
            <p><strong>Share:</strong> (${shareCount})</p>
            <p><strong>Like:</strong> (${likeCount})</p>
        </div>
    </div>

    <div style="margin-top: 25px; border-top: 1px solid #e6e6fa; padding-top: 15px;">
        <h4 style="color: #6a4c93; margin-bottom: 5px;">Description:</h4>
        <p style="color: #666; font-size: 14px; line-height: 1.6;">${video.description}</p>
    </div>
    </c:if>
</div>

</body>
</html>