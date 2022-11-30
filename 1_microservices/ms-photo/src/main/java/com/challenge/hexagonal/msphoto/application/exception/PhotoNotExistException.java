package com.challenge.hexagonal.msphoto.application.exception;

/**
 * Clase que representa la excepcion que sera lanzada cuando no exista una photo de un cliente
 * @author dtrujilloc
 * @version 1.0.0 29/11/2022
 */
public class PhotoNotExistException extends RuntimeException {

    public PhotoNotExistException () {
        super();
    }

    public PhotoNotExistException (String message) {
        super(message);
    }
}
