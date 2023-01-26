package com.alex.elearning.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizException extends RuntimeException{
    private Integer code;
    private String message;
}
