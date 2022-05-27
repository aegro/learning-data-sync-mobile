package br.com.aegro.datasync.user.domain;

import br.com.aegro.datasync.commons.validation.Validator;
import br.com.aegro.datasync.user.domain.model.User;

public class StandardUserRegistryDomainService implements UserRegistryDomainService {

    private final UserRepository userRepository;

    private final Validator<User> userRegistryValidator;

    public StandardUserRegistryDomainService(
            UserRepository userRepository,
            Validator<User> userRegistryValidator
    ) {
        this.userRepository = userRepository;
        this.userRegistryValidator = userRegistryValidator;
    }

    @Override
    public User register(User user) {
        userRegistryValidator.validate(user);
        return userRepository.save(user);
    }
}
