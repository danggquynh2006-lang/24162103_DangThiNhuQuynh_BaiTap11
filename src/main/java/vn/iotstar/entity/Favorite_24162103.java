package vn.iotstar.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Favorites")
public class Favorite_24162103 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteId")
    private int favoriteId;

    @Column(name = "LikedDate")
    @Temporal(TemporalType.DATE)
    private Date likedDate;

    @Column(name = "VideoId", length = 50)
    private String videoId;

    @Column(name = "Username", length = 50)
    private String username;

    public Favorite_24162103() {}
    // Getters and Setters
}