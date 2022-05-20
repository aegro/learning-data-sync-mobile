package br.com.aegro.datasync.domain.repository;

import br.com.aegro.datasync.domain.entity.Seed;
import br.com.aegro.datasync.domain.entity.User;

import java.util.List;

public interface SeedRepository {

    List<Seed> findAllFor(User user);

    Seed save(Seed seed);

}
