package com.cos.blog.handler;

import com.cos.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// 어디서든 해당 파일에 접근할 수 있게 해주는 어노테이션
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    // IllegalArgumentException 이 일어나면 해당 함수로 이동한다. (해당 클래스 value 로 사용 시)
    @ExceptionHandler(value=Exception.class)
    public ResponseDto<String> handleArgumentException(IllegalArgumentException e) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
