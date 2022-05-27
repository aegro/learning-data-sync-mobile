package br.com.aegro.datasync.seed.domain;

import br.com.aegro.datasync.commons.validation.ValidationException;
import br.com.aegro.datasync.commons.validation.Validator;
import br.com.aegro.datasync.seed.domain.model.Seed;

import java.time.format.DateTimeFormatter;

public class StandardSeedRegistryValidator implements Validator<Seed> {
    @Override
    public void validate(Seed candidate) {
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
}
