package net.sternstein.saft.service;

import net.sternstein.saft.domain.User;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(String passcode, String couleurName);
    User getUser(Long id);
    List<User> getAllUsers();
    User updateUser(User user);
    boolean deleteUser(Long id);
}
