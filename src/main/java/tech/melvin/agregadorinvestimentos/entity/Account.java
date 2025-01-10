package tech.melvin.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
public class Account {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   @Column(name = "account_id")
   private UUID accountId;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
   @PrimaryKeyJoinColumn
   private BillingAddress billingAddress;

   @Column(name = "description")
   private String description;

   @OneToMany(mappedBy = "account")
   private List<AccountStock> accountStocks = new ArrayList<>();

   public Account() {
   }

   public Account(UUID accountId, User user, BillingAddress billingAddress, String description, List<AccountStock> accountStocks) {
      this.accountId = accountId;
      this.user = user;
      this.billingAddress = billingAddress;
      this.description = description;
      this.accountStocks = accountStocks;
   }

   public Account(User user, BillingAddress billingAddress, String description, List<AccountStock> accountStocks) {
      this.user = user;
      this.billingAddress = billingAddress;
      this.description = description;
      this.accountStocks = accountStocks;
   }

   public UUID getAccountId() {
      return accountId;
   }

   public void setAccountId(UUID accountId) {
      this.accountId = accountId;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public String getDescription() {
      return description;
   }

   public BillingAddress getBillingAddress() {
      return billingAddress;
   }

   public void setBillingAddress(BillingAddress billingAddress) {
      this.billingAddress = billingAddress;
   }

   public List<AccountStock> getAccountStocks() {
      return accountStocks;
   }

   public void setAccountStocks(List<AccountStock> accountStocks) {
      this.accountStocks = accountStocks;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Account account = (Account) o;
      return Objects.equals(accountId, account.accountId) && Objects.equals(user, account.user) && Objects.equals(billingAddress, account.billingAddress) && Objects.equals(description, account.description) && Objects.equals(accountStocks, account.accountStocks);
   }

   @Override
   public int hashCode() {
      return Objects.hash(accountId, user, billingAddress, description, accountStocks);
   }
}
