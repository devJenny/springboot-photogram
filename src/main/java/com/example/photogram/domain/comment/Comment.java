package com.example.photogram.domain.comment;

import com.example.photogram.domain.image.Image;
import com.example.photogram.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(length = 100)
    private String content;

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "imageId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Image image;

    private LocalDateTime createdDate;

    @PrePersist
    public void createdDate() {
        this.createdDate = LocalDateTime.now();
    }

}
