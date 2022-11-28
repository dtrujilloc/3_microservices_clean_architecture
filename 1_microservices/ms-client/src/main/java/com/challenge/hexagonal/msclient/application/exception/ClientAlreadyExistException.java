package com.challenge.hexagonal.msclient.application.exception;

/**
 * Clase que representa la excepcion que sera lanzada cuando exista un cliente
 * @author dtrujilloc
 * @version 1.0.0 25/11/2022
 */
public class ClientAlreadyExistException extends RuntimeException {

    public ClientAlreadyExistException () {
        super();
    }

    public ClientAlreadyExistException (String message) {
        super(message);
    }
}
