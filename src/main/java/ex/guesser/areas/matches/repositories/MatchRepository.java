package ex.guesser.areas.matches.repositories;

import ex.guesser.areas.matches.entities.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<FootballMatch, Long> {
    List<FootballMatch> findAllByStatusOrderByKickOffAsc(String status);
    List<FootballMatch> findAllByOrderByKickOffAsc();

}
