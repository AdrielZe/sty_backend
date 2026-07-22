package com.example.sty_backend_def.repositories;

import com.example.sty_backend_def.domains.models.history.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, String> {
}
