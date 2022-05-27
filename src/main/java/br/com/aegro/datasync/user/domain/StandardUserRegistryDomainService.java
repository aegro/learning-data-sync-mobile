package br.com.aegro.datasync.user.domain;

import br.com.aegro.datasync.user.domain.model.UserAlreadyRegisteredException;
import br.com.aegro.datasync.user.domain.model.User;

public class StandardUserRegistryDomainService implements UserRegistryDomainService {

    private final UserRepository userRepository;

    public StandardUserRegistryDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        userRepository.existDuplicatedByEmail(user.getId(), user.getEmail())
                .ifPresent(userFromDatabase -> {
                    throw new UserAlreadyRegisteredException(userFromDatabase.getEmail());
                });

        return userRepository.save(user);
    }
}
