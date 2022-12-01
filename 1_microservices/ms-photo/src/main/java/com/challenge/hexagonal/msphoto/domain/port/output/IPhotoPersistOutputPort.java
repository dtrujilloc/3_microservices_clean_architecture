package com.challenge.hexagonal.msphoto.domain.port.output;

import com.challenge.hexagonal.msphoto.domain.model.Photo;

import java.util.List;

/**
 * Interface que representa el puerto de salida para utilizacion en la capa de aplicacion (caso de uso) y la
 * posterior implementacion por el adaptador de persistencia
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
public interface IPhotoPersistOutputPort {

    /**
     * Permite guardar una photo
     * @param photo Objeto de tipo Photo que contiene la informacion a guardar
     * @return Un objeto de tipo Photo con la informacion guardada
     */
    Photo savePhoto(Photo photo);

    /**
     * Permite leer todos las fotos
     * @return una lista de fotos
     */
    List<Photo> getAllPhoto();

    /**
     * Permite leer una foto por su id con el que se persistio
     * @param id String que representa el identificador de persistencia
     * @return Un objeto de tipo Photo
     */
    Photo getPhotoById(String id);

    /**
     * Permite leer una foto por el id del cliente con el que se persistio
     * @param clientId String que representa el identificador del cliente con el que se persistio
     * @return Un objeto de tipo Photo
     */
    Photo getPhotoByClientId(String clientId);

    /**
     * Permite eliminar una foto  por su id con el que se persistio
     * @param id String que representa el identificador de persistencia
     */
    void deletePhotoById(String id);
}
