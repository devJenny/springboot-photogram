package com.example.photogram.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {

    private int id;
    private String username;
    private String profileImageUrl;
    private Long subscribeState;
    private Long equalUserState;

}
