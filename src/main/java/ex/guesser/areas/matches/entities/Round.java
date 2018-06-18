package ex.guesser.areas.matches.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Integer round;


    @Column(nullable = false)
    private String status;

    @Column(name = "finish_round")
    private LocalDateTime finishRound;

    public Round() {
    }

    public LocalDateTime getFinishRound() {
        return finishRound;
    }

    public void setFinishRound(LocalDateTime finishRound) {
        this.finishRound = finishRound;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
