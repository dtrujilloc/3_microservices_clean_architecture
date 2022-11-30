package com.challenge.hexagonal.msphoto.infrastructure.handlerexception;

import com.challenge.hexagonal.msphoto.application.exception.ExceptionCauseMessage;
import com.challenge.hexagonal.msphoto.application.exception.PhotoAlreadyExistException;
import com.challenge.hexagonal.msphoto.application.exception.PhotoNoDataFoundException;
import com.challenge.hexagonal.msphoto.application.exception.PhotoNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase que permite dar manejo a las diferentes excepciones que existen en la API
 * @author dtrujilloc
 * @version 1.0.0 25/11/2022
 */
@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(PhotoNoDataFoundException.class)
    public ResponseEntity<ExceptionApiResponse> handleClientNotDataFoundException(PhotoNoDataFoundException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus)
                .body(ExceptionApiResponse.builder()
                        .message(ex.getMessage())
                        .cause(ExceptionCauseMessage.PHOTO_NO_DATA_FOUND.getDescription())
                        .httpStatus(httpStatus)
                        .httpCode(httpStatus.value())
                        .build());
    }

    @ExceptionHandler(PhotoNotExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleClientNotExistException(PhotoNotExistException ex) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(httpStatus)
                .body(ExceptionApiResponse.builder()
                        .message(ex.getMessage())
                        .cause(ExceptionCauseMessage.PHOTO_NOT_EXIST.getDescription())
                        .httpStatus(httpStatus)
                        .httpCode(httpStatus.value())
                        .build());
    }

    @ExceptionHandler(PhotoAlreadyExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleClientAlreadyException(PhotoAlreadyExistException ex) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        return ResponseEntity.status(httpStatus)
                .body(ExceptionApiResponse.builder()
                        .message(ex.getMessage())
                        .cause(ExceptionCauseMessage.PHOTO_ALREADY_EXIST.getDescription())
                        .httpStatus(httpStatus)
                        .httpCode(httpStatus.value())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionApiResponse> handleException(Exception ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus)
                .body(ExceptionApiResponse.builder()
                        .message(ex.getMessage())
                        .cause(ExceptionCauseMessage.PHOTO_API_EXCEPTION_UNKNOWN.getDescription())
                        .httpStatus(httpStatus)
                        .httpCode(httpStatus.value())
                        .build());
    }
}
