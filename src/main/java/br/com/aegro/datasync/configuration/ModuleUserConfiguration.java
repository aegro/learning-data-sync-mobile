package br.com.aegro.datasync.configuration;

import br.com.aegro.datasync.commons.validation.Validator;
import br.com.aegro.datasync.edge.rest.resources.converter.StandardUserResourceConverter;
import br.com.aegro.datasync.edge.rest.resources.converter.UserResourceConverter;
import br.com.aegro.datasync.user.application.StandardUserApplicationService;
import br.com.aegro.datasync.user.application.UserApplicationService;
import br.com.aegro.datasync.user.application.converter.StandardUserConverter;
import br.com.aegro.datasync.user.application.converter.UserConverter;
import br.com.aegro.datasync.user.domain.StandardUserRegistryDomainService;
import br.com.aegro.datasync.user.domain.StandardUserRegistryValidator;
import br.com.aegro.datasync.user.domain.UserRegistryDomainService;
import br.com.aegro.datasync.user.domain.UserRepository;
import br.com.aegro.datasync.user.domain.model.User;
import br.com.aegro.datasync.user.persistence.JpaUserRepository;
import br.com.aegro.datasync.user.persistence.dao.UserDao;
import br.com.aegro.datasync.user.persistence.mapper.StandardUserMapper;
import br.com.aegro.datasync.user.persistence.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleUserConfiguration {

    @Bean
    public UserMapper createUserMapper() {
        return new StandardUserMapper();
    }

    @Bean
    public UserRepository createUserRepository(UserDao userDao, UserMapper userMapper) {
        return new JpaUserRepository(userDao, userMapper);
    }

    @Bean
    public Validator<User> createUserRegistryValidator(UserRepository userRepository) {
        return new StandardUserRegistryValidator(userRepository);
    }

    @Bean
    public UserRegistryDomainService createUserRegistryDomainService(
            UserRepository userRepository,
            Validator<User> userRegistryValidator
    ) {
        return new StandardUserRegistryDomainService(userRepository, userRegistryValidator);
    }

    @Bean
    public UserConverter createUserConverter() {
        return new StandardUserConverter();
    }

    @Bean
    public UserApplicationService createUserApplicationService(
            UserConverter userConverter,
            UserRepository userRepository,
            UserRegistryDomainService userRegistryDomainService
    ) {
        return new StandardUserApplicationService(
                userConverter,
                userRepository,
                userRegistryDomainService
        );
    }

    @Bean
    public UserResourceConverter createUserResourceConverter() {
        return new StandardUserResourceConverter();
    }
}
