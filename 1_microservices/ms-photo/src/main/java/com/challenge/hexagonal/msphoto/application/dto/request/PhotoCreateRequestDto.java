package com.challenge.hexagonal.msphoto.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el DTO de solicitud o peticion para la creacion de la foto
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoCreateRequestDto {

    private String clientId;
    private String photoBase64;
}
