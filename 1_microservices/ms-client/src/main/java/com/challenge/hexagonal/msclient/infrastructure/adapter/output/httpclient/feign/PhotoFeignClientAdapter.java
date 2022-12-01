package com.challenge.hexagonal.msclient.infrastructure.adapter.output.httpclient.feign;

import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.httpclient.feign.PhotoFeignClientResponseDto;
import com.challenge.hexagonal.msclient.domain.port.output.httpclient.IPhotoHttpClientOutputPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase que representa la implementacion en detalle del adaptador de salida para el cliente http de Feign para la informacion de una foto
 * a partir del puerto de salida
 * @author dtrujilloc
 * @version 1.0.0 01/12/2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PhotoFeignClientAdapter implements IPhotoHttpClientOutputPort
{

    private final IPhotoFeignClient photoFeignClient;

//    @HystrixCommand(fallbackMethod = "obtenerItemPorIdProductoYCantidadFallback")
    @CircuitBreaker(name = "ms-photo-cb", fallbackMethod = "createPhotoFallBack")
    @Override
    public PhotoFeignClientResponseDto createPhoto(PhotoFeignClientCreateRequestDto photo) {
        log.info(">>> Star httpClientFeignAdapter createPhoto -> clientId{}", photo.getClientId());
        return photoFeignClient.createPhoto(photo);
    }

    @Override
    public List<PhotoFeignClientResponseDto> readAllPhoto() {
        log.info(">>> Star httpClientFeignAdapter readAllPhoto");
        return photoFeignClient.readAllPhoto();
    }

    @Override
    public PhotoFeignClientResponseDto readPhotoById(String id) {
        log.info(">>> Star httpClientFeignAdapter readAllPhoto");
        return photoFeignClient.readPhotoById(id);
    }

    @Override
    public PhotoFeignClientResponseDto readPhotoByClientId(String clientId) {
        log.info(">>> Star httpClientFeignAdapter readPhotoByClientId -> clientId:{}", clientId);
        return photoFeignClient.readPhotoByClientId(clientId);
    }

    @Override
    public PhotoFeignClientResponseDto updatePhoto(PhotoFeignClientUpdateRequestDto photo) {
        log.info(">>> Star httpClientFeignAdapter updatePhoto -> id:{} - clientId:{}", photo.getId(), photo.getClientId());
        return photoFeignClient.updatePhoto(photo);
    }

    @Override
    public void deletePhotoById(String id) {
        log.info(">>> Star httpClientFeignAdapter deletePhotoById -> id:{}", id);
        photoFeignClient.deletePhotoById(id);
    }

    @CircuitBreaker(name = "ms-photo-cb", fallbackMethod = "deletePhotoByClientIdFallBack")
    @Override
    public void deletePhotoByClientId(String clientId) {
        log.info(">>> Star httpClientFeignAdapter deletePhotoByClientId -> clientId:{}", clientId);
        photoFeignClient.deletePhotoByClientId(clientId);
    }

    private PhotoFeignClientResponseDto createPhotoFallBack(PhotoFeignClientCreateRequestDto photo, RuntimeException ex) {
        log.info(">>> Star httpClientFeignAdapter createPhotoFallBack -> clientId{}", photo.getClientId());
        log.error("the call to create the photo for the clientId:{} didn't work -> error:{}",photo.getClientId(), ex.getMessage());
        log.warn("Creating a fall response because failed the call to create the photo");
        return PhotoFeignClientResponseDto.builder()
                .id(null)
                .clientId(photo.getClientId())
                .photoBase64("Without photo")
                .build();
    }

    private void deletePhotoByClientIdFallBack(String clientId, RuntimeException ex) {
        log.info(">>> Star httpClientFeignAdapter deletePhotoByClientIdFallBack -> clientId:{}", clientId);
        log.error("The call to delete the photo by clientId:{} didn't work -> error:{}", clientId, ex.getMessage());
        log.warn("Creating a fall response because failed the call to delete the photo");

    }
}
