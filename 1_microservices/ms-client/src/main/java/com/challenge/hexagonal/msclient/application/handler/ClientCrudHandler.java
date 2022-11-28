package com.challenge.hexagonal.msclient.application.handler;

import com.challenge.hexagonal.msclient.application.dto.request.ClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.ClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.ClientResponseDto;
import com.challenge.hexagonal.msclient.application.mapper.IClientMapper;
import com.challenge.hexagonal.msclient.domain.model.Client;
import com.challenge.hexagonal.msclient.domain.port.input.IClientServiceInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Clase que implementa y especifica la logica para la orquestacion de la transformacion de Objetos relacinados al CRUD de clientes
 * @author dtrujilloc
 * @version 1.0.0 23/11/2022
 */
@Slf4j
@RequiredArgsConstructor
public class ClientCrudHandler implements IClientCrudHandler{

    private final IClientServiceInputPort clientServiceInputPort;
    private final IClientMapper clientMapper;

    @Override
    public ClientResponseDto createClient(ClientCreateRequestDto clientRequest) {
        log.info(">>> Start handler createClient -> clientRequest:{}", clientRequest);
        Client client = clientMapper.clientCreateRequestDtoToClientModel(clientRequest);
        Client clientSaved = clientServiceInputPort.createClient(client);
        ClientResponseDto clientResponse = clientMapper.clientModelToClientClientResponseDto(clientSaved);
        log.info("<<< End handler createClient -> clientResponse:{}", clientResponse);
        return clientResponse;
    }

    @Override
    public List<ClientResponseDto> readAllClient() {
        log.info(">>> Start handler readAllClient");
        List<Client> clientList = clientServiceInputPort.readAllClient();
        List<ClientResponseDto> clientResponseDtoList = clientMapper.clientModelListToClientResponseDtoList(clientList);
        log.info("<<< End handler readAllClient -> clientResponseListSize:{}", clientResponseDtoList.size());
        return clientResponseDtoList;
    }

    @Override
    public ClientResponseDto readClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        log.info(">>> Start handler readClientByIdentificationTypeAndIdentificationNumber -> idType:{} - idNumber:{}", identificationType, identificationNumber);
        Client client = clientServiceInputPort.readClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        ClientResponseDto clientResponse = clientMapper.clientModelToClientClientResponseDto(client);
        log.info("<<< End handler readClientByIdentificationTypeAndIdentificationNumber -> response:{}", clientResponse);
        return clientResponse;

    }

    @Override
    public List<ClientResponseDto> readAllClientGreaterOrEqualThanAge(int age) {
        log.info(">>> Start handler readAllClientGreaterOrEqualThanAge -> age:{}", age);
        List<Client> clientList = clientServiceInputPort.readAllClientGreaterOrEqualThanAge(age);
        List<ClientResponseDto> clientResponseDtoList = clientMapper.clientModelListToClientResponseDtoList(clientList);
        log.info("<<< End handler readAllClientGreaterOrEqualThanAge -> clientResponseListSize:{}", clientResponseDtoList.size());
        return clientResponseDtoList;
    }

    @Override
    public ClientResponseDto updateClient(ClientUpdateRequestDto clientRequest) {
        log.info(">>> Start handler updateClient -> clientRequest:{}", clientRequest);
        Client client = clientMapper.clientUpdateRequestDtoToClientModel(clientRequest);
        Client clientUpdated = clientServiceInputPort.updateClient(client);
        ClientResponseDto clientResponse = clientMapper.clientModelToClientClientResponseDto(clientUpdated);
        log.info("<<< End handler updateClient -> clientResponse:{}", clientResponse);
        return clientResponse;
    }

    @Override
    public void deleteClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        log.info(">>> Start handler deleteClientByIdentificationTypeAndIdentificationNumber -> idType:{} - idNumber:{}", identificationType, identificationNumber);
        clientServiceInputPort.deleteClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        log.info("<<< End handler deleteClientByIdentificationTypeAndIdentificationNumber");
    }
}
