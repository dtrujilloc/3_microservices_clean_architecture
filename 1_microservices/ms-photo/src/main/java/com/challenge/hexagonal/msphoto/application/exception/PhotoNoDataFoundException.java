package com.challenge.hexagonal.msphoto.application.exception;

/**
 * Clase que representa la excepcion que sera lanzada cuando no encuentre la informacion de una photo de un cliente
 * @author dtrujilloc
 * @version 1.0.0 29/11/2022
 */
public class PhotoNoDataFoundException extends RuntimeException {

    public PhotoNoDataFoundException(){
        super();
    }

    public PhotoNoDataFoundException(String message) {
        super(message);
    }
}
