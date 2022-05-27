package br.com.aegro.datasync;

import br.com.aegro.datasync.user.application.StandardUserApplicationService;
import br.com.aegro.datasync.user.application.UserApplicationService;
import br.com.aegro.datasync.user.application.converter.StandardUserConverter;
import br.com.aegro.datasync.user.application.converter.UserConverter;
import br.com.aegro.datasync.user.application.model.UserNotFoundException;
import br.com.aegro.datasync.user.domain.UserRegistryDomainService;
import br.com.aegro.datasync.user.domain.UserRepository;
import br.com.aegro.datasync.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class UserApplicationServiceTest {

    private final UserConverter userConverter = new StandardUserConverter();

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final UserRegistryDomainService userRegistryDomainService = mock(UserRegistryDomainService.class);

    private final UserApplicationService userApplicationService = new StandardUserApplicationService(
            userConverter,
            userRepository,
            userRegistryDomainService);

    @Test
    public void authenticating_an_user_successfully() {
        var user = new User(
                999L,
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
                null,
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

}
