package net.sternstein.saft.service;

import io.quarkus.hibernate.orm.panache.Panache;
import net.sternstein.saft.domain.User;
import net.sternstein.saft.persistence.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public User getUser(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().toList();
    }

    @Override
    public User updateUser(User user) {
        return Panache.getEntityManager().merge(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean login(UUID id, String passcode) {
        User user = userRepository.findById(id);
        return user.getPasscode().equals(passcode);
    }

    @Override
    public BigDecimal getBalance(UUID id) {
        User user = Optional.of(userRepository.findById(id)).orElseThrow(() -> new RuntimeException("User not found with ID: " + id.toString()));
        return user.getBalance();
    }
}