package br.com.aegro.datasync.seed.application;

import br.com.aegro.datasync.seed.application.model.SeedModel;

import java.util.List;

public interface SeedQueryApplicationService {

    List<SeedModel> findAllByUser(String userId);

}
