package com.challenge.hexagonal.msphoto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el modelo de dominio para una photo
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    private String id;

    private Long clientId;

    // Base64
    private String photo;
}
