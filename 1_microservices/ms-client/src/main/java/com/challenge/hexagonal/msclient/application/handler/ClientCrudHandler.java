package com.challenge.hexagonal.msclient.application.handler;

import com.challenge.hexagonal.msclient.application.dto.request.ClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.ClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.ClientResponseDto;
import com.challenge.hexagonal.msclient.application.mapper.IClientMapper;
import com.challenge.hexagonal.msclient.domain.model.Client;
import com.challenge.hexagonal.msclient.domain.port.input.IClientServiceInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase que implementa y especifica la logica para la orquestacion de la transformacion de Objetos relacinados al CRUD de clientes
 * @author dtrujilloc
 * @version 1.0.0 23/11/2022
 */
@Service
@RequiredArgsConstructor
public class ClientCrudHandler implements IClientCrudHandler{

    private final IClientServiceInputPort clientServiceInputPort;
    private final IClientMapper clientMapper;

    @Override
    public ClientResponseDto createClient(ClientCreateRequestDto clientRequest) {
        Client client = clientMapper.clientCreateRequestDtoToClientModel(clientRequest);
        Client clientSaved = clientServiceInputPort.createClient(client);
        ClientResponseDto clientResponse = clientMapper.clientModelToClientClientResponseDto(clientSaved);
        return clientResponse;
    }

    @Override
    public List<ClientResponseDto> readAllClient() {
        List<Client> clientList = clientServiceInputPort.readAllClient();
        List<ClientResponseDto> clientResponseDtoList = clientMapper.clientModelListToClientResponseDtoList(clientList);
        return clientResponseDtoList;
    }

    @Override
    public ClientResponseDto readClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        Client client = clientServiceInputPort.readClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        ClientResponseDto clientResponse = clientMapper.clientModelToClientClientResponseDto(client);
        return clientResponse;

    }

    @Override
    public List<ClientResponseDto> readClientGreaterOrEqualThanAge(int age) {
        List<Client> clientList = clientServiceInputPort.readClientGreaterOrEqualThanAge(age);
        List<ClientResponseDto> clientResponseDtoList = clientMapper.clientModelListToClientResponseDtoList(clientList);
        return clientResponseDtoList;
    }

    @Override
    public ClientResponseDto updateClient(ClientUpdateRequestDto clientRequest) {
        Client client = clientMapper.clientUpdateRequestDtoToClientModel(clientRequest);
        Client clientUpdated = clientServiceInputPort.updateClient(client);
        ClientResponseDto clientResponse = clientMapper.clientModelToClientClientResponseDto(clientUpdated);
        return clientResponse;
    }

    @Override
    public void deleteClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        Client client = clientServiceInputPort.readClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        clientServiceInputPort.deleteClientById(client.getId());
    }
}
