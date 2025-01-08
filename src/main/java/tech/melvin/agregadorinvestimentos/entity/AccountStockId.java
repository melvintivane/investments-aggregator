package tech.melvin.agregadorinvestimentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public class AccountStockId {

   @Column(name = "account_id")
   private UUID accountId;

   @Column(name = "stock_id")
   private String stockId;

   public AccountStockId() {
   }

   public AccountStockId(UUID accountId, String stockId) {
      this.accountId = accountId;
      this.stockId = stockId;
   }

   public UUID getAccountId() {
      return accountId;
   }

   public void setAccountId(UUID accountId) {
      this.accountId = accountId;
   }

   public String getStockId() {
      return stockId;
   }

   public void setStockId(String stockId) {
      this.stockId = stockId;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      AccountStockId that = (AccountStockId) o;
      return Objects.equals(accountId, that.accountId) && Objects.equals(stockId, that.stockId);
   }

   @Override
   public int hashCode() {
      return Objects.hash(accountId, stockId);
   }
}
