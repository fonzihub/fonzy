package com.alfonsosarno.fonzy.repositories;

import com.alfonsosarno.fonzy.entities.AbbonamentoCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<AbbonamentoCatalogo,Long> {
}
