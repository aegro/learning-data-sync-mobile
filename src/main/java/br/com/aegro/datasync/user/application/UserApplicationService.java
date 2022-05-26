package br.com.aegro.datasync.user.application;

import br.com.aegro.datasync.user.application.model.UserModel;

public interface UserApplicationService {

    UserModel authenticate(String email);

}
