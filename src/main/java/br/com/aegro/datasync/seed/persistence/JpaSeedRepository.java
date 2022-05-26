package br.com.aegro.datasync.seed.persistence;

import br.com.aegro.datasync.seed.domain.Seed;
import br.com.aegro.datasync.seed.domain.SeedRepository;
import br.com.aegro.datasync.seed.persistence.dao.SeedDao;
import br.com.aegro.datasync.seed.persistence.mapper.SeedMapper;

import java.util.List;
import java.util.stream.Collectors;

public class JpaSeedRepository implements SeedRepository {

    private final SeedDao seedDao;

    private final SeedMapper seedMapper;

    public JpaSeedRepository(SeedDao seedDao, SeedMapper seedMapper) {
        this.seedDao = seedDao;
        this.seedMapper = seedMapper;
    }

    @Override
    public List<Seed> findAllByUser(String userId) {
        return seedDao.findAllByUser(userId)
                .stream().map(seedMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Seed save(Seed seed) {
        var seedData = seedMapper.toData(seed);
        var savedSeed = seedDao.save(seedData);
        return seedMapper.toDomain(savedSeed);
    }
}
