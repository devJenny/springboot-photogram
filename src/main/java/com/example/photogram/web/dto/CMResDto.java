package com.example.photogram.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMResDto<T> {

    private int code; // 1(성공), -1(실패)
    private String message;
    private T data;
}
