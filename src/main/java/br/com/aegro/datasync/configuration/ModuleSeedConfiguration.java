package br.com.aegro.datasync.configuration;

import br.com.aegro.datasync.commons.validation.Validator;
import br.com.aegro.datasync.edge.rest.resources.converter.SeedResourceConverter;
import br.com.aegro.datasync.edge.rest.resources.converter.StandardSeedResourceConverter;
import br.com.aegro.datasync.seed.application.SeedApplicationService;
import br.com.aegro.datasync.seed.application.SeedQueryApplicationService;
import br.com.aegro.datasync.seed.application.StandardSeedApplicationService;
import br.com.aegro.datasync.seed.application.StandardSeedQueryApplicationService;
import br.com.aegro.datasync.seed.application.converter.SeedConverter;
import br.com.aegro.datasync.seed.application.converter.StandardSeedConverter;
import br.com.aegro.datasync.seed.domain.SeedRegistryDomainService;
import br.com.aegro.datasync.seed.domain.SeedRepository;
import br.com.aegro.datasync.seed.domain.StandardSeedRegistryDomainService;
import br.com.aegro.datasync.seed.domain.StandardSeedRegistryValidator;
import br.com.aegro.datasync.seed.domain.model.Seed;
import br.com.aegro.datasync.seed.persistence.JpaSeedRepository;
import br.com.aegro.datasync.seed.persistence.dao.SeedDao;
import br.com.aegro.datasync.seed.persistence.mapper.SeedMapper;
import br.com.aegro.datasync.seed.persistence.mapper.StandardSeedMapper;
import br.com.aegro.datasync.user.application.converter.UserConverter;
import br.com.aegro.datasync.user.persistence.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleSeedConfiguration {

    @Bean
    public SeedMapper createSeedMapper(UserMapper userMapper) {
        return new StandardSeedMapper(userMapper);
    }

    @Bean
    public SeedRepository createSeedRepository(SeedDao seedDao, SeedMapper seedMapper) {
        return new JpaSeedRepository(seedDao, seedMapper);
    }

    @Bean
    public Validator<Seed> createSeedRegistryValidator() {
        return new StandardSeedRegistryValidator();
    }

    @Bean
    public SeedRegistryDomainService createSeedRegistryDomainService(
            Validator<Seed> seedRegistryValidator,
            SeedRepository seedRepository
    ) {
        return new StandardSeedRegistryDomainService(seedRegistryValidator, seedRepository);
    }

    @Bean
    public SeedConverter createSeedConverter(UserConverter userConverter) {
        return new StandardSeedConverter(userConverter);
    }

    @Bean
    public SeedApplicationService createSeedApplicationService(
            SeedConverter seedConverter,
            SeedRegistryDomainService seedRegistryDomainService
    ) {
        return new StandardSeedApplicationService(seedConverter, seedRegistryDomainService);
    }

    @Bean
    public SeedQueryApplicationService createSeedQueryApplicationService(
            SeedConverter seedConverter,
            SeedRepository seedRepository
    ) {
        return new StandardSeedQueryApplicationService(seedConverter, seedRepository);
    }

    @Bean
    public SeedResourceConverter createSeedResourceConverter() {
        return new StandardSeedResourceConverter();
    }

}
