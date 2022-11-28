package com.challenge.hexagonal.msclient.infrastructure.adapter.output.jpa.repository;

import com.challenge.hexagonal.msclient.infrastructure.adapter.output.jpa.entity.ClientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IClientJpaRepository extends JpaRepository<ClientJpaEntity, Long> {

    Optional<ClientJpaEntity> findByIdentificationTypeAndIdentificationNumber(String identificationType, String identificationNumber);

}
