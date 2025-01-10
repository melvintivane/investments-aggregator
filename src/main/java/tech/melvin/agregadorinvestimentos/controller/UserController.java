package tech.melvin.agregadorinvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.melvin.agregadorinvestimentos.dto.*;
import tech.melvin.agregadorinvestimentos.entity.User;
import tech.melvin.agregadorinvestimentos.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        var user = userService.createUser(userCreateDTO);

        return ResponseEntity.created(URI.create("/users/" + user.getUserId().toString())).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<UserResponseDTO>> getUser(@PathVariable("userId") String userId) {
        Optional<UserResponseDTO> user = userService.getUserById(userId);

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.updateUser(userId, userUpdateDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteById(userId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable("userId") String userId, @RequestBody CreateAccountDTO accountDTO) {
        userService.createAccount(userId, accountDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> getUsersAccounts(@PathVariable("userId") String userId) {
        var accounts = userService.findAccounts(userId)
             .stream()
             .map(account -> new AccountResponseDTO(account.getAccountId().toString(), account.getDescription()))
             .toList();

        return ResponseEntity.ok().body(accounts);
    }

}
