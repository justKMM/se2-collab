package hbrs.se2.collhbrs.repository;


import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDTO findUserByUsernameAndPassword(String username, String password);

    UserDTO findUserByUsername(String username);
}
