package br.com.aegro.datasync;

import br.com.aegro.datasync.domain.entity.User;
import br.com.aegro.datasync.domain.exception.UserAlreadyRegisteredException;
import br.com.aegro.datasync.domain.repository.UserRepository;
import br.com.aegro.datasync.domain.service.SaveUserService;
import br.com.aegro.datasync.domain.service.StandardSaveUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.never;

public class SaveUserServiceTests {

    private final UserRepository repository = Mockito.mock(UserRepository.class);

    private final SaveUserService saveUserService = new StandardSaveUserService(repository);

    @Test
    public void creating_an_user_successfully() {
        var user = new User(
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        Mockito.when(repository.save(user)).thenReturn(user);

        var savedUser = saveUserService.execute(user);

        Assertions.assertNotNull(savedUser);
    }

    @Test
    public void creating_an_user_that_already_exists() {
        var user = new User(
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        Mockito.when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        var exception = Assertions.assertThrows(
                UserAlreadyRegisteredException.class,
                () -> saveUserService.execute(user)
        );

        Assertions.assertEquals(
                "There's an user registered for the email " + user.getEmail(),
                exception.getMessage()
        );

        Mockito.verify(repository, never()).save(user);
    }

}
