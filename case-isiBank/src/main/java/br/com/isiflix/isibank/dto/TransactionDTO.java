package br.com.isiflix.isibank.dto;

import java.time.LocalDateTime;

public record TransactionDTO(Integer accountOrigin,
                             Integer accountDestiny,
                             LocalDateTime dateTime,
                             Double value,
                             String description) {
}
