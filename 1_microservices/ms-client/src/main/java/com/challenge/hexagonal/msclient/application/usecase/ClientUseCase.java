package com.challenge.hexagonal.msclient.application.usecase;

import com.challenge.hexagonal.msclient.domain.model.Client;
import com.challenge.hexagonal.msclient.domain.port.input.IClientServiceInputPort;
import com.challenge.hexagonal.msclient.domain.port.output.IClientPersistOutputPort;

import java.util.List;
import java.util.Optional;

/**
 * Clase que representa el caso de uso o servicio de un cliente, es decir donde existe la logica y reglas de negocio para
 * el cliente. Implementa el puerto de entrada y utiliza el puerto de salida.
 * @author dtrujilloc
 * @version 1.0.0 22/11/2022
 */
public class ClientUseCase implements IClientServiceInputPort {

    private final IClientPersistOutputPort clientPersistOutputPort;

    public ClientUseCase(IClientPersistOutputPort clientPersistOutputPort) {
        this.clientPersistOutputPort = clientPersistOutputPort;
    }

    @Override
    public Client createClient(Client client) {
        //TODO: comunicacion con el otro ms para guardar la foto

        return clientPersistOutputPort.saveClient(client);
    }

    @Override
    public List<Client> readAllClient() {
        List<Client> clientList = clientPersistOutputPort.getAllClient();

        //TODO: comunicacion con el otro ms para traer todas las fotos y setiarlas a cada objeto de cliente

        return clientList;
    }

    @Override
    public Client readClientById(Long id) {
        Client client = clientPersistOutputPort.getClientById(id).orElseThrow(RuntimeException::new);

        // TODO: comunicacion con el otro ms para traer la foto y setiarla al objeto de cliente

        return client;
    }

    @Override
    public Client readClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        Client client = clientPersistOutputPort.getClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber).orElseThrow(RuntimeException::new);

        // TODO: comunicacion con el otro ms para traer la foto y setiarla al objeto de cliente

        return client;
    }

    @Override
    public List<Client> readClientGreaterOrEqualThanAge(int age) {
        List<Client> clientList = clientPersistOutputPort.getClientGreaterOrEqualThanAge(age);

        // TODO: comunicacion con el otro ms para traer todas las fotos y setiarlas a cada objeto de cliente

        return clientList;
    }

    @Override
    public Client updateClient(Client client) {
        Client clientOld = clientPersistOutputPort.getClientById(client.getId()).orElseThrow(RuntimeException::new);

        // TODO: comunicacion con el otro ms para actualizar la foto

        clientOld.setName(client.getName());
        clientOld.setLastName(client.getLastName());
        clientOld.setBirthDate(client.getBirthDate());

        return clientPersistOutputPort.updateClient(clientOld);
    }

    @Override
    public void deleteClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        Client client = readClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);

        // TODO: comunicacion con el otro ms para eliminar la foto

        clientPersistOutputPort.deleteClientByIdentificationTypeAndIdentificationNumber(client.getIdentificationType(), client.getIdentificationNumber());
    }

    @Override
    public void deleteClientById(Long id) {
        Client client = readClientById(id);

        // TODO: comunicacion con el otro ms para eliminar la foto

        clientPersistOutputPort.deleteClientById(client.getId());
    }
}
