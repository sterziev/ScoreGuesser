package ex.guesser.areas.matches.repositories;

import ex.guesser.areas.matches.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findFirstByName(String name);
}
