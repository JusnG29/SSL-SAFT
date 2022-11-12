package net.sternstein.saft.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private User user;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Purchase> purchaseList;
    private LocalDate purchaseDate;

    public Transaction() {
    }

    public Transaction(User user, Product product, BigDecimal value, int amount) {
        this.id = UUID.randomUUID();
        this.user = user;
        purchaseList = new ArrayList<>();
        this.purchaseDate = LocalDate.now();
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

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (!id.equals(that.id)) return false;
        if (!user.equals(that.user)) return false;
        if (!purchaseList.equals(that.purchaseList)) return false;
        return purchaseDate.equals(that.purchaseDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + purchaseList.hashCode();
        result = 31 * result + purchaseDate.hashCode();
        return result;
    }
}
