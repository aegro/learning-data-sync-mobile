package br.com.aegro.datasync;

import br.com.aegro.datasync.commons.validation.ValidationException;
import br.com.aegro.datasync.commons.validation.Validator;
import br.com.aegro.datasync.user.domain.StandardUserRegistryValidator;
import br.com.aegro.datasync.user.domain.UserRepository;
import br.com.aegro.datasync.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

public class StandardUserRegistryValidatorTests {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final Validator<User> userRegistryValidator = new StandardUserRegistryValidator(userRepository);

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

        userRegistryValidator.validate(user);

        Mockito.verify(userRepository).existDuplicatedByEmail(user.getId(), user.getEmail());
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
                ValidationException.class,
                () -> userRegistryValidator.validate(user)
        );

        Assertions.assertEquals(
                "There's an user registered for the email " + user.getEmail(),
                exception.getMessage()
        );
    }

}
