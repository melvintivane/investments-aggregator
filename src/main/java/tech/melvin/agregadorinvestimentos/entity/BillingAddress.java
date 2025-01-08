package tech.melvin.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_billingaddress")
public class BillingAddress {

   @Id
   @Column(name = "account_id")
   private UUID accountId;

   @OneToOne(cascade = CascadeType.ALL)
   @MapsId
   @JoinColumn(name = "account_id")
   private Account account;

   @Column(name = "street")
   private String street;

   @Column(name = "number")
   private Integer number;

   @Version
   private Long version;

   public BillingAddress() {
   }

   public BillingAddress(UUID accountId, Account account, String street, Integer number) {
      this.accountId = accountId;
      this.account = account;
      this.street = street;
      this.number = number;
   }

   public UUID getId() {
      return accountId;
   }

   public void setId(UUID id) {
      this.accountId = id;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public Integer getNumber() {
      return number;
   }

   public void setNumber(Integer number) {
      this.number = number;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      BillingAddress that = (BillingAddress) o;
      return Objects.equals(accountId, that.accountId) && Objects.equals(account, that.account) && Objects.equals(street, that.street) && Objects.equals(number, that.number);
   }

   @Override
   public int hashCode() {
      return Objects.hash(accountId, account, street, number);
   }
}
