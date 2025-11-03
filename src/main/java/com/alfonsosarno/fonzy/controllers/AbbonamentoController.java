package com.alfonsosarno.fonzy.controllers;

import com.alfonsosarno.fonzy.entities.Abbonamento;
import com.alfonsosarno.fonzy.services.AbbonamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abbonamenti")
public class AbbonamentoController {
    @Autowired
    AbbonamentoService abbonamentoService;


//    get all

    @GetMapping
    public List<Abbonamento> findAll(){
        return this.abbonamentoService.findAll();
    }

//    POST

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Abbonamento createAbbonamento(@RequestBody NewAbbonamentoDTO body){

    }
}
