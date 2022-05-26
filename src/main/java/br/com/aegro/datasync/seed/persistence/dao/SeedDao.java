package br.com.aegro.datasync.seed.persistence.dao;

import br.com.aegro.datasync.seed.persistence.model.SeedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeedDao extends JpaRepository<SeedData, Long> {

    @Query("SELECT s FROM SeedData s JOIN s.createdBy u WHERE u.id = ?1")
    List<SeedData> findAllByUser(String userId);

}
