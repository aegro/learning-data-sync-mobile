package br.com.aegro.datasync.user.application.converter;

import br.com.aegro.datasync.user.application.model.UserModel;
import br.com.aegro.datasync.user.domain.model.User;

public class StandardUserConverter implements UserConverter {
    @Override
    public User convertTo(UserModel userModel) {
        return new User(
                null,
                userModel.getId(),
                userModel.getFullName(),
                userModel.getEmail()
        );
    }

    @Override
    public UserModel convertFrom(User user) {
        return new UserModel(
                user.getExternalId(),
                user.getFullName(),
                user.getEmail()
        );
    }
}
