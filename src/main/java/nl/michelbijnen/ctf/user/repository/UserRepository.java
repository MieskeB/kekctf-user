package nl.michelbijnen.ctf.user.repository;

import nl.michelbijnen.ctf.user.model.Team;
import nl.michelbijnen.ctf.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> getUsersByTeam(Team team);
}
