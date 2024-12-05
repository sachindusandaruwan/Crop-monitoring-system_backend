package lk.ijse.gdse68.Crop.monitoring.system.Repository;

import lk.ijse.gdse68.Crop.monitoring.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,String> {

    boolean existsByEmail(String s);

    Optional<User> findByEmail(String email);

}
