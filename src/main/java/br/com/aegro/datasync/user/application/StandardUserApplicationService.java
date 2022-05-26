package br.com.aegro.datasync.user.application;

import br.com.aegro.datasync.user.application.converter.UserConverter;
import br.com.aegro.datasync.user.application.model.UserModel;
import br.com.aegro.datasync.user.application.model.UserNotFoundException;
import br.com.aegro.datasync.user.domain.SaveUserService;
import br.com.aegro.datasync.user.domain.UserRepository;

public class StandardUserApplicationService implements UserApplicationService {

    private final UserConverter userConverter;

    private final UserRepository userRepository;

    private final SaveUserService saveUserService;

    public StandardUserApplicationService(
            UserConverter userConverter,
            UserRepository userRepository,
            SaveUserService saveUserService
    ) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.saveUserService = saveUserService;
    }

    @Override
    public UserModel authenticate(String email) {
        return userRepository.findByEmail(email)
                .map(userConverter::convertFrom)
                .orElseThrow(() -> new UserNotFoundException("Unable to find an user for email " + email));
    }
}
