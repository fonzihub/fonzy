package com.alfonsosarno.fonzy.repositories;

import com.alfonsosarno.fonzy.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
