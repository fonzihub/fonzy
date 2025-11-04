package com.alfonsosarno.fonzy.payloads;

import com.alfonsosarno.fonzy.entities.frequenza;

import java.time.LocalDate;
import java.util.UUID;

public record NewAbbonamentoRequestDTO(UUID userId,
                                       Long catalogId,
                                       double price,
                                       LocalDate dataRinnovo,
                                       frequenza frequenza) {
}
