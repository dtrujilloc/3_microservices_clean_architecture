package com.challenge.hexagonal.msclient.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Clase que representa la entidad para un cliente dentro del modelo
 * @author dtrujilloc
 * @version 1.0.0 22/11/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private Long id;

    // Tipo de identificacion -> Ej: CC, TI
    private String identificationType;

    // Numero de identificacion
    private String identificationNumber;

    private String name;

    private String lastName;

    private LocalDate birthDate;

    private int age;

    private String photo;
}
