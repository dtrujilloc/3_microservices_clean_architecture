package com.challenge.hexagonal.msclient.application.usecase;

import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign.PhotoFeignClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.httpclient.feign.PhotoFeignClientResponseDto;
import com.challenge.hexagonal.msclient.application.exception.ClientAlreadyExistException;
import com.challenge.hexagonal.msclient.application.exception.ClientNoDataFoundException;
import com.challenge.hexagonal.msclient.application.exception.ClientNotExistException;
import com.challenge.hexagonal.msclient.domain.model.Client;
import com.challenge.hexagonal.msclient.domain.port.input.IClientServiceInputPort;
import com.challenge.hexagonal.msclient.domain.port.output.httpclient.IPhotoHttpClientOutputPort;
import com.challenge.hexagonal.msclient.domain.port.output.persist.IClientPersistOutputPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase que representa el caso de uso o servicio de un cliente, es decir donde existe la logica y reglas de negocio para
 * el cliente. Implementa el puerto de entrada y utiliza el puerto de salida.
 * @author dtrujilloc
 * @version 1.0.0 22/11/2022
 *
 * Se agrega la instancia del puerto de salida para el cliente http, y se desarrolla la logica relacionada la informacion
 * de la foto
 * @author dtrujilloc
 * @version 1.0.1 01/12/2022
 */
@Slf4j
public class ClientUseCase implements IClientServiceInputPort {

    private final IClientPersistOutputPort clientPersistOutputPort;
    private final IPhotoHttpClientOutputPort photoHttpClientOutputPort;

    public ClientUseCase(IClientPersistOutputPort clientPersistOutputPort,
                         IPhotoHttpClientOutputPort photoHttpClientOutputPort) {
        this.clientPersistOutputPort = clientPersistOutputPort;
        this.photoHttpClientOutputPort = photoHttpClientOutputPort;
    }

    @Override
    public Client createClient(Client client) {
        log.info(">>> Start use case createClient -> client -- idType:{} - idNumber:{} - name:{} - lastName{} - birthDate:{}", client.getIdentificationType(), client.getIdentificationNumber(), client.getName(), client.getLastName(), client.getBirthDate());
        if (clientPersistOutputPort.getClientByIdentificationTypeAndIdentificationNumber(client.getIdentificationType(), client.getIdentificationNumber()) != null) {
            String messageError = String.format("The client can't be create because already exist with idType=%s and idNumber=%s", client.getIdentificationType(), client.getIdentificationNumber());
            log.error(messageError);
            throw new ClientAlreadyExistException(messageError);
        }

        Client clientSaved = clientPersistOutputPort.saveClient(client);

        PhotoFeignClientResponseDto photoFeignClientResponseDto = photoHttpClientOutputPort.createPhoto(
                PhotoFeignClientCreateRequestDto.builder()
                        .clientId(clientSaved.getIdentificationNumber())
                        .photoBase64(client.getPhoto())
                        .build()
        );
        clientSaved.setPhoto(photoFeignClientResponseDto.getPhotoBase64());

        log.info("<<< End use case createClient -> clientSaved -- id:{} - idType:{} - idNumber:{} - name:{} - lastName{} - age:{} - birthDate:{}", clientSaved.getId(), clientSaved.getIdentificationType(), clientSaved.getIdentificationNumber(), clientSaved.getName(), clientSaved.getLastName(), clientSaved.getAge(), clientSaved.getBirthDate());
        return clientSaved;
    }

    @Override
    public List<Client> readAllClient() {
        log.info(">>> Start use case readAllClient");
        List<Client> clientList = clientPersistOutputPort.getAllClient();

        if (clientList.isEmpty()) {
            String messageError = "The client list is empty";
            log.error(messageError);
            throw new ClientNoDataFoundException(messageError);
        }

        List<PhotoFeignClientResponseDto> photoFeignClientResponseDtoList = photoHttpClientOutputPort.readAllPhoto();

        clientList.forEach(clientTemp -> {
            Optional<PhotoFeignClientResponseDto> optionalPhotoTemp = photoFeignClientResponseDtoList.stream().filter(photoTemp -> Objects.equals(photoTemp.getClientId(), clientTemp.getIdentificationNumber())).findFirst();
            String photoBase64Temp = "Without Photo";
            if(optionalPhotoTemp.isPresent()) {
                photoBase64Temp = optionalPhotoTemp.get().getPhotoBase64();

            }
            clientTemp.setPhoto(photoBase64Temp);
        });

        log.info("<<< End use case readAllClient -> clientListSize:{}", clientList.size());
        return clientList;
    }

    @Override
    public Client readClientById(Long id) {
        log.info(">>> Start use case readClientById -> id:{}", id);
        Client client = clientPersistOutputPort.getClientById(id);

        if (client == null) {
            String messageError = String.format("The client not exist with id=%s", id.toString());
            log.error(messageError);
            throw new ClientNotExistException(messageError);
        }

        PhotoFeignClientResponseDto photoFeignClientResponseDto = photoHttpClientOutputPort.readPhotoByClientId(client.getIdentificationNumber());
        client.setPhoto(photoFeignClientResponseDto.getPhotoBase64());

        log.info("<<< End use case readClientById -> client -- id:{} - idType:{} - idNumber:{} - name:{} - lastName{} - age:{} - birthDate:{}", client.getId(), client.getIdentificationType(), client.getIdentificationNumber(), client.getName(), client.getLastName(), client.getAge(), client.getBirthDate());
        return client;
    }

