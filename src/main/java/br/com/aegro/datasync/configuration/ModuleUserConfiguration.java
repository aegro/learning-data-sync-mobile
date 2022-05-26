package br.com.aegro.datasync.configuration;

import br.com.aegro.datasync.user.application.StandardUserApplicationService;
import br.com.aegro.datasync.user.application.UserApplicationService;
import br.com.aegro.datasync.user.application.converter.StandardUserConverter;
import br.com.aegro.datasync.user.application.converter.UserConverter;
import br.com.aegro.datasync.user.domain.UserRepository;
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
    public UserConverter createUserConverter() {
        return new StandardUserConverter();
    }

    @Bean
    public UserApplicationService createUserApplicationService(
            UserConverter userConverter,
            UserRepository userRepository
    ) {
        return new StandardUserApplicationService(userConverter, userRepository);
    }
}
