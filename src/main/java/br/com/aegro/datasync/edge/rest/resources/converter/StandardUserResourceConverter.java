package br.com.aegro.datasync.edge.rest.resources.converter;

import br.com.aegro.datasync.edge.rest.resources.UserResource;
import br.com.aegro.datasync.user.application.model.UserModel;

public class StandardUserResourceConverter implements UserResourceConverter {
    @Override
    public UserResource convertTo(UserModel userModel) {
        return new UserResource(userModel.getId(), userModel.getFullName(), userModel.getEmail());
    }

    @Override
    public UserModel convertFrom(UserResource userResource) {
        return new UserModel(userResource.id(), userResource.fullName(), userResource.email());
    }
}
