package tech.melvin.agregadorinvestimentos.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import tech.melvin.agregadorinvestimentos.dto.UserCreateDTO;
import tech.melvin.agregadorinvestimentos.dto.UserResponseDTO;
import tech.melvin.agregadorinvestimentos.dto.UserUpdateDTO;
import tech.melvin.agregadorinvestimentos.entity.User;
import tech.melvin.agregadorinvestimentos.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Nested
    class createUser {

        @Test
        @DisplayName("Should create a user with success.")
        void shouldCreateAUserWithSuccess() {

            // Arrange
            var user = new User(
                 UUID.randomUUID(),
                 "username",
                 "email@email.com",
                 "password",
                 Instant.now(),
                 Instant.now()
            );

            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());

            var input = new UserCreateDTO(
                 "username",
                 "email@email.com",
                 "123"
            );

            // Act
            var output = userService.createUser(input);

            // Assert
            var userCaptured = userArgumentCaptor.getValue();

            assertNotNull(output);
            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
        }

        @Test
        @DisplayName("Should throw an exception when email is not valid.")
        void shouldThrowExceptionWhenEmailIsNotValid() {

            // Arrange
            var createUserDTO = new UserCreateDTO(
                 "username",
                 "invalid-email",
                 "password"
            );


            // Act
            var exception = assertThrows(ResponseStatusException.class,
                 () -> userService.createUser(createUserDTO)
            );


            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
            assertEquals("Email is not valid!", exception.getReason());
        }
    }

    @Nested
    class updateUser {

        @Test
        @DisplayName("Should update user by ID when user exists and username and password is filled.")
        void shouldUpdateUserByIdWhenUserExistsAndUsernameAndPasswordIsFilled() {

            // Arrange
            var userUpdateDTO = new UserUpdateDTO(
                 "Melvin Tivane",
                 "123"
            );

            var user = new User(
                 UUID.randomUUID(),
                 "Melvin Messias",
                 "email@email.com",
                 "787711",
                 Instant.now(),
                 Instant.now()
            );

            doReturn(Optional.of(user))
                 .when(userRepository)
                 .findById(uuidArgumentCaptor.capture());

            doReturn(user)
                 .when(userRepository)
                 .save(userArgumentCaptor.capture());


            // Act
            userService.updateUser(user.getUserId().toString(), userUpdateDTO);
            var capturedUser = userArgumentCaptor.getValue();


            // Assert
            assertEquals(userUpdateDTO.username(), capturedUser.getUsername());
            assertEquals(userUpdateDTO.password(), capturedUser.getPassword());

            verify(userRepository, times(1)).findById(uuidArgumentCaptor.capture());
            verify(userRepository, times(1)).save(userArgumentCaptor.capture());
        }

        @Test
        @DisplayName("Should throw an exception when user does not Exists.")
        void shouldThrowExceptionWhenUserDoesNotExists() {

            // Arrange
            var userUpdateDTO = new UserUpdateDTO(
                 "newUsername",
                 "newPassword"
            );

            var userId = UUID.randomUUID();

            doReturn(Optional.empty())
                 .when(userRepository)
                 .findById(uuidArgumentCaptor.capture());


            // Act & Assert
            var exception = assertThrows(ResponseStatusException.class, () ->
                userService.updateUser(userId.toString(), userUpdateDTO)
            );

            assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            assertEquals("User not found.", exception.getReason());
            assertEquals(userId, uuidArgumentCaptor.getValue());
        }
    }

    @Nested
    class getAllUsers {

        @Test
        @DisplayName("Should return all users.")
        void shouldReturnAllUsersList() {

            // Arrange
            var user = new User(
                 UUID.randomUUID(),
                 "username",
                 "email@email.com",
                 "password",
                 Instant.now(),
                 Instant.now()
            );

            var userList = List.of(user);

            doReturn(userList).when(userRepository).findAll();


            // Act
            var output = userService.getAllUsers();


            // Assert
            assertNotNull(output);
            assertEquals(userList.size(), output.size());
        }
    }

    @Nested
    class getUserById {

        @Test
        @DisplayName("Should get user by ID with success when optional is present.")
        void shouldGetUserByIdWithSuccessWhenOptionalIsPresent() {

            // Arrange
            var userId = UUID.randomUUID();
            var userEntity = new User(
                 userId,
                 "John Doe",
                 "johndoe@email.com",
                 "password",
                 Instant.now(),
                 Instant.now()
            );
            var expectedUser = new UserResponseDTO(
                 userId,
                 "John Doe",
                 "johndoe@email.com",
                 userEntity.getCreationTimestamp(),
                 userEntity.getUpdateTimestamp()
            );

            doReturn(Optional.of(userEntity))
                 .when(userRepository)
                 .findById(uuidArgumentCaptor.capture());

            // Act
            var output = userService.getUserById(userId.toString());

            // Assert
            assertTrue(output.isPresent());
            assertEquals(expectedUser, output.get());
            assertEquals(userId, uuidArgumentCaptor.getValue());
        }


        @Test
        @DisplayName("Should throw an exception when user is not found.")
        void shouldThrowExceptionWhenUserIsNotFound() {

            // Arrange
            var userId = UUID.randomUUID();

            doReturn(Optional.empty())
                 .when(userRepository)
                 .findById(uuidArgumentCaptor.capture());


            // Act & Assert
            var exception = assertThrows(ResponseStatusException.class, () ->
                userService.getUserById(userId.toString())
            );

            assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            assertEquals("User not found.", exception.getReason());
            assertEquals(userId, uuidArgumentCaptor.getValue());
        }
    }

    @Nested
    class deleteUserById {

        @Test
        @DisplayName("Should delete a user with success when user exists.")
        void shouldDeleteAUserWithSuccessWhenExists() {

            // Arrange
            var userId = UUID.randomUUID();

            doReturn(true)
                 .when(userRepository)
                 .existsById(uuidArgumentCaptor.capture());


            // Act
            userService.deleteById(userId.toString());


            // Assert
            assertEquals(userId, uuidArgumentCaptor.getValue());

            verify(userRepository, times(1)).existsById(uuidArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("Should throw an exception when user does not exists.")
        void shouldThrowExceptionWhenDoesNotExists() {

            // Arrange
            var userId = UUID.randomUUID();

            doReturn(false)
                 .when(userRepository)
                 .existsById(uuidArgumentCaptor.capture());


            // Act
            var exception = assertThrows(ResponseStatusException.class, () ->
                userService.deleteById(userId.toString())
            );


            // Assert
            assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
            assertEquals("User not found.", exception.getReason());
            assertEquals(userId, uuidArgumentCaptor.getValue());
        }
    }
}