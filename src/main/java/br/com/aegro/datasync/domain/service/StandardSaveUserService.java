package br.com.aegro.datasync.domain.service;

import br.com.aegro.datasync.domain.entity.User;
import br.com.aegro.datasync.domain.exception.UserAlreadyRegisteredException;
import br.com.aegro.datasync.domain.repository.UserRepository;

public class StandardSaveUserService implements SaveUserService {

    private final UserRepository repository;

    public StandardSaveUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User execute(User user) {
        repository.findByEmail(user.getEmail())
                .ifPresent(userFromDatabase -> {
                    throw new UserAlreadyRegisteredException(userFromDatabase.getEmail());
                });
        return repository.save(user);
    }
}
