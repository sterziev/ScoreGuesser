package ex.guesser.areas.points.repositories;

import ex.guesser.areas.points.entities.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    List<Prediction> findAllByUser_Username(String user_username);
    List<Prediction> findAllByUser_Id(String id);
    List<Prediction> findAllByUserId(String id);
    Prediction findFirstByUser_UsernameAndFootballMatch_Id(String user_username, long footballMatch_id);
    List<Prediction> findAllByFootballMatch_Id(long footballMatch_id);
}
