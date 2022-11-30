package com.challenge.hexagonal.msphoto.application.usecase;

import com.challenge.hexagonal.msphoto.application.exception.PhotoAlreadyExistException;
import com.challenge.hexagonal.msphoto.application.exception.PhotoNoDataFoundException;
import com.challenge.hexagonal.msphoto.application.exception.PhotoNotExistException;
import com.challenge.hexagonal.msphoto.domain.model.Photo;
import com.challenge.hexagonal.msphoto.domain.port.input.IPhotoServiceInputPort;
import com.challenge.hexagonal.msphoto.domain.port.salida.IPhotoPersistOutputPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Clase que representa el caso de uso o servicio de una foto, es decir donde existe la logica y reglas de negocio para
 * la foto. Implementa el puerto de entrada y utiliza el puerto de salida.
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Slf4j
public class PhotoUseCase implements IPhotoServiceInputPort {

    private final IPhotoPersistOutputPort photoPersistOutputPort;

    public PhotoUseCase (IPhotoPersistOutputPort photoPersistOutputPort) {
        this.photoPersistOutputPort = photoPersistOutputPort;
    }


    @Override
    public Photo createPhoto(Photo photo) {
        log.info(">>> Start use case createPhoto");
        Photo photoValidation = photoPersistOutputPort.getPhotoByClientId(photo.getClientId());
        if(photoValidation != null) {
            throw new PhotoAlreadyExistException(String.format("The photo can't be create because the client have a photo -> photoId=%s - clientId:%s", photoValidation.getId(), photoValidation.getClientId().toString()));
        }
        Photo photoSaved = photoPersistOutputPort.savePhoto(photo);
        log.info("<<< End use case createPhoto -> photoSavedId:{}", photoSaved.getId());
        return photoSaved;
    }

    @Override
    public List<Photo> readAllPhoto() {
        log.info(">>> Start use case readAllPhoto");
        List<Photo> photoList = photoPersistOutputPort.getAllPhoto();
        if (photoList.isEmpty()) {
            throw new PhotoNoDataFoundException("the photo list is empty");
        }
        log.info("<<< End use case readAllPhoto -> photoListSize:{}", photoList.size());
        return photoList;
    }

    @Override
    public Photo readPhotoById(String id) {
        log.info(">>> Start use case readPhotoById -> id:{}", id);
        Photo photo = photoPersistOutputPort.getPhotoById(id);
        if (photo == null) {
            throw new PhotoNotExistException(String.format("The photo not exist with id=%s", id));
        }
        log.info("<<< End use case readPhotoById -> photoId:{}", photo.getId());
        return photo;
    }

    @Override
    public Photo readPhotoByClientId(Long clientId) {
        log.info(">>> Start use case readPhotoByClientId -> clientId:{}", clientId);
        Photo photo = photoPersistOutputPort.getPhotoByClientId(clientId);
        if (photo == null) {
            throw new PhotoNotExistException(String.format("The photo not exist with client's id=%s", clientId.toString()));
        }
        log.info("<<< End use case readPhotoByClientId -> photoId:{} - clientId:{}", photo.getId(), photo.getClientId());
        return photo;
    }

    @Override
    public Photo updatePhoto(Photo photo) {
        log.info(">>> Start use case updatePhoto -> photoId:{}", photo.getId());
        Photo photoOld = readPhotoById(photo.getId());

        photoOld.setPhoto(photo.getPhoto());
        photoOld.setClientId(photo.getClientId());

        Photo photoUpdated = photoPersistOutputPort.savePhoto(photoOld);
        log.info("<<< End use case updatePhoto -> photoUpdatedId:{}", photoUpdated.getId());
        return photoUpdated;
    }

    @Override
    public void deletePhotoById(String id) {
        log.info(">>> Start use case deletePhotoById -> id:{}", id);
        Photo photo = readPhotoById(id);
        photoPersistOutputPort.deletePhotoById(photo.getId());
        log.info("<<< End use case deletePhotoById");
    }

    @Override
    public void deletePhotoByClientId(Long clientId) {
        log.info(">>> Start use case deletePhotoByClientId -> clientId:{}", clientId);
        Photo photo = readPhotoByClientId(clientId);
        photoPersistOutputPort.deletePhotoById(photo.getId());
        log.info("<<< End use case deletePhotoById -> id:{} - clientId:{}", photo.getId(), photo.getClientId());
    }
}
