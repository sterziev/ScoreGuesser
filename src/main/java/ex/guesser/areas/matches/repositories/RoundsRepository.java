package ex.guesser.areas.matches.repositories;

import ex.guesser.areas.matches.entities.FootballMatch;
import ex.guesser.areas.matches.entities.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundsRepository extends JpaRepository<Round, Long> {

    @Procedure(name = "finishRound")
    void finishRound();
}
