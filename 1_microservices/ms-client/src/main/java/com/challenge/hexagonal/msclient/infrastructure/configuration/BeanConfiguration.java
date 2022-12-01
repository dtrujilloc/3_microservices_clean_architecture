package com.challenge.hexagonal.msclient.infrastructure.configuration;

import com.challenge.hexagonal.msclient.application.handler.ClientCrudHandler;
import com.challenge.hexagonal.msclient.application.handler.IClientCrudHandler;
import com.challenge.hexagonal.msclient.application.mapper.IClientMapper;
import com.challenge.hexagonal.msclient.application.usecase.ClientUseCase;
import com.challenge.hexagonal.msclient.domain.port.input.IClientServiceInputPort;
import com.challenge.hexagonal.msclient.domain.port.output.httpclient.IPhotoHttpClientOutputPort;
import com.challenge.hexagonal.msclient.domain.port.output.persist.IClientPersistOutputPort;
import com.challenge.hexagonal.msclient.infrastructure.adapter.output.httpclient.feign.IPhotoFeignClient;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase que permite la configuracon de Beans para la inyeccion de dependencias
 * @author dtrujilloc
 * @version 1.0.0 24/11/2022
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IClientPersistOutputPort clientPersistOutputPort;
    private final IPhotoHttpClientOutputPort photoHttpClientOutputPort;

    /**
     * Permite crear el Bean de IClientMapper para la inyeccion de la implementacion autogenerada por mapStruct
     * @return la implementacion del mapper autogenerada por mapStruct
     */
    @Bean
    public IClientMapper clientMapper() {
        return Mappers.getMapper(IClientMapper.class);
    }

    /**
     * Permite crear el Bean de IClientServiceInputPort para la inyeccion de la implementacion ClientUseCase
     * @return un objeto de tipo ClientUseCase
     */
    @Bean
    public IClientServiceInputPort clientServiceInputPort() {
        return new ClientUseCase(clientPersistOutputPort, photoHttpClientOutputPort);
    }

    /**
     * Permite crear el Bean de IClientCrudHandler para la inyeccion de la implementacion ClientCrudHandler
     * @return un objeto de tipo ClientCrudHandler
     */
    @Bean
    public IClientCrudHandler clientCrudHandler() {
        return new ClientCrudHandler(clientServiceInputPort(), clientMapper());
    }
}
