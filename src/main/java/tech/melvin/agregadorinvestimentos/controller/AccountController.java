package tech.melvin.agregadorinvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.melvin.agregadorinvestimentos.dto.AccountStockDTO;
import tech.melvin.agregadorinvestimentos.dto.AccountStockResponseDTO;
import tech.melvin.agregadorinvestimentos.service.AccountService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

   private final AccountService accountService;

   public AccountController(AccountService accountService) {
      this.accountService = accountService;
   }

   @PostMapping("/{accountId}/stocks")
   public ResponseEntity<Void> associateStocks(@PathVariable("accountId") String accountId, @RequestBody AccountStockDTO accountStockDTO) {
      var account = accountService.associateStock(accountId, accountStockDTO);

      return ResponseEntity.created(URI.create("/accounts/" + account.getAccountStockId())).build();
   }

   @GetMapping("/{accountId}/stocks")
   public ResponseEntity<List<AccountStockResponseDTO>> getAccountStocks(@PathVariable("accountId") String accountId) {
      var stocks = accountService.listStocks(accountId);

      return ResponseEntity.ok().body(stocks);
   }
}
