package net.sternstein.saft.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;
    @Column(length = 4)
    private String passcode;
    @Column(length = 50)
    private String couleurName;
    private BigDecimal balance;

    public User() {}

    public User(String passcode, String couleurName) {
        id = UUID.randomUUID();
        balance = BigDecimal.ZERO;
        this.passcode = passcode;
        this.couleurName = couleurName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getCouleurName() {
        return couleurName;
    }

    public void setCouleurName(String couleurName) {
        this.couleurName = couleurName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(passcode, user.passcode) && Objects.equals(couleurName, user.couleurName) && Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passcode, couleurName, balance);
    }
}
