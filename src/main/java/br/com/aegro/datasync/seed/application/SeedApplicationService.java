package br.com.aegro.datasync.seed.application;

import br.com.aegro.datasync.seed.application.model.SeedModel;
import br.com.aegro.datasync.seed.domain.Seed;

public interface SeedApplicationService {

    SeedModel save(SeedModel seedModel);

}
