package br.com.aegro.datasync.user.domain;

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
