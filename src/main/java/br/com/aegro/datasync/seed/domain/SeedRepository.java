package br.com.aegro.datasync.seed.domain;

import br.com.aegro.datasync.user.domain.User;

import java.util.List;

public interface SeedRepository {

    List<Seed> findAllFor(User user);

    Seed save(Seed seed);

}
