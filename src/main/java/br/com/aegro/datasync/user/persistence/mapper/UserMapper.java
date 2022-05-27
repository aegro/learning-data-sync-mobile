package br.com.aegro.datasync.user.persistence.mapper;

import br.com.aegro.datasync.user.domain.model.User;
import br.com.aegro.datasync.user.persistence.model.UserData;

public interface UserMapper {

    User toDomain(UserData userData);

    UserData toData(User user);

}
