package br.com.aegro.datasync.seed.application.converter;

import br.com.aegro.datasync.seed.application.model.SeedModel;
import br.com.aegro.datasync.seed.domain.model.Seed;
import br.com.aegro.datasync.user.application.converter.UserConverter;

public class StandardSeedConverter implements SeedConverter {

    private final UserConverter userConverter;

    public StandardSeedConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public Seed convertTo(SeedModel seedModel) {
        return new Seed(
                seedModel.getId(),
                seedModel.getName(),
                seedModel.getManufacturer(),
                seedModel.getManufacturedAt(),
                seedModel.getExpiresIn(),
                seedModel.getCreatedAt(),
                userConverter.convertTo(seedModel.getCreatedBy())
        );
    }

    @Override
    public SeedModel convertFrom(Seed seed) {
        return new SeedModel(
                seed.getId(),
                seed.getName(),
                seed.getManufacturer(),
                seed.getManufacturedAt(),
                seed.getExpiresIn(),
                seed.getCreatedAt(),
                userConverter.convertFrom(seed.getCreatedBy())
        );
    }
}
