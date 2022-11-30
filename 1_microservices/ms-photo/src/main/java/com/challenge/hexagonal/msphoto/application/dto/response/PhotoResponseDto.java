package com.challenge.hexagonal.msphoto.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el DTO de respuesta para una foto
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponseDto {
    private String id;
    private String clientId;
    private String photoBase64;
}
