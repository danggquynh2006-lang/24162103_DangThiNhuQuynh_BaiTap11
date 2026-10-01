package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Cart")
public class Cart_24162103 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartId")
    private Integer cartId;

    @Column(name = "Username")
    private String username;

    @Column(name = "VideoId")
    private String videoId;

    @Column(name = "Quantity")
    private Integer quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreatedDate")
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId", insertable = false, updatable = false)
    private Video_24162103 video;

    public Cart_24162103() {}

    public Integer getCartId() { return cartId; }
    public void setCartId(Integer cartId) { this.cartId = cartId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getVideoId() { return videoId; }
    public void setVideoId(String videoId) { this.videoId = videoId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    public Video_24162103 getVideo() { return video; }
    public void setVideo(Video_24162103 video) { this.video = video; }

    public double getSubTotal() {
        double price = (video != null && video.getPrice() != null) ? video.getPrice() : 0;
        return price * (quantity != null ? quantity : 0);
    }
}