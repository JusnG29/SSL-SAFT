package net.sternstein.saft.service;

import net.sternstein.saft.domain.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(String passcode, String couleurName);
    User getUser(UUID id);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(UUID id);
    boolean login(UUID id, String passcode);
    BigDecimal getBalance(UUID id);
}
