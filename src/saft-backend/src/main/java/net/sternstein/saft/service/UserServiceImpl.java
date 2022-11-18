package net.sternstein.saft.service;

import io.quarkus.hibernate.orm.panache.Panache;
import net.sternstein.saft.domain.Product;
import net.sternstein.saft.domain.User;
import net.sternstein.saft.model.dto.transaction.PurchaseDTO;
import net.sternstein.saft.persistence.ProductRepository;
import net.sternstein.saft.persistence.UserRepository;
import net.sternstein.saft.services.TransactionService;

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
        Product seiterl = new Product("Bier", BigDecimal.ONE, "#fcba03", "0,33 l");
        Product halbe = new Product("Bier", BigDecimal.valueOf(1.3), "#fcba03", "0,5 l");
        Product wein = new Product("Wein", BigDecimal.valueOf(1.2), "#fcba03", "0,75 l");
        Product anti = new Product("Anti", BigDecimal.valueOf(1.2), "#4bc22b", "0,5 l");
        Product eiskaffe = new Product("Eiskaffee", BigDecimal.valueOf(1.2), "#4bc22b", "0,25 l");
        Product pizza = new Product("Pizza", BigDecimal.valueOf(2), "#2ba6c2", "1 Stk.");
        Product knabber = new Product("Knabberei", BigDecimal.valueOf(2), "#2ba6c2", "1 Stk.");
        Product schoko = new Product("Schoko", BigDecimal.valueOf(0.7), "#2ba6c2", "1 Stk.");
        Product something = new Product("Product X", BigDecimal.valueOf(0.1), "#000000", "1 Stk.");


        userRepository.persist(odin);
        userRepository.persist(sky);
        productRepository.persist(seiterl);
        productRepository.persist(halbe);
        productRepository.persist(wein);
        productRepository.persist(anti);
        productRepository.persist(eiskaffe);
        productRepository.persist(pizza);
        productRepository.persist(knabber);
        productRepository.persist(schoko);
        productRepository.persist(something);

        List<PurchaseDTO> purchaseOdin = List.of(new PurchaseDTO(eiskaffe.getId(), 5));
        List<PurchaseDTO> purchaseSky = List.of(
                new PurchaseDTO(halbe.getId(), 10),
                new PurchaseDTO(halbe.getId(), 2)
        );

        transactionService.executeTransaction(odin.getId(), purchaseOdin);
        transactionService.executeTransaction(sky.getId(), purchaseSky);
    }
}