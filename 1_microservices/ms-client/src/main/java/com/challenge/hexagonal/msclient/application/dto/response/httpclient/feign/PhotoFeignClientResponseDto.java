package com.challenge.hexagonal.msclient.application.dto.response.httpclient.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa el DTO de respuesta para una foto cuando se utiliza el cliente http feign
 * @author dtrujilloc
 * @version 1.0.0 30/11/2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoFeignClientResponseDto {
    private String id;
    private String clientId;
    private String photoBase64;
}
