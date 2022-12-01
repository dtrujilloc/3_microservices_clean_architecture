package com.challenge.hexagonal.msclient.infrastructure.adapter.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.Period;

/**
 * Clase que representa la entidad para la persistencia por medio de JPA
 * @author dtrujilloc
 * @version 1.0.0 24/11/2022
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class ClientJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_type", nullable = false)
    private String identificationType;

    @Column(name = "identification_number", nullable = false)
    private String identificationNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
}
