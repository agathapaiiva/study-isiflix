package br.com.isiflix.isibank.dto;

import java.time.LocalDateTime;

public record ExtractDTO(Integer numberAccount, LocalDateTime dateInit, LocalDateTime dateFinal){
}
