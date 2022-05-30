package br.com.aegro.datasync.edge.rest.resources.converter;

import br.com.aegro.datasync.edge.rest.resources.SeedResource;
import br.com.aegro.datasync.seed.application.model.SeedModel;

public interface SeedResourceConverter {

    SeedResource convertTo(SeedModel seedModel);

    SeedModel convertFrom(SeedResource seedResource);

}
