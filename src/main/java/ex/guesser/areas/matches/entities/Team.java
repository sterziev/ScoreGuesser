package ex.guesser.areas.matches.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private String groups;

    @Column
    private String flag;

    @Column
    private String clubLeague;

    @OneToMany(mappedBy="home")
    private List<FootballMatch> matchesHome;

    @OneToMany(mappedBy="away")
    private List<FootballMatch> matchesAway;

    public Team() {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getClubLeague() {
        return clubLeague;
    }

    public void setClubLeague(String clubLeague) {
        this.clubLeague = clubLeague;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public List<FootballMatch> getMatchesHome() {
        return matchesHome;
    }

    public void setMatchesHome(List<FootballMatch> matchesHome) {
        this.matchesHome = matchesHome;
    }

    public List<FootballMatch> getMatchesAway() {
        return matchesAway;
    }

    public void setMatchesAway(List<FootballMatch> matchesAway) {
        this.matchesAway = matchesAway;
    }
}
