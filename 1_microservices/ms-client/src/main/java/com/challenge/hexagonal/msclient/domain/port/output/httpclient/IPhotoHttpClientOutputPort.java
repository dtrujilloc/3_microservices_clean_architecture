package com.challenge.hexagonal.msclient.domain.port.output.httpclient;

import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.httpclient.feign.PhotoFeignClientResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Interface que representa el puerto de salida para utilizacion en la capa de aplicacion (caso de uso) y la
 * posterior implementacion por el adaptador de httpClient
 * @author dtrujilloc
 * @version 1.0.0 22/11/2022
 */
public interface IPhotoHttpClientOutputPort {
    /**
     * Permite guardar una photo
     * @param photo Objeto de que contiene la informacion de la foto que se desea guardar
     * @return Un objeto de que contiene la informacion de la foto guardada
     */
    PhotoFeignClientResponseDto createPhoto(PhotoFeignClientCreateRequestDto photo);

    /**
     * Permite leer todos las fotos
     * @return una lista de fotos
     */
    List<PhotoFeignClientResponseDto> readAllPhoto();

    /**
     * Permite leer una foto por su id con el que se persistio
     * @param id dato que representa el identificador de persistencia
     * @return Un objeto que contien la informacion de la foto
     */
    PhotoFeignClientResponseDto readPhotoById(String id);

    /**
     * Permite leer una foto por el id del cliente con el que se persistio
     * @param clientId Long que representa el identificador del cliente con el que se persistio
     * @return Un objeto que contien la informacion de la foto
     */
    PhotoFeignClientResponseDto readPhotoByClientId(String clientId);

    /**
     * Permite actualizar una photo
     * @param photo Objeto de que contiene la informacion de la foto que se desea actualizar
     * @return Un objeto de que contiene la informacion de la foto actualizada
     */
    PhotoFeignClientResponseDto updatePhoto(PhotoFeignClientUpdateRequestDto photo);

    /**
     * Permite eliminar una foto  por su id con el que se persistio
     * @param id dato que representa el identificador de persistencia
     */
    void deletePhotoById(String id);

    void deletePhotoByClientId(@PathVariable(value = "id") String clientId);
}
