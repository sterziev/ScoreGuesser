package ex.guesser.areas.user.repositories;

import ex.guesser.areas.user.entities.MiniLeague;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiniLeagueRepository extends JpaRepository<MiniLeague, String> {
    MiniLeague findByLeagueName(String leagueName);
    MiniLeague findByKeyCode(String key);
}
