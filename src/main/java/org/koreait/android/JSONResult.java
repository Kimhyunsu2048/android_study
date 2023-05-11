package org.koreait.android;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data @Builder
public class JSONResult<T> {
    private boolean success;
    private HttpStatus status;
    private T data;
    private String message;
}
