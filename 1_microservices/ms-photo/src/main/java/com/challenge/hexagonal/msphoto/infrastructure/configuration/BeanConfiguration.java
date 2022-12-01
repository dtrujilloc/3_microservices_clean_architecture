package com.challenge.hexagonal.msphoto.infrastructure.configuration;

import com.challenge.hexagonal.msphoto.application.handler.IPhotoCrudHandler;
import com.challenge.hexagonal.msphoto.application.handler.PhotoCrudHandler;
import com.challenge.hexagonal.msphoto.application.mapper.IPhotoMapper;
import com.challenge.hexagonal.msphoto.application.usecase.PhotoUseCase;
import com.challenge.hexagonal.msphoto.domain.port.input.IPhotoServiceInputPort;
import com.challenge.hexagonal.msphoto.domain.port.output.IPhotoPersistOutputPort;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase que permite la configuracon de Beans para la inyeccion de dependencias
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IPhotoPersistOutputPort photoPersistOutputPort;

    /**
     * Permite crear el Bean de IPhotoMapper para la inyeccion de la implementacion autogenerada por mapStruct
     * @return la implementacion del mapper autogenerada por mapStruct
     */
    @Bean
    public IPhotoMapper photoMapper() {
        return Mappers.getMapper(IPhotoMapper.class);
    }

    /**
     * Permite crear el Bean de IPhotoServiceInputPort para la inyeccion de la implementacion PhotoUseCase
     * @return un objeto de tipo PhotoUseCase
     */
    @Bean
    public IPhotoServiceInputPort photoServiceInputPort() {
        return new PhotoUseCase(photoPersistOutputPort);
    }

    /**
     * Permite crear el Bean de IPhotoCrudHandler para la inyeccion de la implementacion PhotoCrudHandler
     * @return un objeto de tipo PhotoCrudHandler
     */
    @Bean
    public IPhotoCrudHandler photoCrudHandler() {
        return new PhotoCrudHandler(photoServiceInputPort(), photoMapper());
    }
}
