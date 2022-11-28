package com.challenge.hexagonal.msclient.application.handler;

import com.challenge.hexagonal.msclient.application.dto.request.ClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.ClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.ClientResponseDto;

import java.util.List;

/**
 * Inteface que representa la plantilla que permite la orquestacion de la transformacion de Objetos del modelo a DTO's y viceversa. Implementacion
 * del patron Fachada
 * @author dtrujilloc
 * @version 1.0.0 23/11/2022
 */
public interface IClientCrudHandler {

    /**
     * Permite realizar el proceso de creacion un un cliente
     * @param clientRequest Objeto de tipo ClientCreateRequestDto que contiene la informacion a guardar
     * @return Un objeto de tipo ClientResponseDto con la informacion guardada
     */
    ClientResponseDto createClient(ClientCreateRequestDto clientRequest);

    /**
     * Permite realizar el proceso de buscar o leer todos los clientes
     * @return una lista de ClientResponseDto
     */
    List<ClientResponseDto> readAllClient();

    /**
     * Permite realizar el proceso de buscar o leer un cliente por el tipo de identificacion y numero de identificacion
     * @param identificationType String que representa el tipo de identificacion
     * @param identificationNumber String que representa el numero de identificacion
     * @return Un objeto de tipo Clien con la informacion correspondiente
     */
    ClientResponseDto readClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber);

    /**
     * Permite realizar el proceso de buscar o leer los clientes que tengan una edad mayor o igual a la especificada por parametro
     * @param age int que representa la edad de referencia para buscar los clientes
     * @return Una lista de Client que representan los clientes con edad mayores o igual a especificada
     */
    List<ClientResponseDto> readAllClientGreaterOrEqualThanAge(int age);

    /**
     * Permite realizar el proceso de actualizar la informacion de un cliente
     * @param clientRequest Objeto de tipo ClientUpdateRequestDto con la informacion a actualizar
     * @return un objeto de tipo ClientResponseDto con la informacion actualizada
     */
    ClientResponseDto updateClient(ClientUpdateRequestDto clientRequest);

    /**
     * Permite realizar el proceso de eliminar un cliente por el tipo de identificacion y numero de identificacion
     * @param identificationType String que representa el tipo de identificacion
     * @param identificationNumber String que representa el numero de identificacion
     */
    void deleteClientByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber);
}
