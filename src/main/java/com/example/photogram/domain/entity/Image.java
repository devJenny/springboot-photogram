package com.example.photogram.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name="userId")
    @ManyToOne(fetch = FetchType.EAGER) // 이미지를 select 하면 조인해서 User 정보를 같이 들고옴
    private User user;

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
