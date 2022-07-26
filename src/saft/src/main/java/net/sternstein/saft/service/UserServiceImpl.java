package net.sternstein.saft.service;

import net.sternstein.saft.domain.User;
import net.sternstein.saft.persistence.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {
    @Inject
    UserRepository userRepository;

    @Override
    public User createUser(String couleurName, String passcode) {
        var user = new User(passcode, couleurName);

        userRepository.persist(user);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().toList();
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }
}
