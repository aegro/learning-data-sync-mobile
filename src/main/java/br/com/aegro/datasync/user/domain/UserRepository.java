package br.com.aegro.datasync.user.domain;

import br.com.aegro.datasync.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    Optional<User> existDuplicatedByEmail(String id, String email);

    User save(User user);

}
