package com.challenge.hexagonal.msclient.application.mapper;


import com.challenge.hexagonal.msclient.application.dto.request.ClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.ClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.ClientResponseDto;
import com.challenge.hexagonal.msclient.domain.model.Client;
import org.mapstruct.*;

import java.util.List;

/**
 * Interface que especifica los metodos de mapeo o conversion de la informacion de un cliente
 * @author dtrujilloc
 * @version 1.0.0 23/11/2022
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IClientMapper {

    /**
     * Permite mapear la info de un Client a un ClientResponseDto
     * @param clientModel Objeto de tipo Client con la informacion a mapear
     * @return un objeto de tipo ClientResponseDto con la informacion mapeada
     */
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "identificationType", target = "identificationType"),
            @Mapping(source = "identificationNumber", target = "identificationNumber"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "age", target = "age"),
            @Mapping(source = "birthDate", target = "birthDate"),
            @Mapping(source = "photo", target = "photo")
    })
    ClientResponseDto clientModelToClientClientResponseDto(Client clientModel);

    /**
     * Permite mapear la info de una lista de Client a una lista de ClientResponseDto
     * @param clientModelList List de tipo Client con la informacion a mapear
     * @return una List de tipo ClientResponseDto con la info mapeada
     */
    List<ClientResponseDto> clientModelListToClientResponseDtoList(List<Client> clientModelList);

    /**
     * Permite mapear la info de ClientCreateRequestDto a un Client
     * @param clientCreateRequestDto Objeto de tipo ClientCreateRequestDto con la informacion a mapear
     * @return un Objeto de tipo Client con la informacion mapeada
     */
    Client clientCreateRequestDtoToClientModel(ClientCreateRequestDto clientCreateRequestDto);

    /**
     * Permite mapear la info de ClientUpdateRequestDto a un Client
     * @param clientUpdateRequestDto Objeto de tipo ClientUpdateRequestDto con la informacion a mapear
     * @return un Objeto de tipo Client con la informacion mapeada
     */
    Client clientUpdateRequestDtoToClientModel(ClientUpdateRequestDto clientUpdateRequestDto);
}
