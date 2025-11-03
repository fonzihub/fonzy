package com.alfonsosarno.fonzy.repositories;

import com.alfonsosarno.fonzy.entities.Abbonamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AbbonamentoRepository extends JpaRepository<Abbonamento, UUID> {
}
