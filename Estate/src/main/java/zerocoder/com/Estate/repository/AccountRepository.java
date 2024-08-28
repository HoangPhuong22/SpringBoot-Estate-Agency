package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.model.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Boolean existsByEmailAndIdNot(String email, Long id);
    Boolean existsByUsernameAndIdNot(String username, Long id);

    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);

    @Query("SELECT a FROM Account a JOIN a.roles r WHERE r.name = :roleName")
    List<Account> findAllByRoleName(@Param("roleName") String roleName);
}
