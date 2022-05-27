package br.com.aegro.datasync.seed.application;

import br.com.aegro.datasync.seed.application.converter.SeedConverter;
import br.com.aegro.datasync.seed.application.model.SeedModel;
import br.com.aegro.datasync.seed.domain.SeedRegistryDomainService;

public class StandardSeedApplicationService implements SeedApplicationService {

    private final SeedConverter seedConverter;

    private final SeedRegistryDomainService seedRegistryDomainService;

    public StandardSeedApplicationService(
            SeedConverter seedConverter,
            SeedRegistryDomainService seedRegistryDomainService
    ) {
        this.seedConverter = seedConverter;
        this.seedRegistryDomainService = seedRegistryDomainService;
    }

    @Override
    public SeedModel save(SeedModel seedModel) {
        return seedConverter.convertFrom(
                seedRegistryDomainService.register(seedConverter.convertTo(seedModel))
        );
    }
}
