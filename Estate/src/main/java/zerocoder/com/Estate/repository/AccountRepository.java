package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
