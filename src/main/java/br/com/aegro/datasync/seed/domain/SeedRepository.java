package br.com.aegro.datasync.seed.domain;

import br.com.aegro.datasync.user.domain.model.User;

import java.util.List;

public interface SeedRepository {

    List<Seed> findAllByUser(String userId);

    Seed save(Seed seed);

}
