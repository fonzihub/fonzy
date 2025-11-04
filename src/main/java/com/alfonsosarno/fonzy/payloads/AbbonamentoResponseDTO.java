package com.alfonsosarno.fonzy.payloads;

import com.alfonsosarno.fonzy.entities.frequenza;

import java.time.LocalDate;
import java.util.UUID;

public record AbbonamentoResponseDTO(UUID id,
                                     String serviceName,
                                     String categoryName,
                                     double price,
                                     LocalDate dataRinnovo,
                                     frequenza frequenza) {
}
