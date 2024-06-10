package com.example.photogram.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMReqDto {

    private String message;
    private Map<String, String> errorMap;
}
