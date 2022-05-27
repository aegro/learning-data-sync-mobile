package br.com.aegro.datasync.seed.persistence.mapper;

import br.com.aegro.datasync.seed.domain.model.Seed;
import br.com.aegro.datasync.seed.persistence.model.SeedData;

public interface SeedMapper {

    Seed toDomain(SeedData seedData);

    SeedData toData(Seed seed);

}
