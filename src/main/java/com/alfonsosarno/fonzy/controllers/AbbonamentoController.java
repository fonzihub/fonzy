package com.alfonsosarno.fonzy.controllers;

import com.alfonsosarno.fonzy.entities.Abbonamento;
import com.alfonsosarno.fonzy.entities.User;
import com.alfonsosarno.fonzy.payloads.NewAbbonamentoRequestDTO;
import com.alfonsosarno.fonzy.services.AbbonamentoService;
import com.alfonsosarno.fonzy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/abbonamenti")
public class AbbonamentoController {
    @Autowired
    AbbonamentoService abbonamentoService;
@Autowired
    UserService userService;

//    get all

    @GetMapping
    public List<Abbonamento> findAll(@AuthenticationPrincipal User user) {
        return this.abbonamentoService.findByUser(user);
    }

//    POST

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Abbonamento createAbbonamento(@RequestBody NewAbbonamentoRequestDTO body,
                                         @AuthenticationPrincipal User user) {
        System.out.println("BODY == " + body);
        return this.abbonamentoService.createAbbonamento(body,user);
    }

//    GET BY ID

    @GetMapping("/{abbonamentoId}")
    public Abbonamento findById(@PathVariable UUID abbonamentoId){
        return this.abbonamentoService.findById(abbonamentoId);
    }



//    PUT
    @PutMapping("/{abbonamentoId}")
    public Abbonamento findByIdAndUpdate(@PathVariable UUID abbonamentoId, @RequestBody NewAbbonamentoRequestDTO body){
        return this.abbonamentoService.findByIdAndUpdate(abbonamentoId,body);
    }


// DELETE

    @DeleteMapping("/{abbonamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID abbonamentoId){
       this.abbonamentoService.findByIdAndDelete(abbonamentoId);
    }
}
