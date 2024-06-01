package hbrs.se2.collhbrs.repository;


import hbrs.se2.collhbrs.model.dto.imp.UserDTOImpl;
import hbrs.se2.collhbrs.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDTOImpl findUserByUsernameAndPassword(String username, String password);
}
