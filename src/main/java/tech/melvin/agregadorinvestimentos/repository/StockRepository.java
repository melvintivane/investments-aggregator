package tech.melvin.agregadorinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.melvin.agregadorinvestimentos.entity.Stock;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {
}
