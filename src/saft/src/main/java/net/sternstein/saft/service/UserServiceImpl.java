package net.sternstein.saft.service;

import net.sternstein.saft.domain.User;
import net.sternstein.saft.persistence.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
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
    // TODO: likely conflict when using non UUID datatype - panache accepts only Long here
    //  Note: Use PanacheEntityBase instead with @GeneratedValue annotation or similar (maybe ?!?!)
    //  Check and/or change on all Methods with id as input (marked by "GAJ ID!" comment)
    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().toList();
    }

    @Override
    public User updateUser(User user) {
        userRepository.persist(user);
        return user;
    }

    @Override
    // TODO: GAJ ID!
    public boolean deleteUser(Long id) {
        return userRepository.deleteById(id);
    }
}
