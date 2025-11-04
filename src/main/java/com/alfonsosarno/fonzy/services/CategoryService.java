package com.alfonsosarno.fonzy.services;

import com.alfonsosarno.fonzy.entities.Category;
import com.alfonsosarno.fonzy.payloads.CategoryRequestDTO;

import com.alfonsosarno.fonzy.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


//    creo nuova categoria


    public Category createCategory(CategoryRequestDTO body) {
        Category category = new Category();
        category.setName(body.name());

        categoryRepository.save(category);

        return new Category(category.getId(), category.getName());

    }

//    find all categories

    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }


//    aggiorna categoria

    public Category updateCategory(Long id, CategoryRequestDTO body){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata"));
        category.setName(body.name());
        categoryRepository.save(category);
        return new Category(category.getId(), category.getName());
    }

//    elimina categoria
    public void deleteCategory(Long id){
        if(!categoryRepository.existsById(id)){
            throw new RuntimeException("Categoria non trovata");
        }
        categoryRepository.deleteById(id);
    }
}
