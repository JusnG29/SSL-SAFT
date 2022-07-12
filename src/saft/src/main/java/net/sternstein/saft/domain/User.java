package net.sternstein.saft.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;
    private String passcode;
    private String couleurName;
    private int balance;

    public User() {}

    public User(String passcode, String couleurName) {
        id = UUID.randomUUID();
        balance = 0;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (balance != user.balance) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (!passcode.equals(user.passcode)) return false;
        return couleurName.equals(user.couleurName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + passcode.hashCode();
        result = 31 * result + couleurName.hashCode();
        result = 31 * result + balance;
        return result;
    }
}
