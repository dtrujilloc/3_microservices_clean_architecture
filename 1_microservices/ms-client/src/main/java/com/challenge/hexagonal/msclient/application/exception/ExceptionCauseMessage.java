package com.challenge.hexagonal.msclient.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que representa las diferentes causas de errores que pueden existir con su respectiva descripcion.
 * @author dtrujilloc
 * @version 1.0.0 25/11/2022
 */
@AllArgsConstructor
@Getter
public enum ExceptionCauseMessage {
    CLIENT_NOT_EXIST("the client not exist in bd"),
    CLIENT_NO_DATA_FOUND("the client data not found in bd"),
    CLIENT_ALREADY_EXIST("the client exist in bd");

    private final String description;
}
