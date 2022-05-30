package br.com.aegro.datasync.seed.application;

import br.com.aegro.datasync.seed.application.converter.SeedConverter;
import br.com.aegro.datasync.seed.application.model.SeedModel;
import br.com.aegro.datasync.seed.domain.SeedRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StandardSeedQueryApplicationService implements SeedQueryApplicationService {

    private final SeedConverter seedConverter;

    private final SeedRepository seedRepository;

    public StandardSeedQueryApplicationService(SeedConverter seedConverter, SeedRepository seedRepository) {
        this.seedConverter = seedConverter;
        this.seedRepository = seedRepository;
    }

    @Override
    public List<SeedModel> findAllByUser(String userId) {
        return seedRepository.findAllByUser(userId)
                .stream().map(seedConverter::convertFrom)
                .collect(Collectors.toList());
    }
}
