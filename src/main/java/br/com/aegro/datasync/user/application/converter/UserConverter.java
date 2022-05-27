package br.com.aegro.datasync.user.application.converter;

import br.com.aegro.datasync.user.application.model.UserModel;
import br.com.aegro.datasync.user.domain.model.User;

public interface UserConverter {

    User convertTo(UserModel userModel);

    UserModel convertFrom(User user);

}
