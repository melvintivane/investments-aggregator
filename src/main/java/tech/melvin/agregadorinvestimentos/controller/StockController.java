package tech.melvin.agregadorinvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.melvin.agregadorinvestimentos.dto.CreateStockDTO;
import tech.melvin.agregadorinvestimentos.service.StockService;

import java.net.URI;

@RestController
@RequestMapping(("/stocks"))
public class StockController {

   private final StockService stockService;

   public StockController(StockService stockService) {
      this.stockService = stockService;
   }

   @PostMapping
   public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO stockDTO) {
      var stock = stockService.createStock(stockDTO);

      return ResponseEntity.created(URI.create("/stock/" + stock.getStockId())).build();
   }
}
