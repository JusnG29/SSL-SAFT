package net.sternstein.saft.domain;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Purchase {

    @Id
    private UUID id;
    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "transactionId", nullable = false)
    private Transaction transaction;
    @ManyToOne
    @JoinColumn(name="productId", nullable=false)
    private Product product;
    private BigDecimal value;
    private int amount;

    public Purchase() {
    }

    public Purchase(Transaction transaction, Product product, BigDecimal value, int amount) {
        this.id = UUID.randomUUID();
        this.transaction = transaction;
        this.product = product;
        this.value = value;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

        Purchase purchase = (Purchase) o;

        if (amount != purchase.amount) return false;
        if (!id.equals(purchase.id)) return false;
        if (!transaction.equals(purchase.transaction)) return false;
        if (!product.equals(purchase.product)) return false;
        return value.equals(purchase.value);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + transaction.hashCode();
        result = 31 * result + product.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + amount;
        return result;
    }
}
