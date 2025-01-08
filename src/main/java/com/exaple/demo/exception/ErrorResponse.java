package com.exaple.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp; // 에러 발생 시각
    private int status;              // HTTP 상태 코드
    private String error;            // 상태 코드 설명
    private String message;          // 에러 메시지
    private String path;             // 요청 경로
}