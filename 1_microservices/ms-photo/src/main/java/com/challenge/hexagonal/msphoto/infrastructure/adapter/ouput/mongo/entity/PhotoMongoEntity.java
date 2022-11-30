package com.challenge.hexagonal.msphoto.infrastructure.adapter.ouput.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase que representa la entidad de foto para la persistencia por medio de mongo
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */

@Document(collection = "photo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoMongoEntity {

    @Id
    private String id;
    private Long clientId;
    private byte[] photo;
}
