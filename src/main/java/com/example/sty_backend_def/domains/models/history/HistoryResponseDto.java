package com.example.sty_backend_def.domains.models.history;

import java.time.LocalDate;
import java.util.List;

public record HistoryResponseDto(
        List<History> histories
){
}
