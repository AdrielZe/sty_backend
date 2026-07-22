package com.example.sty_backend_def.controllers;

import com.example.sty_backend_def.domains.models.history.HistoryRequestDto;
import com.example.sty_backend_def.repositories.HistoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController {

//    private HistoryService service;
//
//    public HistoryController(HistoryService service) {
//        this.service = service;
//    }
//
//    @PostMapping
//    public ResponseEntity createHistory(@Valid @RequestBody HistoryRequestDto data) {
//        return ResponseEntity.ok(service.createHistory(data));
//    }

}
