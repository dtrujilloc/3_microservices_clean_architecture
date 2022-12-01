package com.challenge.hexagonal.msphoto.application.handler;

import com.challenge.hexagonal.msphoto.application.dto.request.PhotoCreateRequestDto;
import com.challenge.hexagonal.msphoto.application.dto.request.PhotoUpdateRequestDto;
import com.challenge.hexagonal.msphoto.application.dto.response.PhotoResponseDto;
import com.challenge.hexagonal.msphoto.application.mapper.IPhotoMapper;
import com.challenge.hexagonal.msphoto.application.usecase.PhotoUseCase;
import com.challenge.hexagonal.msphoto.domain.model.Photo;
import com.challenge.hexagonal.msphoto.domain.port.input.IPhotoServiceInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Clase que implementa y especifica la logica para la orquestacion de la transformacion de Objetos relacinados al CRUD de foto
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Slf4j
@RequiredArgsConstructor
public class PhotoCrudHandler implements IPhotoCrudHandler {

    private final IPhotoServiceInputPort photoServiceInputPort;
    private final IPhotoMapper photoMapper;


    @Override
    public PhotoResponseDto createPhoto(PhotoCreateRequestDto photoRequest) {
        log.info(">>> Start handler createPhoto");
        Photo photo = photoMapper.photoCreateRequestDtoToPhotoModel(photoRequest);
        Photo photoSaved = photoServiceInputPort.createPhoto(photo);
        PhotoResponseDto photoResponse = photoMapper.photoModelToPhotoResponseDto(photoSaved);
        log.info("<<< End handler createPhoto -> photoResponseId:{}", photoResponse.getId());
        return photoResponse;
    }

    @Override
    public List<PhotoResponseDto> readAllPhoto() {
        log.info(">>> Start handler readAllPhoto");
        List<Photo> photoList = photoServiceInputPort.readAllPhoto();
        List<PhotoResponseDto> photoResponseDtoList = photoMapper.photoModelListToPhotoResponseDtoList(photoList);
        log.info("<<< End handler readAllPhoto -> photoResponseListSize:{}", photoResponseDtoList.size());
        return photoResponseDtoList;
    }

    @Override
    public PhotoResponseDto readPhotoById(String id) {
        log.info(">>> Start handler readPhotoById -> id:{}", id);
        Photo photo = photoServiceInputPort.readPhotoById(id);
        PhotoResponseDto photoResponse = photoMapper.photoModelToPhotoResponseDto(photo);
        log.info("<<< End handler readPhotoById -> id:{}", photoResponse.getId());
        return photoResponse;
    }

    @Override
    public PhotoResponseDto readPhotoByClientId(String clientId) {
        log.info(">>> Start handler readPhotoByClientId -> clientId:{}", clientId);
        Photo photo = photoServiceInputPort.readPhotoByClientId(clientId);
        PhotoResponseDto photoResponse = photoMapper.photoModelToPhotoResponseDto(photo);
        log.info("<<< End handler readPhotoByClientId -> id:{} - clientId:{}", photoResponse.getId(), photoResponse.getClientId());
        return photoResponse;
    }

    @Override
    public PhotoResponseDto updatePhoto(PhotoUpdateRequestDto photoRequest) {
        log.info(">>> Start handler updatePhoto -> photoRequestId:{}", photoRequest.getId());
        Photo photo = photoMapper.photoUpdateRequestDtoToPhotoModel(photoRequest);
        Photo photoUpdated = photoServiceInputPort.updatePhoto(photo);
        PhotoResponseDto photoResponse = photoMapper.photoModelToPhotoResponseDto(photoUpdated);
        log.info("<<< End handler updatePhoto -> photoResponseId:{}", photoResponse.getId());
        return photoResponse;
    }

    @Override
    public void deletePhotoById(String id) {
        log.info(">>> Start handler deletePhotoById -> id:{}", id);
        photoServiceInputPort.deletePhotoById(id);
        log.info("<<< End handler deletePhotoById");
    }

    @Override
    public void deletePhotoByClientId(String clientId) {
        log.info(">>> Start handler deletePhotoByClientId -> clientId:{}", clientId);
        photoServiceInputPort.deletePhotoByClientId(clientId);
        log.info("<<< End handler deletePhotoByClientId");
    }
}
