package com.challenge.hexagonal.msclient.infrastructure.adapter.output.jpa;

import com.challenge.hexagonal.msclient.domain.model.Client;
import com.challenge.hexagonal.msclient.domain.port.output.persist.IClientPersistOutputPort;
import com.challenge.hexagonal.msclient.infrastructure.adapter.output.jpa.entity.ClientJpaEntity;
import com.challenge.hexagonal.msclient.infrastructure.adapter.output.jpa.mapper.IClientJpaMapper;
import com.challenge.hexagonal.msclient.infrastructure.adapter.output.jpa.repository.IClientJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase que representa la implementacion en detalle del adaptador de salida para JPA a partir del puerto de salida
 * @author dtrujilloc
 * @version 1.0.0 24/11/2022
 *
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class ClientJpaAdapter implements IClientPersistOutputPort {

    private final IClientJpaRepository clientJpaRepository;
    private final IClientJpaMapper clientJpaMapper;

    @Override
    public Client saveClient(Client client) {
        log.info(">>> Start adapter createClient -> client -- idType:{} - idNumber:{} - name:{} - lastName{} - birthDate:{}", client.getIdentificationType(), client.getIdentificationNumber(), client.getName(), client.getLastName(), client.getBirthDate());
        ClientJpaEntity clientJpaEntity = clientJpaMapper.clientModelToClientJpaEntity(client);
        ClientJpaEntity clientSaved = clientJpaRepository.save(clientJpaEntity);
        Client clientResponse = clientJpaMapper.clientJpaEntityToClientModel(clientSaved);
        log.info("<<< End adapter createClient -> clientSaved:{}", clientResponse);
        return clientResponse;
    }

    @Override
    public List<Client> getAllClient() {
        log.info(">>> Start adapter getAllClient");
        List<ClientJpaEntity> clientJpaEntityList = clientJpaRepository.findAll();
        List<Client> clientList = clientJpaMapper.clientJpaEntityListToClientModelList(clientJpaEntityList);
        log.info("<<< End adapter getAllClient -> clientListSize:{}", clientList.size());
        return clientList;
    }

    @Override
    public Client getClientById(Long id) {
        log.info(">>> Start adapter readClientById -> id:{}", id);
        ClientJpaEntity clientJpaEntity = clientJpaRepository.findById(id).orElse(null);
        Client client = clientJpaMapper.clientJpaEntityToClientModel(clientJpaEntity);
        log.info("<<< End adapter readClientById -> client:{}", client);
        return client;
    }

    @Override
    public Client getClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        log.info(">>> Start adapter readClientByIdentificationTypeAndIdentificationNumber -> idType:{} - idNumber:{}", identificationType, identificationNumber);
        ClientJpaEntity clientJpaEntity = clientJpaRepository.findByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber).orElse(null);
        Client client = clientJpaMapper.clientJpaEntityToClientModel(clientJpaEntity);
        log.info("<<< End adapter readClientByIdentificationTypeAndIdentificationNumber -> client:{}", client);
        return client;
    }

    @Override
    public void deleteClientById(Long id) {
        log.info(">>> Start adapter deleteClientById -> id:{}", id);
        clientJpaRepository.deleteById(id);
        log.info("<<< End adapter deleteClientById");
    }
}
