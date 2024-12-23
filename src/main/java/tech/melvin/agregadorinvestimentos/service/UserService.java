package tech.melvin.agregadorinvestimentos.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.melvin.agregadorinvestimentos.dto.CreateUserDTO;
import tech.melvin.agregadorinvestimentos.dto.UserResponseDTO;
import tech.melvin.agregadorinvestimentos.dto.UserUpdateDTO;
import tech.melvin.agregadorinvestimentos.entity.User;
import tech.melvin.agregadorinvestimentos.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        var userExists = userRepository.findByEmail(createUserDTO.email());

        if (!isValidEmail(createUserDTO.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is not valid!");
        }

        if (userExists != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use!");
        }

        user.setUsername(createUserDTO.username());
        user.setPassword(createUserDTO.password());
        user.setEmail(createUserDTO.email());

        return userRepository.save(user);
    }

    public Optional<User> getUserById(String userId) {
        Optional<User> user = userRepository.findById(UUID.fromString(userId));

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

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
}
