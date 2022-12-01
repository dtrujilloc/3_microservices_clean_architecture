package com.challenge.hexagonal.msphoto.application.handler;

import com.challenge.hexagonal.msphoto.application.dto.request.PhotoCreateRequestDto;
import com.challenge.hexagonal.msphoto.application.dto.request.PhotoUpdateRequestDto;
import com.challenge.hexagonal.msphoto.application.dto.response.PhotoResponseDto;
import com.challenge.hexagonal.msphoto.domain.model.Photo;

import java.util.List;

/**
 * Inteface que representa la plantilla que permite la orquestacion de la transformacion de Objetos del modelo a DTO's y viceversa. Implementacion
 * del patron Fachada
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
public interface IPhotoCrudHandler {

    /**
     * Permite realizar el proceso de creacion/guardado de una foto
     * @param photoRequest Objeto de tipo PhotoCreateRequestDto que contiene la informacion a guardar
     * @return Un objeto de tipo PhotoResponseDto con la informacion guardada
     */
    PhotoResponseDto createPhoto(PhotoCreateRequestDto photoRequest);

    /**
     * Permite realizar el proceso de buscar o leer todos las fotos
     * @return una lista de PhotoResponseDto
     */
    List<PhotoResponseDto> readAllPhoto();

    /**
     * Permite realizar el proceso de buscar o leer una foto por su id con el que se persistio
     * @param id String que representa el identificador de persistencia
     * @return Un objeto de tipo PhotoResponseDto
     */
    PhotoResponseDto readPhotoById(String id);

    /**
     * Permite realizar el proceso de buscar o leer una foto por el id del cliente con el que se persistio
     * @param clientId Long que representa el identificador del cliente con el que se persistio
     * @return Un objeto de tipo PhotoResponseDto
     */
    PhotoResponseDto readPhotoByClientId(String clientId);

    /**
     * Permite realizar el proceso de actualizar la informacion de una foto
     * @param photoRequest Objeto de tipo PhotoUpdateRequestDto con la informacion a actualizar
     * @return un objeto de tipo PhotoResponseDto con la informacion actualizada
     */
    PhotoResponseDto updatePhoto(PhotoUpdateRequestDto photoRequest);

    /**
     * Permite realizar el proceso de eliminar una foto  por su id con el que se persistio
     * @param id String que representa el identificador de persistencia
     */
    void deletePhotoById(String id);

    /**
     * Permite realizar el proceso de eliminar una foto  por el id del cliente con el que se persistio
     * @param clientId Long que representa el identificador de del cliente con el que se persistio
     */
    void deletePhotoByClientId(String clientId);
}
