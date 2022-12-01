package com.challenge.hexagonal.msphoto.domain.port.input;


import com.challenge.hexagonal.msphoto.domain.model.Photo;

import java.util.List;

/**
 * Inteface que representa el puerto de entrada para su implementacion en la capa de aplicacion(caso de uso) y
 * la posterior utilizacion del adaptador
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
public interface IPhotoServiceInputPort {

    /**
     * Permite crear/guardar una photo
     * @param photo Objeto de tipo Photo que contiene la informacion a guardar
     * @return Un objeto de tipo Photo con la informacion guardada
     */
    Photo createPhoto(Photo photo);

    /**
     * Permite buscar o leer todos las fotos
     * @return una lista de fotos
     */
    List<Photo> readAllPhoto();

    /**
     * Permite buscar o leer una foto por su id con el que se persistio
     * @param id String que representa el identificador de persistencia
     * @return Un objeto de tipo Photo
     */
    Photo readPhotoById(String id);

    /**
     * Permite buscar o leer una foto por el id del cliente con el que se persistio
     * @param clientId Long que representa el identificador del cliente con el que se persistio
     * @return Un objeto de tipo Photo
     */
    Photo readPhotoByClientId(String clientId);

    /**
     * Permite actualizar la informacion de una foto
     * @param photo Objeto de tipo Photo con la informacion a actualizar
     * @return un objeto de tipo Photo con la informacion actualizada
     */
    Photo updatePhoto(Photo photo);

    /**
     * Permite eliminar una foto  por su id con el que se persistio
     * @param id String que representa el identificador de persistencia
     */
    void deletePhotoById(String id);

    /**
     * Permite eliminar una foto  por el id del cliente con el que se persistio
     * @param clientId Long que representa el identificador de del cliente con el que se persistio
     */
    void deletePhotoByClientId(String clientId);
}
