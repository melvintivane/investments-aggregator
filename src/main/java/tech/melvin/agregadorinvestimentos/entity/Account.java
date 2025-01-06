package tech.melvin.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
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

   public Account(UUID accountId, String description, List<AccountStock> accountStocks) {
      this.accountId = accountId;
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

   public void setDescription(String description) {
      this.description = description;
   }
}
