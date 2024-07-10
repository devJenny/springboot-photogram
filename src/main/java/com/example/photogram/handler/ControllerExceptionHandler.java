package com.example.photogram.handler;

import com.example.photogram.handler.ex.CustomApiException;
import com.example.photogram.handler.ex.CustomException;
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

        if (e.getErrorMap() == null) {
            return Script.back(e.getMessage());

        } else {
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e) {
        return Script.back(e.getMessage());
    }


    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMResDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CMResDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}
