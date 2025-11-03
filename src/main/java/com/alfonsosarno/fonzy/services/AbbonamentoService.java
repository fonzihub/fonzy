package com.alfonsosarno.fonzy.services;

import com.alfonsosarno.fonzy.entities.Abbonamento;
import com.alfonsosarno.fonzy.repositories.AbbonamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbbonamentoService {
    @Autowired private AbbonamentoRepository abbonamentoRepository;

//GET

    public List<Abbonamento> findAll(){

        return this.abbonamentoRepository.findAll();
    }
}