    @Override
    public Client readClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        log.info(">>> Start use case readClientByIdentificationTypeAndIdentificationNumber -> idType:{} - idNumber:{}", identificationType, identificationNumber);
        Client client = clientPersistOutputPort.getClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);

        if (client == null) {
            String messageError = String.format("The client not exist with idType=%s and idNumber=%s", identificationType, identificationNumber);
            log.error(messageError);
            throw new ClientNotExistException(messageError);
        }

        PhotoFeignClientResponseDto photoFeignClientResponseDto = photoHttpClientOutputPort.readPhotoByClientId(client.getIdentificationNumber());
        client.setPhoto(photoFeignClientResponseDto.getPhotoBase64());

        log.info("<<< End use case readClientByIdentificationTypeAndIdentificationNumber -> client -- id:{} - idType:{} - idNumber:{} - name:{} - lastName{} - age:{} - birthDate:{}", client.getId(), client.getIdentificationType(), client.getIdentificationNumber(), client.getName(), client.getLastName(), client.getAge(), client.getBirthDate());
        return client;
    }

    @Override
    public List<Client> readAllClientGreaterOrEqualThanAge(int age) {
        log.info(">>> Start use case readAllClientGreaterOrEqualThanAge -> age:{}", age);
        List<Client> clientList = clientPersistOutputPort.getAllClient().stream().filter(clientTemp -> clientTemp.getAge() >= age).collect(Collectors.toList());

        if (clientList.isEmpty()) {
            String messageError = "The client list is empty";
            log.error(messageError);
            throw new ClientNoDataFoundException(messageError);
        }

        List<PhotoFeignClientResponseDto> photoFeignClientResponseDtoList = photoHttpClientOutputPort.readAllPhoto();

        clientList.forEach(clientTemp -> {
            Optional<PhotoFeignClientResponseDto> optionalPhotoTemp = photoFeignClientResponseDtoList.stream().filter(photoTemp -> Objects.equals(photoTemp.getClientId(), clientTemp.getIdentificationNumber())).findFirst();
            String photoBase64Temp = "Without Photo";
            if(optionalPhotoTemp.isPresent()) {
                photoBase64Temp = optionalPhotoTemp.get().getPhotoBase64();

            }
            clientTemp.setPhoto(photoBase64Temp);
        });

        log.info("<<< End use case readAllClientGreaterOrEqualThanAge -> clientListSize:{}", clientList.size());
        return clientList;
    }

    @Override
    public Client updateClient(Client client) {
        log.info(">>> Start use case updateClient -> clientRequest -- id:{} - idType:{} - idNumber:{} - name:{} - lastName{} - age:{} - birthDate:{}", client.getId(), client.getIdentificationType(), client.getIdentificationNumber(), client.getName(), client.getLastName(), client.getAge(), client.getBirthDate());
        Client clientOld = clientPersistOutputPort.getClientById(client.getId());

        if (clientOld == null) {
            String messageError = String.format("The client can't be update because no exist with id -> id=%s", client.getId().toString());
            log.error(messageError);
            throw new ClientNotExistException(messageError);
        }

        String identificationNumberOld = clientOld.getIdentificationNumber();

        clientOld.setIdentificationType(client.getIdentificationType());
        clientOld.setIdentificationNumber(client.getIdentificationNumber());
        clientOld.setName(client.getName());
        clientOld.setLastName(client.getLastName());
        clientOld.setBirthDate(client.getBirthDate());

        Client clientNew = clientPersistOutputPort.saveClient(clientOld);

        PhotoFeignClientResponseDto photoFeignClientResponseDtoOld = photoHttpClientOutputPort.readPhotoByClientId(identificationNumberOld);
        PhotoFeignClientResponseDto photoFeignClientResponseDto = photoHttpClientOutputPort.updatePhoto(
                PhotoFeignClientUpdateRequestDto.builder()
                        .id(photoFeignClientResponseDtoOld.getId())
                        .clientId(clientNew.getIdentificationNumber())
                        .photoBase64(client.getPhoto())
                        .build()
        );

        clientNew.setPhoto(photoFeignClientResponseDto.getPhotoBase64());

        log.info("<<< End use case updateClient -> clientNew -- id:{} - idType:{} - idNumber:{} - name:{} - lastName{} - age:{} - birthDate:{}", clientNew.getId(), clientNew.getIdentificationType(), clientNew.getIdentificationNumber(), clientNew.getName(), clientNew.getLastName(), clientNew.getAge(), clientNew.getBirthDate());
        return clientNew;
    }

    @Override
    public void deleteClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber) {
        log.info(">>> Start use case deleteClientByIdentificationTypeAndIdentificationNumber -> idType:{} - idNumber:{}", identificationType, identificationNumber);
        Client client = clientPersistOutputPort.getClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);

        if (client == null) {
            String messageError = String.format("The client can't be delete because no exist with idType=%s and idNumber=%s", identificationType, identificationNumber);
            log.error(messageError);
            throw new ClientNotExistException(messageError);
        }

        photoHttpClientOutputPort.deletePhotoByClientId(client.getIdentificationNumber());

        clientPersistOutputPort.deleteClientById(client.getId());
        log.info("<<< End use case deleteClientByIdentificationTypeAndIdentificationNumber");
    }
}
