package com.alfonsosarno.fonzy.controllers;

import com.alfonsosarno.fonzy.entities.Category;
import com.alfonsosarno.fonzy.payloads.CategoryRequestDTO;
import com.alfonsosarno.fonzy.payloads.CategoryResponseDTO;
import com.alfonsosarno.fonzy.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    //    leggi tutte le categorie
@GetMapping
    public List<Category> findAll(){
        return this.categoryService.findAll();
    }

//    crea nuova categoria
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody CategoryRequestDTO body){
        return categoryService.createCategory(body);
    }

//    Aggiorna

    @PutMapping("/{id}")
    public Category upadateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO body){
        return categoryService.updateCategory(id,body);
    }

//    DELETE

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

}
