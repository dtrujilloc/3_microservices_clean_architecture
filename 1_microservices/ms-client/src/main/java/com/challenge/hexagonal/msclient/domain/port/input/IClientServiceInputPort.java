package com.challenge.hexagonal.msclient.domain.port.input;

import com.challenge.hexagonal.msclient.domain.model.Client;

import java.util.List;
import java.util.Optional;

/**
 * Inteface que representa el puerto de entrada para su implementacion en la capa de aplicacion(caso de uso) y
 * la posterior utilizacion del adaptador
 * @author dtrujilloc
 * @version 1.0.0 22/11/2022
 */
public interface IClientServiceInputPort {

    /**
     * Permite crear un un cliente
     * @param client Objeto de tipo Client que contiene la informacion a guardar
     * @return Un objeto de tipo Client con la informacion guardada
     */
    Client createClient(Client client);

    /**
     * Permite buscar o leer todos los clientes
     * @return una lista de clientes
     */
    List<Client> readAllClient();

    /**
     * Permite buscar o leer un cliente por su identificador con el que se persistio
     * @param id Long que representa el identificador de persistencia
     * @return Un objeto de tipo Client
     */
    Client readClientById(Long id);

    /**
     * Permite buscar o leer un cliente por el tipo de identificacion y numero de identificacion
     * @param identificationType String que representa el tipo de identificacion
     * @param identificationNumber String que representa el numero de identificacion
     * @return Un objeto de tipo Clien con la informacion correspondiente
     */
    Client readClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber);

    /**
     * Permite buscar o leer los clientes que tengan una edad mayor o igual a la especificada por parametro
     * @param age int que representa la edad de referencia para buscar los clientes
     * @return Una lista de Client que representan los clientes con edad mayores o igual a especificada
     */
    List<Client> readClientGreaterOrEqualThanAge(int age);

    /**
     * Permite actualizar la informacion de un cliente
     * @param client Objeto de tipo Client con la informacion a actualizar
     * @return un objeto de tipo Client con la informacion actualizada
     */
    Client updateClient(Client client);

    /**
     * Permite eliminar un cliente por el tipo de identificacion y numero de identificacion
     * @param identificationType String que representa el tipo de identificacion
     * @param identificationNumber String que representa el numero de identificacion
     */
    void deleteClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber);

    /**
     * Permite eliminar un cliente por el identificador con el que se persistio
     * @param id Long que representa el identificador del cliente con el que se persistio
     */
    void deleteClientById(Long id);
}
