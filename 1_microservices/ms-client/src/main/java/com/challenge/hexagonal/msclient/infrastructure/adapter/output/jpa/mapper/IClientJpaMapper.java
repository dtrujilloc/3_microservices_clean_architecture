package com.challenge.hexagonal.msclient.infrastructure.adapter.output.jpa.mapper;

import com.challenge.hexagonal.msclient.domain.model.Client;
import com.challenge.hexagonal.msclient.infrastructure.adapter.output.jpa.entity.ClientJpaEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Interface que especifica los metodos de mapeo o conversion de la informacion de un cliente en la capa de infraestructura
 * para el adaptador de jpa
 * @author dtrujilloc
 * @version 1.0.0 24/11/2022
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface IClientJpaMapper {

    @AfterMapping
    default void calculateAge(final ClientJpaEntity source, @MappingTarget Client target) {
        int age = Period.between(source.getBirthDate(), LocalDate.now()).getYears();
        target.setAge(age);
    }

    Client clientJpaEntityToClientModel(ClientJpaEntity clientJpaEntity);

    ClientJpaEntity clientModelToClientJpaEntity(Client clientModel);

    List<Client> clientJpaEntityListToClientModelList(List<ClientJpaEntity> clientModelList);

    List<ClientJpaEntity> clientModelListToClientJpaEntityList(List<Client> clientModelList);
}
