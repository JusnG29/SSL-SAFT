package net.sternstein.saft.service;

import io.quarkus.hibernate.orm.panache.Panache;
import net.sternstein.saft.domain.Product;
import net.sternstein.saft.domain.Transaction;
import net.sternstein.saft.domain.User;
import net.sternstein.saft.model.dto.transaction.PurchaseDTO;
import net.sternstein.saft.persistence.ProductRepository;
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
        return userRepository.listAll();
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

    @Inject
    ProductRepository productRepository;
    @Inject
    TransactionService transactionService;
    @Inject
    PurchaseService purchaseService;

    @Override
    // TODO: remove! only for local dev
    public void init() {
        User odin = new User("Odin", "1234");
        User sky = new User("Skywalker", "0815");
        Product bier = new Product("Bier", BigDecimal.ONE);
        Product eiskaffe = new Product("Eiskaffe", BigDecimal.valueOf(1.2));

        userRepository.persist(odin);
        userRepository.persist(sky);
        productRepository.persist(bier);
        productRepository.persist(eiskaffe);

        List<PurchaseDTO> purchaseOdin = List.of(new PurchaseDTO(eiskaffe.getId(), 5));
        List<PurchaseDTO> purchaseSky = List.of(
                new PurchaseDTO(bier.getId(), 10),
                new PurchaseDTO(bier.getId(), 2)
        );

        transactionService.executeTransaction(odin.getId(), purchaseOdin);
        transactionService.executeTransaction(sky.getId(), purchaseSky);
    }
}