package vn.iotstar.dto;

import java.util.List;
import vn.iotstar.entity.Video_24162103;

/**
 * DTO gom nhóm danh sách video theo Category, kèm thông tin phân trang
 * riêng cho từng Category (dùng cho trang chủ User - Cau 4, Cau 5).
 */
public class CategoryBlock_24162103 {
    private int categoryId;
    private String categoryName;
    private List<Video_24162103> videos;
    private int currentPage;
    private int totalPages;
    private long totalVideo; // tong so video cua category nay (Cau 5)

    public CategoryBlock_24162103() {}

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public List<Video_24162103> getVideos() { return videos; }
    public void setVideos(List<Video_24162103> videos) { this.videos = videos; }

    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public long getTotalVideo() { return totalVideo; }
    public void setTotalVideo(long totalVideo) { this.totalVideo = totalVideo; }
}
