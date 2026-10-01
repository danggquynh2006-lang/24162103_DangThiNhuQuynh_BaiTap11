<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head><title>Trang chủ</title></head>
<body>

<h2 style="color: #6a4c93; border-bottom: 2px solid #e6e6fa; padding-bottom: 10px;">Trang Chủ Người Dùng</h2>

<c:if test="${empty categoryBlocks}">
    <p style="color: #999;">Hiện chưa có video nào.</p>
</c:if>

<%-- Câu 4: video theo từng Category, mỗi Category phân trang riêng 3 video/trang.
     Câu 5: số lượng video của từng Category hiển thị cạnh tên Category. --%>
<c:forEach var="block" items="${categoryBlocks}">
    <div style="margin-top: 25px;">
        <h3 style="background-color: #f3e8ff; padding: 10px; border-left: 5px solid #b19cd9; color: #4a2e4a;">
            ${block.categoryName} (${block.totalVideo})
        </h3>

        <div style="display: flex; gap: 20px; flex-wrap: wrap;">
            <c:forEach var="v" items="${block.videos}">
                <div style="background: #fff; border: 1px solid #e6e6fa; border-radius: 8px; width: 30%; padding: 15px; box-shadow: 0 2px 5px rgba(177,156,217,0.1); box-sizing: border-box; margin-bottom: 15px;">

                    <img src="${pageContext.request.contextPath}/images/${not empty v.poster ? v.poster : 'gucci.jpg'}"
                         alt="Poster" style="width: 100%; height: 140px; object-fit: cover; border-radius: 6px;"
                         onerror="this.src='${pageContext.request.contextPath}/images/gucci.jpg'">

                    <h4 style="margin: 10px 0 5px 0; color: #333;">Tiêu đề: ${v.title}</h4>
                    <p style="margin: 3px 0; font-size: 13px; color: #666;">Mã video: ${v.videoId}</p>
                    <p style="margin: 3px 0; font-size: 13px; color: #666;">Category name: ${block.categoryName}</p>
                    <p style="margin: 3px 0; font-size: 13px; color: #666;">View: ${v.views}</p>
                    <p style="margin: 3px 0; font-size: 13px; color: #8a4f9d; font-weight: bold;">Giá: <fmt:formatNumber value="${v.price}" type="number" groupingUsed="true"/> đ</p>
                    <p style="margin: 3px 0; font-size: 13px; color: #666;">Share (${v.shareCount})</p>
                    <p style="margin: 3px 0; font-size: 13px; color: #666;">Like (${v.likeCount})</p>

                    <c:if test="${sessionScope.account != null}">
                        <form action="${pageContext.request.contextPath}/cart/add" method="post" style="margin: 8px 0;">
                            <input type="hidden" name="videoId" value="${v.videoId}">
                            <input type="number" name="quantity" value="1" min="1" max="10"
                                   style="width: 50px; padding: 4px; border: 1px solid #d8bfd8; border-radius: 4px;">
                            <button type="submit" style="background: #8a4f9d; color: white; border: none; padding: 5px 10px; border-radius: 4px; cursor: pointer;">Thêm vào giỏ</button>
                        </form>
                    </c:if>

                    <a href="${pageContext.request.contextPath}/video/detail?id=${v.videoId}" style="display: inline-block; margin-top: 5px; background: #b19cd9; color: white; padding: 5px 10px; text-decoration: none; border-radius: 4px; font-size: 13px;">Chi tiết</a>
                </div>
            </c:forEach>
        </div>

        <%-- Phân trang riêng cho từng Category (3 video/trang) --%>
        <c:if test="${block.totalPages > 1}">
            <div style="text-align: center; margin-top: 10px; font-weight: bold; color: #8a4f9d;">
                <c:if test="${block.currentPage > 1}">
                    <a href="${pageContext.request.contextPath}/home?page_${block.categoryId}=${block.currentPage - 1}" style="margin: 0 5px; text-decoration: none; color: #8a4f9d;">&lt;&lt;</a>
                </c:if>
                <c:forEach begin="1" end="${block.totalPages}" var="p">
                    <a href="${pageContext.request.contextPath}/home?page_${block.categoryId}=${p}"
                       style="margin: 0 5px; text-decoration: none; ${p == block.currentPage ? 'color:#fff;background:#b19cd9;padding:2px 8px;border-radius:4px;' : 'color:#8a4f9d;'}">${p}</a>
                </c:forEach>
                <c:if test="${block.currentPage < block.totalPages}">
                    <a href="${pageContext.request.contextPath}/home?page_${block.categoryId}=${block.currentPage + 1}" style="margin: 0 5px; text-decoration: none; color: #8a4f9d;">&gt;&gt;</a>
                </c:if>
            </div>
        </c:if>
    </div>
</c:forEach>

</body>
</html>