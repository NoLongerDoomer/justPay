package com.justpay.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justpay.app.entities.KeyStorageEntity;

public interface KeyStorageRepository extends JpaRepository<KeyStorageEntity, Long> {

}
