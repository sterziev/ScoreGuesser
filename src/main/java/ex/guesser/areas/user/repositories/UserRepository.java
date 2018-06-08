package ex.guesser.areas.user.repositories;


import ex.guesser.areas.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    @Query("SELECT p FROM User p LEFT JOIN FETCH p.miniLeagues WHERE p.username = (:username)")
    User findByUsernameFetchMiniLeagues(@Param("username") String username);

    @Query("SELECT p FROM User p LEFT JOIN FETCH p.miniLeagues LEFT join fetch p.userPoints WHERE p.username = (:username)")
    User findByUsernameFetchMiniLeaguesAndPoints(@Param("username") String username);

    @Query("SELECT p FROM User p LEFT JOIN FETCH p.miniLeagues LEFT join fetch p.userPoints WHERE p.id = (:id)")
    User findByUserIdFetchMiniLeaguesAndPoints(@Param("id") String id);

}
