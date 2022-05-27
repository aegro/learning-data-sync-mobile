package br.com.aegro.datasync.user.persistence.mapper;

import br.com.aegro.datasync.user.domain.model.User;
import br.com.aegro.datasync.user.persistence.model.UserData;

public class StandardUserMapper implements UserMapper {
    @Override
    public User toDomain(UserData userData) {
        return new User(
                userData.getId(),
                userData.getExternalId(),
                userData.getFullName(),
                userData.getEmail()
        );
    }

    @Override
    public UserData toData(User user) {
        var userData = new UserData();
        userData.setId(user.getId());
        userData.setExternalId(user.getExternalId());
        userData.setFullName(user.getFullName());
        userData.setEmail(user.getEmail());
        return userData;
    }
}
