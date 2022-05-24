package br.com.aegro.datasync.domain.service;

import br.com.aegro.datasync.domain.entity.User;

public interface SaveUserService {
    User execute(User user);
}
