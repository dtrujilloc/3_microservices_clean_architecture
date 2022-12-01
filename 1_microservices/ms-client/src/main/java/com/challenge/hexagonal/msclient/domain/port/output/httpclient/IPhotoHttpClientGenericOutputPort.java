package com.challenge.hexagonal.msclient.domain.port.output.httpclient;

import java.util.List;

/**
 * Interface que representa el puerto de salida para utilizacion en la capa de aplicacion (caso de uso) y la
 * posterior implementacion por el adaptador de persistencia
 * @author dtrujilloc
 * @version 1.0.0 30/11/2022
 */

/**
 * Interface generica que representa el puerto de salida para utilizacion en la capa de aplicacion (caso de uso) y la
 * posterior implementacion por el adaptador de cliente http para la informacion de la foto
 * @param <ResponseObj> Objeto representa la respuesta de los metodos
 * @param <CreateObj> Objeto que representa el request para la creacion o guardado
 * @param <UpdateObj> Objeto que representa el reques para la actualizacion
 * @param <ID> Tipo de dato que representa el identificador principal
 */
public interface IPhotoHttpClientGenericOutputPort<ResponseObj, CreateObj, UpdateObj, ID> {

    /**
     * Permite guardar una photo
     * @param photo Objeto de que contiene la informacion de la foto que se desea guardar
     * @return Un objeto de que contiene la informacion de la foto guardada
     */
    ResponseObj savePhoto(CreateObj photo);

    /**
     * Permite leer todos las fotos
     * @return una lista de fotos
     */
    List<ResponseObj> getAllPhoto();

    /**
     * Permite leer una foto por su id con el que se persistio
     * @param id dato que representa el identificador de persistencia
     * @return Un objeto que contien la informacion de la foto
     */
    ResponseObj getPhotoById(ID id);

    /**
     * Permite leer una foto por el id del cliente con el que se persistio
     * @param clientId Long que representa el identificador del cliente con el que se persistio
     * @return Un objeto que contien la informacion de la foto
     */
    ResponseObj getPhotoByClientId(String clientId);

    /**
     * Permite actualizar una photo
     * @param photo Objeto de que contiene la informacion de la foto que se desea actualizar
     * @return Un objeto de que contiene la informacion de la foto actualizada
     */
    ResponseObj updatePhoto(UpdateObj photo);

    /**
     * Permite eliminar una foto  por su id con el que se persistio
     * @param id dato que representa el identificador de persistencia
     */
    void deletePhotoById(ID id);

    /**
     * Permite eliminar una foto por el identificador del cliente con el que se persistio
     * @param clientId Long que representa el identificador de persistencia del cliente
     */
    void deletePhotoByClientId(String clientId);
}
