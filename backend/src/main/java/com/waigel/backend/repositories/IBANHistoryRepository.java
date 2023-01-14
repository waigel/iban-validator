package com.waigel.backend.repositories;

import com.waigel.backend.entities.IBANHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBANHistoryRepository extends JpaRepository<IBANHistory, Long> {}
