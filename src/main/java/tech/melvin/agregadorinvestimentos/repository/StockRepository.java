package tech.melvin.agregadorinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.melvin.agregadorinvestimentos.entity.Stock;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
