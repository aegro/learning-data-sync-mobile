package br.com.aegro.datasync.user.persistence.dao;

import br.com.aegro.datasync.user.persistence.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserData, Long> {

    Optional<UserData> findByEmail(String email);

}
