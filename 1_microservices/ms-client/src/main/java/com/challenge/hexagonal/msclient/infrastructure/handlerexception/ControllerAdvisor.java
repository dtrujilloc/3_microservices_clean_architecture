package com.challenge.hexagonal.msclient.infrastructure.handlerexception;

import com.challenge.hexagonal.msclient.application.exception.ClientAlreadyExistException;
import com.challenge.hexagonal.msclient.application.exception.ClientNoDataFoundException;
import com.challenge.hexagonal.msclient.application.exception.ClientNotExistException;
import com.challenge.hexagonal.msclient.application.exception.ExceptionCauseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase que permite dar manejo a las diferentes excepciones que existen en la BD
 * @author dtrujilloc
 * @version 1.0.0 25/11/2022
 */
@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ClientNoDataFoundException.class)
    public ResponseEntity<ExceptionApiResponse> handleClientNotDataFoundException(ClientNoDataFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus)
                .body(ExceptionApiResponse.builder()
                        .message(ex.getMessage())
                        .cause(ExceptionCauseMessage.CLIENT_NO_DATA_FOUND.getDescription())
                        .httpStatus(httpStatus)
                        .httpCode(httpStatus.value())
                        .build());
    }

    @ExceptionHandler(ClientNotExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleClientNotExistException(ClientNotExistException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus)
                .body(ExceptionApiResponse.builder()
                        .message(ex.getMessage())
                        .cause(ExceptionCauseMessage.CLIENT_NOT_EXIST.getDescription())
                        .httpStatus(httpStatus)
                        .httpCode(httpStatus.value())
                        .build());
    }

    @ExceptionHandler(ClientAlreadyExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleClientAlreadyException(ClientAlreadyExistException ex) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        return ResponseEntity.status(httpStatus)
                .body(ExceptionApiResponse.builder()
                        .message(ex.getMessage())
                        .cause(ExceptionCauseMessage.CLIENT_ALREADY_EXIST.getDescription())
                        .httpStatus(httpStatus)
                        .httpCode(httpStatus.value())
                        .build());
    }
}
