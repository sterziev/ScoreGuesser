package ex.guesser.areas.user.entities;

import ex.guesser.areas.common.enums.Gender;
import ex.guesser.areas.points.entities.Points;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String favTeam;

    @Column
    private LocalDate birthday;

    @Column
    private String country;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender sex;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities;

    @OneToMany(mappedBy = "user")
    private Set<Points> userPoints;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "league_admin",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "league_id"))
    private Set<MiniLeague> adminLeague;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "league_users",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "league_id"))
    private Set<MiniLeague> miniLeagues;

    public User() {
        this.authorities = new HashSet<>();
        this.userPoints = new HashSet<>();
        this.miniLeagues = new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void addRole(Role role){
        authorities.add(role);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavTeam() {
        return favTeam;
    }

    public void setFavTeam(String favTeam) {
        this.favTeam = favTeam;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getSex() {
        return sex;
    }
    public String getSexString() {
        if (sex ==null){
            return null;
        }
        return sex.toString();
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Set<Points> getUserPoints() {
        return userPoints;
    }

    public void setUserPoints(Set<Points> userPoints) {
        this.userPoints = userPoints;
    }

//    @PrePersist
//    private void prePersist() {
//        userPoints.forEach( c -> c.setUser(this));
//    }

    public Set<MiniLeague> getAdminLeague() {
        return adminLeague;
    }

    public void setAdminLeague(Set<MiniLeague> adminLeague) {
        this.adminLeague = adminLeague;
    }

    public Set<MiniLeague> getMiniLeagues() {
        return miniLeagues;
    }

    public void setMiniLeagues(Set<MiniLeague> miniLeagues) {
        this.miniLeagues = miniLeagues;
    }
}
