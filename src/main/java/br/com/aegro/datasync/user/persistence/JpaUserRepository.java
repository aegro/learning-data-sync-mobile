package br.com.aegro.datasync.user.persistence;

import br.com.aegro.datasync.user.domain.UserRepository;
import br.com.aegro.datasync.user.domain.model.User;
import br.com.aegro.datasync.user.persistence.dao.UserDao;
import br.com.aegro.datasync.user.persistence.mapper.UserMapper;

import java.util.Optional;

public class JpaUserRepository implements UserRepository {

    private final UserDao userDao;
    private final UserMapper userMapper;

    public JpaUserRepository(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> existDuplicatedByEmail(String id, String email) {
        return userDao.existDuplicatedByExternalIdAndEmail(id, email)
                .map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        var savedUser = userDao.save(userMapper.toData(user));
        return userMapper.toDomain(savedUser);
    }
}
