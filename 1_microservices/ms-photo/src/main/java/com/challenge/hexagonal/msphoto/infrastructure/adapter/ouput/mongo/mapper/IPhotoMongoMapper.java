package com.challenge.hexagonal.msphoto.infrastructure.adapter.ouput.mongo.mapper;

import com.challenge.hexagonal.msphoto.domain.model.Photo;
import com.challenge.hexagonal.msphoto.infrastructure.adapter.ouput.mongo.entity.PhotoMongoEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Base64;
import java.util.List;

/**
 * Interface que especifica los metodos de mapeo o conversion de la informacion de una photo en la capa de infraestructura
 * para el adaptador de mogo
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface IPhotoMongoMapper {


    @Mappings({
            @Mapping(source = "photo", target = "photo", qualifiedByName = "byteArrayToBase64")
    })
    Photo photoMongoEntityToPhotoModel(PhotoMongoEntity photoMongoEntity);

    List<Photo> photoMongoEntityListToPhotoModelList(List<PhotoMongoEntity> photoMongoEntityList);

    @Mappings({
            @Mapping(source = "photo", target = "photo", qualifiedByName = "base64ToByteArray")
    })
    PhotoMongoEntity photoModelToPhotoMongoEntity(Photo photoModel);

    List<PhotoMongoEntity> photoModelListToPhotoMongoEntityList(List<Photo> photoModelList);

    /**
     * Permite realizar la transformacion de un arreglo de bytes a un string de representacion de base64
     * @param byteArray Arreglo de bytes que represneta la foto
     * @return String que representa la foto en base64
     */
    @Named("byteArrayToBase64")
    static String byteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }

    /**
     * Permite realizar la tranformacion de un strign en base 64 a un arreglo de bytes
     * @param photoBase64 String que representa la foto en base64
     * @return un arreglo de bytes
     */
    @Named("base64ToByteArray")
    static byte[] base64ToByteArray(String photoBase64) {
        return Base64.getDecoder().decode(photoBase64);
    }
}
