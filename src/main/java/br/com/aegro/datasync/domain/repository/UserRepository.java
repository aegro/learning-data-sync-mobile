package br.com.aegro.datasync.domain.repository;

import br.com.aegro.datasync.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    User save(User user);

}
