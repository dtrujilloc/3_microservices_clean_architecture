package com.challenge.hexagonal.msphoto.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que representa las diferentes causas de errores que pueden existir con su respectiva descripcion.
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@AllArgsConstructor
@Getter
public enum ExceptionCauseMessage {
    PHOTO_NOT_EXIST("the photo not exist in bd"),
    PHOTO_NO_DATA_FOUND("the photo data not found in bd"),
    PHOTO_ALREADY_EXIST("the photo exist in bd"),
    PHOTO_API_EXCEPTION_UNKNOWN("the exception is unknown");

    private final String description;
}