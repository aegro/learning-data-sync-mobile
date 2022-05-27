package br.com.aegro.datasync.user.application;

import br.com.aegro.datasync.user.application.converter.UserConverter;
import br.com.aegro.datasync.user.application.model.UserAlreadyRegisteredException;
import br.com.aegro.datasync.user.application.model.UserModel;
import br.com.aegro.datasync.user.application.model.UserNotFoundException;
import br.com.aegro.datasync.user.domain.UserRepository;

public class StandardUserApplicationService implements UserApplicationService {

    private final UserConverter userConverter;

    private final UserRepository userRepository;

    public StandardUserApplicationService(UserConverter userConverter, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    @Override
    public UserModel authenticate(String email) {
        return userRepository.findByEmail(email)
                .map(userConverter::convertFrom)
                .orElseThrow(() -> new UserNotFoundException("Unable to find an user for email " + email));
    }

    @Override
    public void save(UserModel userModel) {
        var user = userConverter.convertTo(userModel);

        userRepository.findByEmail(user.getEmail())
                .ifPresent(userFromDatabase -> {
                    throw new UserAlreadyRegisteredException(userFromDatabase.getEmail());
                });

        var savedUser = userRepository.save(user);

        userConverter.convertFrom(savedUser);
    }
}
