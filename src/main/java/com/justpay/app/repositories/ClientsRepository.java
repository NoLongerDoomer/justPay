package com.justpay.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justpay.app.entities.ClientEntity;

public interface ClientsRepository extends JpaRepository<ClientEntity, Long> {

}
