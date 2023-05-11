package org.koreait.android;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONResult<Object>> errorHandler(Exception e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (e instanceof CommonException) {
            CommonException ee = (CommonException)e;
            status = ee.getStatus();
        }

        JSONResult<Object> jsonResult = JSONResult.builder()
                .message(e.getMessage())
                .status(HttpStatus.OK)
                .build();

        return ResponseEntity.ok(jsonResult);
    }
}
