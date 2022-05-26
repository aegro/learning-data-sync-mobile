package br.com.aegro.datasync.seed.application.converter;

import br.com.aegro.datasync.seed.application.model.SeedModel;
import br.com.aegro.datasync.seed.domain.Seed;

public interface SeedConverter {

    Seed convertTo(SeedModel seedModel);

    SeedModel convertFrom(Seed seed);

}
