package br.com.aegro.datasync.user.domain;

import br.com.aegro.datasync.commons.validation.ValidationException;
import br.com.aegro.datasync.commons.validation.Validator;
import br.com.aegro.datasync.user.domain.model.User;

public class StandardUserRegistryValidator implements Validator<User> {

    private final UserRepository userRepository;

    public StandardUserRegistryValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(User candidate) {
        userRepository.existDuplicatedByEmail(candidate.getId(), candidate.getEmail())
                .ifPresent(userFromDatabase -> {
                    throw new ValidationException("There's an user registered for the email " + candidate.getEmail());
                });
    }
}
