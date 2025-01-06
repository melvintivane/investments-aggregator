package tech.melvin.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_stocks")
public class Stock {

   @Id
   @Column(name = "stock_id")
   private UUID stockId;

   @Column(name = "description")
   private String description;

   @Column(name = "ticker")
   private String ticker;

   public Stock() {
   }

   public Stock(UUID stockId, String description, String ticker) {
      this.stockId = stockId;
      this.description = description;
      this.ticker = ticker;
   }

   public UUID getId() {
      return stockId;
   }

   public void setId(UUID stockId) {
      this.stockId = stockId;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getTicker() {
      return ticker;
   }

   public void setTicker(String ticker) {
      this.ticker = ticker;
   }
}
