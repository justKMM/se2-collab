package hbrs.se2.collhbrs.repository;


import hbrs.se2.collhbrs.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByUserID(Long userID);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
