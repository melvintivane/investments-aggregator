package tech.melvin.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_stocks")
public class Stock {

   @Id
   @Column(name = "stock_id")
   private String stockId;

   @Column(name = "description")
   private String description;

   public Stock() {
   }

   public Stock(String stockId, String description) {
      this.stockId = stockId;
      this.description = description;
   }

   public String getId() {
      return stockId;
   }

   public void setId(String stockId) {
      this.stockId = stockId;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Stock stock = (Stock) o;
      return Objects.equals(stockId, stock.stockId) && Objects.equals(description, stock.description);
   }

   @Override
   public int hashCode() {
      return Objects.hash(stockId, description);
   }
}
