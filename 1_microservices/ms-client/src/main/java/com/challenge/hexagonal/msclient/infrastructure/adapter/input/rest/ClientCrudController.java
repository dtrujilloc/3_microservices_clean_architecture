package com.challenge.hexagonal.msclient.infrastructure.adapter.input.rest;

import com.challenge.hexagonal.msclient.application.dto.request.ClientCreateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.request.ClientUpdateRequestDto;
import com.challenge.hexagonal.msclient.application.dto.response.ClientResponseDto;
import com.challenge.hexagonal.msclient.application.handler.IClientCrudHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que permite la exposicion de los endpoints relacionados con el cliente
 * @author dtrujilloc
 * @version 1.0.0 24/11/2022
 */
@Slf4j
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientCrudController {

    private final IClientCrudHandler clientCrudHandler;

    @Operation(summary = "create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "client is create", content = @Content),
            @ApiResponse(responseCode = "409", description = "client already exist", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody ClientCreateRequestDto requestDto) {
        log.info(">>> Start controller createClient -> request -- idType:{} - idNumber:{} - name:{} - lastName{} - birthDate:{}", requestDto.getIdentificationType(), requestDto.getIdentificationNumber(), requestDto.getName(), requestDto.getLastName(), requestDto.getBirthDate());
        ClientResponseDto response= clientCrudHandler.createClient(requestDto);
        log.info("<<< End controller createClient -> response -- id:{} - idType:{} - idNumber:{} - name:{} - lastName{} - age:{} - birthDate:{}", response.getId(), response.getIdentificationType(), response.getIdentificationNumber(), response.getName(), response.getLastName(), response.getAge(), response.getBirthDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "read all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all clients returned",
                    content = @Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = ClientResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> readAllClient() {
        log.info(">>> Start controller readAllClient");
        List<ClientResponseDto> responseList = clientCrudHandler.readAllClient();
        log.info("<<< End controller readAllClient -> responseListSize:{}", responseList.size());
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @Operation(summary = "read all clients by identificationType and identificationNumber")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all clients returned", content = @Content),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/identification-info")
    public ResponseEntity<ClientResponseDto> readClientByIdentificationTypeAndIdentificationNumber(@Parameter(description = "String that represent the type of client's identification")
                                                                                                       @RequestParam(name = "idType") String identificationType,
                                                                                                   @Parameter(description = "String that represent the number of client's identification")
                                                                                                   @RequestParam(name = "idNumber") String identificationNumber) {
        log.info(">>> Start controller readClientByIdentificationTypeAndIdentificationNumber -> idType:{} - idNumber:{}", identificationType, identificationNumber);
        ClientResponseDto response = clientCrudHandler.readClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        log.info("<<< End controller readClientByIdentificationTypeAndIdentificationNumber -> response -- id:{} - idType:{} - idNumber:{} - name:{} - lastName{} - age:{} - birthDate:{}", response.getId(), response.getIdentificationType(), response.getIdentificationNumber(), response.getName(), response.getLastName(), response.getAge(), response.getBirthDate());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "read all clients with age more greater o equal than param age")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all clients returned",
                    content = @Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = ClientResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/greater-or-equal-age")
    public ResponseEntity<List<ClientResponseDto>> readAllClientGreaterOrEqualThanAge(@Parameter(description = "Number that represent the reference age to compare the client age")
                                                                                          @RequestParam(name = "age")int age) {
        log.info(">>> Start controller readAllClientGreaterOrEqualThanAge -> age:{}", age);
        List<ClientResponseDto> responseList = clientCrudHandler.readAllClientGreaterOrEqualThanAge(age);
        log.info("<<< End controller readAllClientGreaterOrEqualThanAge -> responseListSize:{}", responseList.size());
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @Operation(summary = "update a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "client is updated", content = @Content),
            @ApiResponse(responseCode = "409", description = "client not exist", content = @Content)
    })
    @PutMapping
    public ResponseEntity<ClientResponseDto> updateClient(@RequestBody ClientUpdateRequestDto requestDto) {
        log.info(">>> Start controller updateClient -> request -- id:{} - idType:{} - idNumber:{} - name:{} - lastName{} - birthDate:{}", requestDto.getId(), requestDto.getIdentificationType(), requestDto.getIdentificationNumber(), requestDto.getName(), requestDto.getLastName(), requestDto.getBirthDate());
        ClientResponseDto response= clientCrudHandler.updateClient(requestDto);
        log.info("<<< End controller updateClient -> response -- id:{} - idType:{} - idNumber:{} - name:{} - lastName{} - age:{} - birthDate:{}", response.getId(), response.getIdentificationType(), response.getIdentificationNumber(), response.getName(), response.getLastName(), response.getAge(), response.getBirthDate());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "delete a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "client is updated", content = @Content),
            @ApiResponse(responseCode = "409", description = "client not exist", content = @Content)
    })
    @DeleteMapping("/identification-info")
    public ResponseEntity<Void> deleteClientByIdentificationTypeAndIdentificationNumber(@Parameter(description = "String that represent the type of client's identification of")
                                                                                            @RequestParam(name = "idType") String identificationType,
                                                                                        @Parameter(description = "String that represent the number of client's identification")
                                                                                        @RequestParam(name = "idNumber") String identificationNumber) {
        log.info(">>> Start controller deleteClientByIdentificationTypeAndIdentificationNumber -> -> idType:{} - idNumber:{}", identificationType, identificationNumber);
        clientCrudHandler.deleteClientByIdentificationTypeAndIdentificationNumber(identificationType, identificationNumber);
        log.info("<<< End controller deleteClientByIdentificationTypeAndIdentificationNumber");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
