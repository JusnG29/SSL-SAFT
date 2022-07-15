package net.sternstein.saft.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private User user;
    @ManyToOne
    @JoinColumn(name="productId", nullable=false)
    private Product product;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate purchaseDate;
    private BigDecimal value;
    private int amount;

    public Transaction() {
    }

    public Transaction(UUID id, User user, Product product, BigDecimal value, int amount) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.product = product;
        this.purchaseDate = LocalDate.now();
        this.value = value;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount && Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(product, that.product) && Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product, purchaseDate, value, amount);
    }
}
