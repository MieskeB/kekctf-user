package nl.michelbijnen.ctf.user.repository;

import nl.michelbijnen.ctf.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
