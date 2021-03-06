package br.com.aegro.datasync.edge.rest.resources.converter;

import br.com.aegro.datasync.edge.rest.resources.UserResource;
import br.com.aegro.datasync.user.application.model.UserModel;

public interface UserResourceConverter {

    UserResource convertTo(UserModel userModel);

    UserModel convertFrom(UserResource userResource);

}
