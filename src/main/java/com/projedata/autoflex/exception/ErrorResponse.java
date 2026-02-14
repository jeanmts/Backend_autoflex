package com.projedata.autoflex.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class ErrorResponse {

    private Integer status;
    private String message;
    private LocalDateTime dateTime;
    private List<String> errors;
}
