package br.com.aegro.datasync;

import br.com.aegro.datasync.commons.validation.ValidationException;
import br.com.aegro.datasync.commons.validation.Validator;
import br.com.aegro.datasync.seed.domain.StandardSeedRegistryValidator;
import br.com.aegro.datasync.seed.domain.model.Seed;
import br.com.aegro.datasync.user.domain.UserRepository;
import br.com.aegro.datasync.user.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

public class StandardSeedRegistryValidatorTests {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final Validator<Seed> seedRegistryValidator = new StandardSeedRegistryValidator(userRepository);

    @Test
    public void creating_a_seed_successfully() {
        var user = new User(
                999L,
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        var seed = new Seed(
                null,
                UUID.randomUUID().toString(),
                "Fake Seed",
                "Fake Manufacturer",
                LocalDate.of(2022, Month.JANUARY, 1),
                LocalDate.of(2023, Month.JANUARY, 1),
                LocalDateTime.of(2022, Month.MAY, 24, 23, 59),
                user
        );

        Mockito.when(userRepository.fetchById(user.getExternalId())).thenReturn(user);

        seedRegistryValidator.validate(seed);

        Assertions.assertNotNull(seed);
        Assertions.assertNotNull(seed.getExternalId());
        Assertions.assertEquals("Fake Seed", seed.getName());
        Assertions.assertEquals("Fake Manufacturer", seed.getManufacturer());
        Assertions.assertEquals(LocalDate.of(2022, Month.JANUARY, 1), seed.getManufacturedAt());
        Assertions.assertEquals(LocalDate.of(2023, Month.JANUARY, 1), seed.getExpiresIn());
        Assertions.assertEquals(
                LocalDateTime.of(2022, Month.MAY, 24, 23, 59),
                seed.getCreatedAt()
        );
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user, seed.getCreatedBy());
    }

    @Test
    public void creating_a_seed_with_invalid_dates() {
        var user = new User(
                999L,
                UUID.randomUUID().toString(),
                "Fake User",
                "fake.user@fakecompany.com"
        );

        var exception = Assertions.assertThrows(
                ValidationException.class,
                () -> seedRegistryValidator.validate(
                        new Seed(
                                null,
                                UUID.randomUUID().toString(),
                                "Fake Seed",
                                "Fake Manufacturer",
                                LocalDate.of(2022, Month.JANUARY, 1),
                                LocalDate.of(2021, Month.JANUARY, 1),
                                LocalDateTime.of(2022, Month.MAY, 24, 23, 59),
                                user
                        )
                ));

        Assertions.assertEquals(
                "expiresIn (2021-01-01) cannot be less than or equals to the manufacturedAt (2022-01-01)",
                exception.getMessage()
        );
    }
}
