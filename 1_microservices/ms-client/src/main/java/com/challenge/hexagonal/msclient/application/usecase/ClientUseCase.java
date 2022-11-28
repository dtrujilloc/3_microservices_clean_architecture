package com.challenge.hexagonal.msclient.application.usecase;

import com.challenge.hexagonal.msclient.application.exception.ClientAlreadyExistException;
import com.challenge.hexagonal.msclient.application.exception.ClientNoDataFoundException;
import com.challenge.hexagonal.msclient.application.exception.ClientNotExistException;
import com.challenge.hexagonal.msclient.domain.model.Client;
import com.challenge.hexagonal.msclient.domain.port.input.IClientServiceInputPort;
import com.challenge.hexagonal.msclient.domain.port.output.IClientPersistOutputPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que representa el caso de uso o servicio de un cliente, es decir donde existe la logica y reglas de negocio para
 * el cliente. Implementa el puerto de entrada y utiliza el puerto de salida.
 * @author dtrujilloc
 * @version 1.0.0 22/11/2022
 */
@Slf4j
public class ClientUseCase implements IClientServiceInputPort {

    private final IClientPersistOutputPort clientPersistOutputPort;

    public ClientUseCase(IClientPersistOutputPort clientPersistOutputPort) {
        this.clientPersistOutputPort = clientPersistOutputPort;
    }

    @Override
    public Client createClient(Client client) {
        log.info(">>> Start use case createClient -> client:{}", client);
        if (clientPersistOutputPort.getClientByIdentificationTypeAndIdentificationNumber(client.getIdentificationType(), client.getIdentificationNumber()) != null) {
            throw new ClientAlreadyExistException(String.format("The client can't be create because already exist with idType=%s and idNumber=%s", client.getIdentificationType(), client.getIdentificationNumber()));
        }
        //TODO: comunicacion con el otro ms para guardar la foto

        Client clientSaved = clientPersistOutputPort.saveClient(client);
        log.info("<<< End use case createClient -> clientSaved:{}", clientSaved);
        return clientSaved;
    }

    @Override
    public List<Client> readAllClient() {
        log.info(">>> Start use case readAllClient");
        List<Client> clientList = clientPersistOutputPort.getAllClient();

        if (clientList.isEmpty()) {
            throw new ClientNoDataFoundException("The client list is empty");
        }

        //TODO: comunicacion con el otro ms para traer todas las fotos y setiarlas a cada objeto de cliente
        log.info("<<< End use case readAllClient -> clientListSize:{}", clientList.size());
        return clientList;
    }

    @Override
    public Client readClientById(Long id) {
        log.info(">>> Start use case readClientById -> id:{}", id);
        Client client = clientPersistOutputPort.getClientById(id);

        if (client == null) {
            throw new ClientNotExistException(String.format("The client not exist with id=%s", id.toString()));
        }

        // TODO: comunicacion con el otro ms para traer la foto y setiarla al objeto de cliente
        log.info("<<< End use case readClientById -> client:{}", client);
        return client;
    }

    @Override
    public Client readClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        log.info(">>> Start use case readClientByIdentificationTypeAndIdentificationNumber -> idType:{} - idNumber:{}", identificationType, identificationNumber);
        Client client = clientPersistOutputPort.getClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);

        if (client == null) {
            throw new ClientNotExistException(String.format("The client not exist with idType=%s and idNumber=%s", identificationType, identificationNumber));
        }

        // TODO: comunicacion con el otro ms para traer la foto y setiarla al objeto de cliente
        log.info("<<< End use case readClientByIdentificationTypeAndIdentificationNumber -> client:{}", client);
        return client;
    }

    @Override
    public List<Client> readAllClientGreaterOrEqualThanAge(int age) {
        log.info(">>> Start use case readAllClientGreaterOrEqualThanAge -> age:{}", age);
        List<Client> clientList = clientPersistOutputPort.getAllClient().stream().filter(clientTemp -> clientTemp.getAge() >= age).collect(Collectors.toList());

        if (clientList.isEmpty()) {
            throw new ClientNoDataFoundException("The client list is empty");
        }

        // TODO: comunicacion con el otro ms para traer todas las fotos y setiarlas a cada objeto de cliente
        log.info("<<< End use case readAllClientGreaterOrEqualThanAge -> clientListSize:{}", clientList.size());
        return clientList;
    }

    @Override
    public Client updateClient(Client client) {
        log.info(">>> Start use case updateClient -> clientRequest:{}", client);
        Client clientOld = clientPersistOutputPort.getClientById(client.getId());

        if (clientOld == null) {
            throw new ClientNotExistException(String.format("The client can't be update because no exist with id -> id=%s", client.getId().toString()));

        }

        // TODO: comunicacion con el otro ms para actualizar la foto

        clientOld.setIdentificationType(client.getIdentificationType());
        clientOld.setIdentificationNumber(client.getIdentificationNumber());
        clientOld.setName(client.getName());
        clientOld.setLastName(client.getLastName());
        clientOld.setBirthDate(client.getBirthDate());

        Client clientNew = clientPersistOutputPort.saveClient(clientOld);
        log.info("<<< End use case updateClient -> client:{}", clientNew);
        return clientNew;
    }

    @Override
    public void deleteClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        log.info(">>> Start use case deleteClientByIdentificationTypeAndIdentificationNumber -> idType:{} - idNumber:{}", identificationType, identificationNumber);
        Client client = clientPersistOutputPort.getClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);

        if (client == null) {
            throw new ClientNotExistException(String.format("The client can't be delete because no exist with idType=%s and idNumber=%s", identificationType, identificationNumber));
        }

        // TODO: comunicacion con el otro ms para eliminar la foto

        clientPersistOutputPort.deleteClientById(client.getId());
        log.info("<<< End use case deleteClientByIdentificationTypeAndIdentificationNumber");
    }
}
