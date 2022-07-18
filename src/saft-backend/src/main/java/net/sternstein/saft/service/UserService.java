package net.sternstein.saft.service;

import net.sternstein.saft.domain.User;
import java.util.List;

public interface UserService {
    User createUser(String passcode, String couleurName);
    List<User> getAllUsers();
    void updateUser();
    void deleteUser();
}
