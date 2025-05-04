package com.example.telephonesproject.repository;


import com.example.telephonesproject.model.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    Optional<Telephone> findByImei(String imei);
}
