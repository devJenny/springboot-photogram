package com.example.photogram.handler;

import com.example.photogram.dto.CMResDto;
import com.example.photogram.handler.ex.CustomValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public CMResDto<?> validException(CustomValidationException e) {
        return new CMResDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
    }
}
