package com.challenge.hexagonal.msclient.application.exception;

/**
 * Clase que representa la excepcion que sera lanzada cuando no exista un cliente
 * @author dtrujilloc
 * @version 1.0.0 25/11/2022
 */
public class ClientNotExistException extends RuntimeException {

    public ClientNotExistException(){
        super();
    }

    public ClientNotExistException(String message) {
        super(message);
    }
}
