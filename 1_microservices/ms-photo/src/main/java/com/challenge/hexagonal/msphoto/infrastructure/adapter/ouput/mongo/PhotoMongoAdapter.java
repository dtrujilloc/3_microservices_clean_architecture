package com.challenge.hexagonal.msphoto.infrastructure.adapter.ouput.mongo;

import com.challenge.hexagonal.msphoto.domain.model.Photo;
import com.challenge.hexagonal.msphoto.domain.port.output.IPhotoPersistOutputPort;
import com.challenge.hexagonal.msphoto.infrastructure.adapter.ouput.mongo.entity.PhotoMongoEntity;
import com.challenge.hexagonal.msphoto.infrastructure.adapter.ouput.mongo.mapper.IPhotoMongoMapper;
import com.challenge.hexagonal.msphoto.infrastructure.adapter.ouput.mongo.repository.IPhotoMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase que representa la implementacion en detalle del adaptador de salida para mongo a partir del puerto de salida
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class PhotoMongoAdapter implements IPhotoPersistOutputPort {

    private final IPhotoMongoRepository photoMongoRepository;
    private final IPhotoMongoMapper photoMongoMapper;

    @Override
    public Photo savePhoto(Photo photo) {
        log.info(">>> Start adapter savePhoto -> photoId:{}", photo.getId());
        PhotoMongoEntity photoMongoEntity = photoMongoMapper.photoModelToPhotoMongoEntity(photo);
        PhotoMongoEntity photoSaved = photoMongoRepository.save(photoMongoEntity);
        Photo photoResponse = photoMongoMapper.photoMongoEntityToPhotoModel(photoSaved);
        log.info("<<< End adapter savePhoto -> photoResponseId:{}", photoResponse.getId());
        return photoResponse;
    }

    @Override
    public List<Photo> getAllPhoto() {
        log.info(">>> Start adapter getAllPhoto");
        List<PhotoMongoEntity> photoMongoEntityList = photoMongoRepository.findAll();
        List<Photo> photoList = photoMongoMapper.photoMongoEntityListToPhotoModelList(photoMongoEntityList);
        log.info("<<< End adapter getAllPhoto -> photoListSize:{}", photoList.size());
        return photoList;
    }

    @Override
    public Photo getPhotoById(String id) {
        log.info(">>> Start adapter getPhotoById -> id:{}", id);
        PhotoMongoEntity photoMongoEntity = photoMongoRepository.findById(id).orElse(null);
        Photo photo = photoMongoMapper.photoMongoEntityToPhotoModel(photoMongoEntity);
        log.info("<<< End adapter getPhotoById -> photoId:{}", photo != null ? photo.getId() : null);
        return photo;
    }

    @Override
    public Photo getPhotoByClientId(String clientId) {
        log.info(">>> Start adapter getPhotoByClientId -> clientId:{}", clientId);
        PhotoMongoEntity photoMongoEntity = photoMongoRepository.findByClientId(clientId);
        Photo photo = photoMongoMapper.photoMongoEntityToPhotoModel(photoMongoEntity);
        log.info("<<< End adapter getPhotoByClientId -> photoId:{} - clientId:{}", photo != null ? photo.getId() : null, photo != null ? photo.getClientId() : null);
        return photo;
    }

    @Override
    public void deletePhotoById(String id) {
        log.info(">>> Start adapter deletePhotoById -> id:{}", id);
        photoMongoRepository.deleteById(id);
        log.info(">>> End adapter deletePhotoById");
    }
}
