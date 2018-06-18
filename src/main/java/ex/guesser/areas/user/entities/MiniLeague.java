package ex.guesser.areas.user.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mini_league")
public class MiniLeague{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column
    private String leagueName;

    @Column
    private String keyCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "league_users",
    joinColumns = @JoinColumn(name = "league_id"),
    inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private Set<User> participants;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "league_admin",
            joinColumns = @JoinColumn(name = "league_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private Set<User> admins;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    public MiniLeague() {
        this.participants = new HashSet<>();
        this.admins = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public Set<User> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<User> admins) {
        this.admins = admins;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void addParticipant(User participant){
        this.participants.add(participant);
    }

    public void addAdmin(User admin){
        this.participants.add(admin);
    }
}
