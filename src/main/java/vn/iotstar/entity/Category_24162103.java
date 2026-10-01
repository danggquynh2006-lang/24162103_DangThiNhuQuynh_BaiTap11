package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class Category_24162103 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryId;

    @Column(name = "Categoryname", columnDefinition = "NVARCHAR(100)")
    private String categoryName;

    @Column(name = "Categorycode", columnDefinition = "NVARCHAR(100)")
    private String categoryCode;

    @Column(name = "Images", columnDefinition = "NVARCHAR(500)")
    private String images;

    @Column(name = "Status")
    private boolean status;

    @OneToMany(mappedBy = "category")
    private List<Video_24162103> videos;

    public Category_24162103() {}

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public List<Video_24162103> getVideos() { return videos; }
    public void setVideos(List<Video_24162103> videos) { this.videos = videos; }
}