package com.challenge.hexagonal.msclient.infrastructure.adapter.output.httpclient.feign;

import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.httpclient.feign.PhotoFeignClientResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@Component
@FeignClient(name = "ms-photo", url = "${url-ms-photo-feign}")
public interface IPhotoFeignClient {

    @PostMapping("/photo")
    PhotoFeignClientResponseDto createPhoto(@RequestBody PhotoFeignClientCreateRequestDto photo);

    @GetMapping("/photo")
    public List<PhotoFeignClientResponseDto> readAllPhoto();

    @GetMapping("/photo/{id}")
    PhotoFeignClientResponseDto readPhotoById(@PathVariable(value = "id") String id);

    /**
     * Permite leer una foto por el id del cliente con el que se persistio
     * @param clientId Long que representa el identificador del cliente con el que se persistio
     * @return Un objeto que contien la informacion de la foto
     */
    @GetMapping("/photo/client/{id}")
    PhotoFeignClientResponseDto readPhotoByClientId(@PathVariable(value = "id")String clientId);

    /**
     * Permite actualizar una photo
     * @param photo Objeto de que contiene la informacion de la foto que se desea actualizar
     * @return Un objeto de que contiene la informacion de la foto actualizada
     */
    @PutMapping("/photo")
    PhotoFeignClientResponseDto updatePhoto(@RequestBody PhotoFeignClientUpdateRequestDto photo);

    /**
     * Permite eliminar una foto  por su id con el que se persistio
     * @param id dato que representa el identificador de persistencia
     */
    @PutMapping("/photo/{id}")
    void deletePhotoById(String id);

    @DeleteMapping("/photo/client/{id}")
    void deletePhotoByClientId(@PathVariable(value = "id") String clientId);
}
