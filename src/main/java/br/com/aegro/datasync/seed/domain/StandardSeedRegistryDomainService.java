package br.com.aegro.datasync.seed.domain;

import br.com.aegro.datasync.commons.validation.Validator;
import br.com.aegro.datasync.seed.domain.model.Seed;

public class StandardSeedRegistryDomainService implements SeedRegistryDomainService {

    private final Validator<Seed> seedRegistryValidator;

    private final SeedRepository seedRepository;

    public StandardSeedRegistryDomainService(
            Validator<Seed> seedRegistryValidator,
            SeedRepository seedRepository
    ) {
        this.seedRegistryValidator = seedRegistryValidator;
        this.seedRepository = seedRepository;
    }

    @Override
    public Seed register(Seed seed) {
        seedRegistryValidator.validate(seed);
        return seedRepository.save(seed);
    }
}
