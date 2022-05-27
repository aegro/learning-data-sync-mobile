package br.com.aegro.datasync;

import br.com.aegro.datasync.user.domain.model.UserAlreadyRegisteredException;
import br.com.aegro.datasync.user.domain.StandardUserRegistryDomainService;
import br.com.aegro.datasync.user.domain.UserRegistryDomainService;
import br.com.aegro.datasync.user.domain.UserRepository;
import br.com.aegro.datasync.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.never;

public class UserRegistryDomainServiceTests {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final UserRegistryDomainService userRegistryDomainService =
            new StandardUserRegistryDomainService(userRepository);

    @Test
    public void creating_an_user_successfully() {
        var user = new User(
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        Mockito.when(userRepository.existDuplicatedByEmail(
                user.getId(),
                user.getEmail())
        ).thenReturn(Optional.empty());

        Mockito.when(userRepository.save(user)).thenReturn(user);

        userRegistryDomainService.register(user);

        Mockito.verify(userRepository).existDuplicatedByEmail(user.getId(), user.getEmail());
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void creating_an_user_that_already_exists() {
        var user = new User(
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        Mockito.when(userRepository.existDuplicatedByEmail(
                user.getId(),
                user.getEmail())
        ).thenReturn(Optional.of(user));

        var exception = Assertions.assertThrows(
                UserAlreadyRegisteredException.class,
                () -> userRegistryDomainService.register(user)
        );

        Assertions.assertEquals(
                "There's an user registered for the email " + user.getEmail(),
                exception.getMessage()
        );

        Mockito.verify(userRepository, never()).save(user);
    }

}
