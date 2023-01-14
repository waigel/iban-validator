package com.waigel.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IBANHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Column(nullable = false)
  private String iban;

  private String blz;
  private String ip;

  @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
  @CreatedDate
  private LocalDateTime createdAt;

  public IBANHistory(final String iban, final String ip, final String blz) {
    this.iban = iban;
    this.ip = ip;
    this.blz = blz;
  }
}
