package com.challenge.hexagonal.msphoto.application.mapper;

import com.challenge.hexagonal.msphoto.application.dto.request.PhotoCreateRequestDto;
import com.challenge.hexagonal.msphoto.application.dto.request.PhotoUpdateRequestDto;
import com.challenge.hexagonal.msphoto.application.dto.response.PhotoResponseDto;
import com.challenge.hexagonal.msphoto.domain.model.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Interface que especifica los metodos de mapeo o conversion de la informacion de una foto en la capa de aplicacion
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Mapper(componentModel = MappingConstants.ComponentModel.DEFAULT,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPhotoMapper {

    /**
     * Permite mapear la info de un Photo a un PhotoResponseDto
     * @param photoModel Objeto de tipo photoModel con la informacion a mapear
     * @return un objeto de tipo PhotoResponseDto con la informacion mapeada
     */
    @Mappings({
            @Mapping(source = "photo", target = "photoBase64")
    })
    PhotoResponseDto photoModelToPhotoResponseDto(Photo photoModel);

    /**
     * Permite mapear la info de una lista de Photo a una lista de PhotoResponseDto
     * @param photoModelList List de tipo photoModel con la informacion a mapear
     * @return List de tipo PhotoResponseDto con la informacion mapeada
     */
    List<PhotoResponseDto> photoModelListToPhotoResponseDtoList(List<Photo> photoModelList);

    /**
     * Permite mapear la info de un PhotoResponseDto a un Photo
     * @param photoCreateRequestDto Objeto de tipo PhotoCreateRequestDto con la informacion a mapear
     * @return un objeto de tipo Photo con la informacion mapeada
     */
    @Mappings({
            @Mapping(source = "photoBase64", target = "photo")
    })
    Photo photoCreateRequestDtoToPhotoModel(PhotoCreateRequestDto photoCreateRequestDto);

    /**
     * Permite mapear la info de un PhotoResponseDto a un Photo
     * @param photoUpdateRequestDto Objeto de tipo PhotoUpdateRequestDto con la informacion a mapear
     * @return un objeto de tipo Photo con la informacion mapeada
     */
    @Mappings({
            @Mapping(source = "photoBase64", target = "photo")
    })
    Photo photoUpdateRequestDtoToPhotoModel(PhotoUpdateRequestDto photoUpdateRequestDto);
}
