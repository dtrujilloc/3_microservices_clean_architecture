package com.challenge.hexagonal.msclient.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Clase que representa el DTO de respuesta para los clientes
 * @author dtrujilloc
 * @version 1.0.0 22/11/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {

    private Long id;

    // Tipo de identificacion -> Ej: CC, TI
    private String identificationType;

    // Numero de identificacion
    private String identificationNumber;

    private String name;

    private String lastName;

    private int age;

    private Date birthDate;

    private String photo;
}
