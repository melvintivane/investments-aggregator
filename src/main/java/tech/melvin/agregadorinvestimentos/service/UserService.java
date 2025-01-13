package tech.melvin.agregadorinvestimentos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.melvin.agregadorinvestimentos.dto.CreateAccountDTO;
import tech.melvin.agregadorinvestimentos.dto.UserCreateDTO;
import tech.melvin.agregadorinvestimentos.dto.UserResponseDTO;
import tech.melvin.agregadorinvestimentos.dto.UserUpdateDTO;
import tech.melvin.agregadorinvestimentos.entity.Account;
import tech.melvin.agregadorinvestimentos.entity.BillingAddress;
import tech.melvin.agregadorinvestimentos.entity.User;
import tech.melvin.agregadorinvestimentos.repository.AccountRepository;
import tech.melvin.agregadorinvestimentos.repository.BillingAddressRepository;
import tech.melvin.agregadorinvestimentos.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final AccountRepository accountRepository;
    private final BillingAddressRepository billingAddressRepository;
    private final UserRepository userRepository;

    public UserService(AccountRepository accountRepository, BillingAddressRepository billingAddressRepository, UserRepository userRepository) {
       this.accountRepository = accountRepository;
       this.billingAddressRepository = billingAddressRepository;
       this.userRepository = userRepository;
    }

    public User createUser(UserCreateDTO userCreateDTO) {

        User user = new User();
        var userExists = userRepository.findByEmail(userCreateDTO.email());

        if (!isValidEmail(userCreateDTO.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is not valid!");
        }

        if (userExists != null) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use!");
        }

        user.setUsername(userCreateDTO.username());
        user.setPassword(userCreateDTO.password());
        user.setEmail(userCreateDTO.email());

        return userRepository.save(user);
    }

    public Optional<UserResponseDTO> getUserById(String userId) {

        Optional<UserResponseDTO> user = userRepository
             .findById(UUID.fromString(userId))
             .map(res -> new UserResponseDTO(
                  res.getUserId(),
                  res.getUsername(),
                  res.getEmail(),
                  res.getCreationTimestamp(),
                  res.getUpdateTimestamp())
             );

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        return user;
    }

    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
             .stream()
             .map(user -> new UserResponseDTO(
                  user.getUserId(),
                  user.getUsername(),
                  user.getEmail(),
                  user.getCreationTimestamp(),
                  user.getUpdateTimestamp()
             ))
             .toList();
    }

    public void updateUser(String userId, UserUpdateDTO userUpdateDTO) {

        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (userUpdateDTO.username() != null) {
                user.setUsername(userUpdateDTO.username());
            }

            if (userUpdateDTO.password() != null) {
                user.setPassword(userUpdateDTO.password());
            }

            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
    }

    public void deleteById(String userId) {

        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
    }

    public void createAccount(String userId, CreateAccountDTO accountDTO) {

        var user = userRepository.findById(UUID.fromString(userId))
             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        if (user.getAccounts() == null) {
            user.setAccounts(new ArrayList<>());
        }

        // DTO -> ENTITY
        var account = new Account(
             user,
             null,
             accountDTO.description(),
             new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
             accountCreated.getAccountId(),
             accountCreated,
             accountDTO.street(),
             accountDTO.number()
        );

        billingAddressRepository.save(billingAddress);
    }

    public List<Account> findAccounts(String userId) {

        var user = userRepository.findById(UUID.fromString(userId))
             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));

        return user.getAccounts();
    }

    private boolean isValidEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
}
