package ex.guesser.areas.points.repositories;

import ex.guesser.areas.points.entities.Points;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Points, Long> {

    List<Points> findAllByUser_Username(String user_username);
}
