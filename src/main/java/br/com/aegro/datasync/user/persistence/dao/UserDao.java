package br.com.aegro.datasync.user.persistence.dao;

import br.com.aegro.datasync.user.persistence.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserData, Long> {

    Optional<UserData> findByEmail(String email);

    Optional<UserData> findByExternalId(String externalId);

    @Query("SELECT u from UserData u WHERE u.externalId != :externalId AND u.email = :email")
    Optional<UserData> existDuplicatedByExternalIdAndEmail(
            @Param("externalId") String externalId,
            @Param("email") String email
    );

}
