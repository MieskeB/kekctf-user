package nl.michelbijnen.ctf.user.repository;

import nl.michelbijnen.ctf.user.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}
