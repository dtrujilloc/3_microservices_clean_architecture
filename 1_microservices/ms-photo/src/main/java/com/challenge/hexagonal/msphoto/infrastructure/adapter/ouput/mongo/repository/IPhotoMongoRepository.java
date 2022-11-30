package com.challenge.hexagonal.msphoto.infrastructure.adapter.ouput.mongo.repository;

import com.challenge.hexagonal.msphoto.infrastructure.adapter.ouput.mongo.entity.PhotoMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interface que extiende de Mongo repository y permitir realizar operaciones sobre la BD de mongo
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
public interface IPhotoMongoRepository extends MongoRepository<PhotoMongoEntity, String> {

    PhotoMongoEntity findByClientId(Long clientId);
}
