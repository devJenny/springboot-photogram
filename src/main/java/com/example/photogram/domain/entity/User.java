package com.example.photogram.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20, unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String name;
    private String website;
    private String bio; // 자기소개

    @NotNull
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 사진
    private String role; // 권한

    // 나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지마
    // User를 Select할 때 해당 User id로 등록된 image들을 다 가져와
    // Lazy = User를 Select할 때 해당 User Id로 등록된 Image들을 가져오지마 - 대신 getImages() 함수의 iamge들이 호출퇼 때 가져와
    // Eager = User를 Select 할 때 해당 User id로 등록된 Image들을 전부 Join 해서 가져와
    @OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Image> images;

    private LocalDateTime createdDate;

    @PrePersist
    public void createdDate() {
        this.createdDate = LocalDateTime.now();
    }
}