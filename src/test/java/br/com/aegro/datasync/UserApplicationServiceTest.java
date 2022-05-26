package br.com.aegro.datasync;

import br.com.aegro.datasync.user.application.StandardUserApplicationService;
import br.com.aegro.datasync.user.application.UserApplicationService;
import br.com.aegro.datasync.user.application.converter.StandardUserConverter;
import br.com.aegro.datasync.user.application.converter.UserConverter;
import br.com.aegro.datasync.user.application.model.UserAlreadyRegisteredException;
import br.com.aegro.datasync.user.application.model.UserNotFoundException;
import br.com.aegro.datasync.user.domain.User;
import br.com.aegro.datasync.user.domain.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.never;

public class UserApplicationServiceTest {

    private final UserConverter userConverter = new StandardUserConverter();

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final UserApplicationService userApplicationService = new StandardUserApplicationService(
            userConverter,
            userRepository
    );

    @Test
    public void authenticating_an_user_successfully() {
        var user = new User(
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        var authenticatedUser = userApplicationService.authenticate(user.getEmail());

        Assertions.assertNotNull(authenticatedUser);
    }

    @Test
    public void authenticating_an_user_that_does_not_exist() {
        var user = new User(
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        var exception = Assertions.assertThrows(
                UserNotFoundException.class,
                () -> userApplicationService.authenticate(user.getEmail())
        );

        Assertions.assertEquals(
                "Unable to find an user for email " + user.getEmail(),
                exception.getMessage()
        );
    }

    @Test
    public void creating_an_user_successfully() {
        var user = new User(
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user)).thenReturn(user);

        userApplicationService.save(userConverter.convertFrom(user));

        Mockito.verify(userRepository).findByEmail(user.getEmail());
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void creating_an_user_that_already_exists() {
        var user = new User(
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        var exception = Assertions.assertThrows(
                UserAlreadyRegisteredException.class,
                () -> userApplicationService.save(userConverter.convertFrom(user))
        );

        Assertions.assertEquals(
                "There's an user registered for the email " + user.getEmail(),
                exception.getMessage()
        );

        Mockito.verify(userRepository, never()).save(user);
    }

}
