package com.challenge.hexagonal.msclient.domain.port.output.persist;

import com.challenge.hexagonal.msclient.domain.model.Client;

import java.util.List;

/**
 * Interface que representa el puerto de salida para utilizacion en la capa de aplicacion (caso de uso) y la
 * posterior implementacion por el adaptador de persistencia
 * @author dtrujilloc
 * @version 1.0.0 22/11/2022
 */
public interface IClientPersistOutputPort {

    /**
     * Permite guardar un un cliente
     * @param client Objeto de tipo Client que contiene la informacion a guardar
     * @return Un objeto de tipo Client con la informacion guardada
     */
    Client saveClient(Client client);

    /**
     * Permite leer todos los clientes
     * @return una lista de clientes
     */
    List<Client> getAllClient();

    /**
     * Permite leer un cliente por su identificador con el que se persistio
     * @param id Long que representa el identificador con el que se presistio el cliente
     * @return un objeto de tipo client con la informacion correspondiente
     */
    Client getClientById(Long id);

    /**
     * Permite leer un cliente por el tipo de identificacion y numero de identificacion
     * @param identificationType String que representa el tipo de identificacion
     * @param identificationNumber String que representa el numero de identificacion
     * @return Un objeto de tipo Clien con la informacion correspondiente
     */
    Client getClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber);

    /**
     * Permite eliminar un cliente por el identificador de persistencia
     * @param id Long que representa el identificador de persistencia
     */
    void deleteClientById(Long id);
}
