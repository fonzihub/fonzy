package com.alfonsosarno.fonzy.repositories;

import com.alfonsosarno.fonzy.entities.Abbonamento;
import com.alfonsosarno.fonzy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AbbonamentoRepository extends JpaRepository<Abbonamento, UUID> {
    List<Abbonamento> findByDataRinnovoBetween(LocalDate start, LocalDate end);
    List<Abbonamento> findByUser(User user);
    List<Abbonamento> findByDataRinnovo(LocalDate data);
}
