package tech.melvin.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_accountstocks")
public class AccountStock {

   @EmbeddedId
   private AccountStockId accountStockId;

   @ManyToOne
   @JoinColumn(name = "account_id")
   @MapsId("accountId")
   private Account account;

   @ManyToOne
   @JoinColumn(name = "stock_id")
   @MapsId("stockId")
   private Stock stock;

   @Column(name = "quantity")
   private int quantity;

   public AccountStock() {
   }

   public AccountStock(AccountStockId accountStockId, Account account, Stock stock, int quantity) {
      this.accountStockId = accountStockId;
      this.account = account;
      this.stock = stock;
      this.quantity = quantity;
   }

   public AccountStockId getAccountStockId() {
      return accountStockId;
   }

   public void setAccountStockId(AccountStockId accountStockId) {
      this.accountStockId = accountStockId;
   }

   public Account getAccount() {
      return account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }

   public Stock getStock() {
      return stock;
   }

   public void setStock(Stock stock) {
      this.stock = stock;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      AccountStock that = (AccountStock) o;
      return quantity == that.quantity && Objects.equals(accountStockId, that.accountStockId) && Objects.equals(account, that.account) && Objects.equals(stock, that.stock);
   }

   @Override
   public int hashCode() {
      return Objects.hash(accountStockId, account, stock, quantity);
   }
}
