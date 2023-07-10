package com.justpay.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justpay.app.entities.Clients;

public interface ClientsRepository extends JpaRepository<Clients, Long> {

}
