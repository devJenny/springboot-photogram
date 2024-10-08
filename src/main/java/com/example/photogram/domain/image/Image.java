package com.example.photogram.domain.image;

import com.example.photogram.domain.comment.Comment;
import com.example.photogram.domain.likes.Likes;
import com.example.photogram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption;
    private String postImageUrl; // 사진을 전송받아 사진을 서버 특정 폴더에 저장 - DB 그 저장된 경로를 Insert

    @JsonIgnoreProperties({"images"}) // User 객체 안에 있는 images 필드가 무시됨
    @JoinColumn(name="userId")
    @ManyToOne(fetch = FetchType.EAGER) // 이미지를 select 하면 조인해서 User 정보를 같이 들고옴
    private User user; // 1,1

    // 이미지 좋아요
    @JsonIgnoreProperties({"images"})
    @OneToMany(mappedBy = "images")
    private List<Likes> likes;


    // 댓글
    @OrderBy("id DESC")
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Comment> comments;

    @Transient // db에 컬럼이 만들어지지 않음
    private boolean likeState;

    @Transient
    private int likeCount;

    private LocalDateTime createdDate;

    @PrePersist
    public void createdDate() {
        this.createdDate = LocalDateTime.now();
    }

/*    // 오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User 부분을 출력되지 않게 함
    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", postImageUrl='" + postImageUrl + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }*/

}
