package com.vmurashkin.btc.controller;

import com.vmurashkin.btc.dto.ErrorDTO;
import com.vmurashkin.btc.exception.NotReceivedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = {NotReceivedException.class})
    protected ResponseEntity<ErrorDTO> handleNotReceivedException(NotReceivedException ex) {
        log.error("Entity not found", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorDTO> handleException(Exception ex) {
        log.error("Something wrong", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(new ErrorDTO("Something went wrong"));
    }

}
