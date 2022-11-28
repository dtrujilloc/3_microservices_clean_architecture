package com.challenge.hexagonal.msclient.application.exception;

/**
 * Clase que representa la excepcion que sera lanzada cuando no encuentre la informacion de un cliente
 * @author dtrujilloc
 * @version 1.0.0 25/11/2022
 */
public class ClientNoDataFoundException extends RuntimeException {

    public ClientNoDataFoundException() {
        super();
    }

    public ClientNoDataFoundException(String message) {
        super(message);
    }
}
