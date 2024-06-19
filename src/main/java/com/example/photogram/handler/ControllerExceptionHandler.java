package com.example.photogram.handler;

import com.example.photogram.handler.ex.CustomValidationApiException;
import com.example.photogram.handler.ex.CustomValidationException;
import com.example.photogram.util.Script;
import com.example.photogram.web.dto.CMResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validException(CustomValidationException e) {
        // CMResDto, Script 비교
        // 1. 클라이언트에게 응답할 때는 Script 좋음
        // 2. Ajax 통신 - CMResDto
        // 3. Android 통신 - CmResDto
        return Script.back(e.getErrorMap().toString());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMResDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }
}
