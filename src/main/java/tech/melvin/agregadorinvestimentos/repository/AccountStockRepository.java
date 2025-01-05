package tech.melvin.agregadorinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.melvin.agregadorinvestimentos.entity.AccountStock;
import tech.melvin.agregadorinvestimentos.entity.AccountStockId;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
