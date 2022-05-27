package br.com.aegro.datasync.seed.domain;

import br.com.aegro.datasync.commons.validation.ValidationException;
import br.com.aegro.datasync.commons.validation.Validator;
import br.com.aegro.datasync.seed.domain.model.Seed;
import br.com.aegro.datasync.user.domain.UserRepository;
import br.com.aegro.datasync.user.domain.model.UserNotFoundException;

import java.time.format.DateTimeFormatter;

public class StandardSeedRegistryValidator implements Validator<Seed> {

    private final UserRepository userRepository;

    public StandardSeedRegistryValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(Seed candidate) {
        ensureIfExpiresInIsNotLessThanOrEqualToManufacturedAt(candidate);
        ensureUserIsPresent(candidate);
    }

    private void ensureIfExpiresInIsNotLessThanOrEqualToManufacturedAt(Seed candidate) {
        if (candidate.getExpiresIn().isBefore(candidate.getManufacturedAt()) ||
                candidate.getExpiresIn().isEqual(candidate.getManufacturedAt())) {
            throw new ValidationException(
                    String.format(
                            "expiresIn (%s) cannot be less than or equals to the manufacturedAt (%s)",
                            candidate.getExpiresIn().format(DateTimeFormatter.ISO_DATE),
                            candidate.getManufacturedAt().format(DateTimeFormatter.ISO_DATE)
                    )
            );
        }
    }

    private void ensureUserIsPresent(Seed candidate) {
        try {
            candidate.setCreatedBy(userRepository.fetchById(candidate.getCreatedBy().getExternalId()));
        } catch (UserNotFoundException userNotFoundException) {
            throw new ValidationException(userNotFoundException.getMessage());
        }
    }
}
