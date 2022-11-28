package com.challenge.hexagonal.msclient.infrastructure.handlerexception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Clase que representa la plantilla de respuesta para una excepcion de la api
 * @author dtrujilloc
 * @version 1.0.0 25/11/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionApiResponse {
    private String message;
    private String cause;
    private HttpStatus httpStatus;
    private int httpCode;
}
