package br.com.isiflix.isibank.dto;

import java.time.LocalDateTime;

public record PaymentDTO (
         Integer account,
         LocalDateTime dateTime,
         String numberDoc,
         String description,
         Double value) {
}
