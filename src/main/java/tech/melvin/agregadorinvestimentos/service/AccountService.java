package tech.melvin.agregadorinvestimentos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.melvin.agregadorinvestimentos.dto.AccountStockDTO;
import tech.melvin.agregadorinvestimentos.dto.AccountStockResponseDTO;
import tech.melvin.agregadorinvestimentos.entity.AccountStock;
import tech.melvin.agregadorinvestimentos.entity.AccountStockId;
import tech.melvin.agregadorinvestimentos.repository.AccountRepository;
import tech.melvin.agregadorinvestimentos.repository.AccountStockRepository;
import tech.melvin.agregadorinvestimentos.repository.StockRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

   private final AccountRepository accountRepository;
   private final AccountStockRepository accountStockRepository;
   private final StockRepository stockRepository;

   public AccountService(AccountRepository accountRepository, AccountStockRepository accountStockRepository, StockRepository stockRepository) {
      this.accountRepository = accountRepository;
      this.accountStockRepository = accountStockRepository;
      this.stockRepository = stockRepository;
   }

   public AccountStock associateStock(String accountId, AccountStockDTO accountStockDTO) {

      var account = accountRepository.findById(UUID.fromString(accountId))
           .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found."));

      var stock = stockRepository.findById(accountStockDTO.stockId())
           .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found."));

      var id = new AccountStockId(account.getAccountId(), stock.getStockId());

      var accountStock = new AccountStock(id, account, stock, accountStockDTO.quantity());

      return accountStockRepository.save(accountStock);
   }

   public List<AccountStockResponseDTO> listStocks(String accountId) {

      var account = accountRepository.findById(UUID.fromString(accountId))
           .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found."));

      return account.getAccountStocks()
           .stream()
           .map(ac -> new AccountStockResponseDTO(ac.getStock().getStockId(), ac.getQuantity(), 0.0))
           .toList();
   }
}
