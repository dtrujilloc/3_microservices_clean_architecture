package com.challenge.hexagonal.msclient.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

/**
 * Clase que representa el DTO de solicitud o peticion para la actualizacion de los clientes
 * @author dtrujilloc
 * @version 1.0.0 23/11/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateRequestDto {

    private Long id;

    // Tipo de identificacion -> Ej: CC, TI
    private String identificationType;

    // Numero de identificacion
    private String identificationNumber;

    private String name;

    private String lastName;

    private LocalDate birthDate;

    private String photo;
}
