package tech.melvin.agregadorinvestimentos.service;

import org.springframework.stereotype.Service;
import tech.melvin.agregadorinvestimentos.dto.CreateStockDTO;
import tech.melvin.agregadorinvestimentos.entity.Stock;
import tech.melvin.agregadorinvestimentos.repository.StockRepository;

@Service
public class StockService {
   private final StockRepository stockRepository;

   public StockService(StockRepository stockRepository) {
      this.stockRepository = stockRepository;
   }

   public Stock createStock(CreateStockDTO stockDTO) {
      var stock = new Stock(
           stockDTO.stockId(),
           stockDTO.description()
      );

      return stockRepository.save(stock);
   }
}
