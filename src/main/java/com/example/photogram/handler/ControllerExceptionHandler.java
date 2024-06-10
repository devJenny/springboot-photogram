package com.example.photogram.handler;

import com.example.photogram.dto.CMReqDto;
import com.example.photogram.handler.ex.CustomValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public CMReqDto validException(CustomValidationException e) {
        return new CMReqDto(e.getMessage(), e.getErrorMap());
    }
}
