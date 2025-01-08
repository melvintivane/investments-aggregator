package tech.melvin.agregadorinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.melvin.agregadorinvestimentos.entity.AccountStock;
import tech.melvin.agregadorinvestimentos.entity.AccountStockId;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
