package com.challenge.hexagonal.msphoto.application.exception;

/**
 * Clase que representa la excepcion que sera lanzada cuando exista una photo para un cliente
 * @author dtrujilloc
 * @version 1.0.0 29/11/2022
 */
public class PhotoAlreadyExistException extends RuntimeException {

    public PhotoAlreadyExistException() {
        super();
    }

    public PhotoAlreadyExistException(String message) {
        super(message);
    }
}
