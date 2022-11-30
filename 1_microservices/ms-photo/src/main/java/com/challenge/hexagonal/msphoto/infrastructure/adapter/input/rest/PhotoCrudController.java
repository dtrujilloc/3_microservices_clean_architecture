package com.challenge.hexagonal.msphoto.infrastructure.adapter.input.rest;

import com.challenge.hexagonal.msphoto.application.dto.request.PhotoCreateRequestDto;
import com.challenge.hexagonal.msphoto.application.dto.request.PhotoUpdateRequestDto;
import com.challenge.hexagonal.msphoto.application.dto.response.PhotoResponseDto;
import com.challenge.hexagonal.msphoto.application.handler.IPhotoCrudHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Clase que permite la exposicion de los endpoints relacionados con la foto
 * @author dtrujilloc
 * @version 1.0.0 28/11/2022
 */
@Slf4j
@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoCrudController {

    private final IPhotoCrudHandler photoCrudHandler;

    @Operation(summary = "save a new photo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "photo is create", content = @Content),
            @ApiResponse(responseCode = "409", description = "photo already exist", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PhotoResponseDto> createPhoto(@RequestBody PhotoCreateRequestDto requestDto) {
        log.info(">>> Start controller createPhoto");
        PhotoResponseDto response= photoCrudHandler.createPhoto(requestDto);
        log.info("<<< End controller createPhoto -> responseId:{}", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "read all photos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all photos returned",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PhotoResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PhotoResponseDto>> readAllPhoto() {
        log.info(">>> Start controller readAllPhoto");
        List<PhotoResponseDto> responseList = photoCrudHandler.readAllPhoto();
        log.info("<<< End controller readAllPhoto -> responseListSize:{}", responseList.size());
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @Operation(summary = "read photo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "photo returned", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PhotoResponseDto> readPhotoById(@PathVariable(value = "id") String id) {
        log.info("<<< Start controller readPhotoById -> id:{}", id);
        PhotoResponseDto response = photoCrudHandler.readPhotoById(id);
        log.info("<<< End controller readPhotoById -> responseId:{}", response.getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "read photo by client's identification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "photo returned", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/client/{id}")
    public ResponseEntity<PhotoResponseDto> readPhotoByClientId(@PathVariable(value = "id") Long clientId) {
        log.info("<<< Start controller readPhotoByClientId -> clientId:{}", clientId);
        PhotoResponseDto response = photoCrudHandler.readPhotoByClientId(clientId);
        log.info("<<< End controller readPhotoByClientId -> responseId:{}", response.getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "update a photo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "photo is updated", content = @Content),
            @ApiResponse(responseCode = "409", description = "photo not exist", content = @Content)
    })
    @PutMapping
    public ResponseEntity<PhotoResponseDto> updatePhoto(@RequestBody PhotoUpdateRequestDto requestDto) {
        log.info(">>> Start controller updatePhoto -> requestId:{}", requestDto.getId());
        PhotoResponseDto response= photoCrudHandler.updatePhoto(requestDto);
        log.info("<<< End controller updatePhoto -> responseId:{}", response.getId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "delete a photo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "photo is updated", content = @Content),
            @ApiResponse(responseCode = "409", description = "photo not exist", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhotoById(@PathVariable(value = "id") String id) {
        log.info(">>> Start controller deletePhotoById -> id:{}", id);
        photoCrudHandler.deletePhotoById(id);
        log.info("<<< End controller deletePhotoById");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "delete a photo by client's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "photo is deleted", content = @Content),
            @ApiResponse(responseCode = "409", description = "photo not exist", content = @Content)
    })
    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deletePhotoByClientId(@PathVariable(value = "id") Long clientId) {
        log.info(">>> Start controller deletePhotoByClientId -> clientId:{}", clientId);
        photoCrudHandler.deletePhotoByClientId(clientId);
        log.info("<<< End controller deletePhotoByClientId");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
