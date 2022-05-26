package br.com.aegro.datasync.seed.persistence.mapper;

import br.com.aegro.datasync.seed.domain.Seed;
import br.com.aegro.datasync.seed.persistence.model.SeedData;
import br.com.aegro.datasync.user.persistence.mapper.UserMapper;

public class StandardSeedMapper implements SeedMapper {

    private final UserMapper userMapper;

    public StandardSeedMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Seed toDomain(SeedData seedData) {
        return new Seed(
                seedData.getExternalId(),
                seedData.getName(),
                seedData.getManufacturer(),
                seedData.getManufacturedAt(),
                seedData.getExpiresIn(),
                seedData.getCreatedAt(),
                userMapper.toDomain(seedData.getCreatedBy())
        );
    }

    @Override
    public SeedData toData(Seed seed) {
        var seedData = new SeedData();
        seedData.setExternalId(seed.getId());
        seedData.setName(seed.getName());
        seedData.setManufacturer(seed.getManufacturer());
        seedData.setManufacturedAt(seed.getManufacturedAt());
        seedData.setExpiresIn(seed.getExpiresIn());
        seedData.setCreatedAt(seed.getCreatedAt());
        seedData.setCreatedBy(userMapper.toData(seed.getCreatedBy()));
        return seedData;
    }
}
