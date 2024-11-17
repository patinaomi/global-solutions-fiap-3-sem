package br.com.fiap.global.exception;

import br.com.fiap.global.gateways.dtos.response.StandardErrorResponse;
import br.com.fiap.global.service.exception.DataIntegrityException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardErrorResponse> objectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        StandardErrorResponse err = new StandardErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardErrorResponse> dataIntegrityException(DataIntegrityException e, HttpServletRequest request) {
        StandardErrorResponse err = new StandardErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
