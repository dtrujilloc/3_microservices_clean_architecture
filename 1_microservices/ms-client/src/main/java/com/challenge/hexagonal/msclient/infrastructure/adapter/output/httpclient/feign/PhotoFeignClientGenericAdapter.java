package com.challenge.hexagonal.msclient.infrastructure.adapter.output.httpclient.feign;

import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.httpclient.feign.PhotoFeignClientResponseDto;
import com.challenge.hexagonal.msclient.domain.port.output.httpclient.IPhotoHttpClientGenericOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase que representa el adaptor(implementacion del puerto de salida) de salida para el cliente http de Feign relacionado a la iformacion de la foto
 * @author dtrujilloc
 * @version 1.0.0 01/12/2023
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PhotoFeignClientGenericAdapter implements IPhotoHttpClientGenericOutputPort<PhotoFeignClientResponseDto, PhotoFeignClientCreateRequestDto, PhotoFeignClientUpdateRequestDto, String> {

    private final IPhotoFeignClient photoFeignClient;

    @Override
    public PhotoFeignClientResponseDto savePhoto(PhotoFeignClientCreateRequestDto photo) {
        return photoFeignClient.createPhoto(photo);
    }

    @Override
    public List<PhotoFeignClientResponseDto> getAllPhoto() {
        return photoFeignClient.readAllPhoto();
    }

    @Override
    public PhotoFeignClientResponseDto getPhotoById(String s) {
        return photoFeignClient.readPhotoById(s);
    }

    @Override
    public PhotoFeignClientResponseDto getPhotoByClientId(String clientId) {
        return photoFeignClient.readPhotoByClientId(clientId);
    }

    @Override
    public PhotoFeignClientResponseDto updatePhoto(PhotoFeignClientUpdateRequestDto photo) {
        return photoFeignClient.updatePhoto(photo);
    }

    @Override
    public void deletePhotoById(String s) {
        photoFeignClient.deletePhotoById(s);
    }

    @Override
    public void deletePhotoByClientId(String clientId) {
        photoFeignClient.deletePhotoByClientId(clientId);
    }
}
