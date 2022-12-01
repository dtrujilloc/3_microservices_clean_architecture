package com.challenge.hexagonal.msclient.application.dto.request.httpclient.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoFeignClientCreateRequestDto {
    private String clientId;
    private String photoBase64;
}
