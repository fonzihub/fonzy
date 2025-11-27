package com.alfonsosarno.fonzy.payloads;

import com.alfonsosarno.fonzy.entities.frequenza;

import java.time.LocalDate;
import java.util.UUID;

public record NewAbbonamentoRequestDTO(String nomeAbbonamento,
                                       Double prezzo,
                                       LocalDate dataRinnovo,
                                       String categoria,
                                       frequenza frequenza,
                                       String logoUrl) {
}
