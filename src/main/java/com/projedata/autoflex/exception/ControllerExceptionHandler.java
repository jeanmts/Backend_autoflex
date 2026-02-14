package com.projedata.autoflex.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            erros.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        ErrorResponse erroResposta = new ErrorResponse(status.value(), "Existem campos inválidos, confira o preenchimento!", LocalDateTime.now(), erros);
        return this.handleExceptionInternal(ex,erroResposta, headers, status, request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.unprocessableContent().body(ex.getMessage());
    }

    @ExceptionHandler(RawMaterialNotFoundException.class)
    protected ResponseEntity<Object> handleRawMaterialNotFoundException(RawMaterialNotFoundException ex) {
        return ResponseEntity.unprocessableContent().body(ex.getMessage());
    }

    @ExceptionHandler(RawMaterialInsufficientException.class)
    protected ResponseEntity<Object> handleRawMaterialInsufficientException(RawMaterialInsufficientException ex) {
        return ResponseEntity.unprocessableContent().body(ex.getMessage());
    }
    @ExceptionHandler(RawMaterialDuplicateException.class)
    protected ResponseEntity<Object> handleRawMaterialDuplicateException(RawMaterialDuplicateException ex) {
        return ResponseEntity.unprocessableContent().body(ex.getMessage());
    }
}
