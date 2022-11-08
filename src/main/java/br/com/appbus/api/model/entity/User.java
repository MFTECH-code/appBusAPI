package br.com.appbus.api.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TB_BUS_USER")
@SequenceGenerator(name = "SQ_BUS_USER", sequenceName = "SQ_BUS_USER", allocationSize = 1, initialValue = 1)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BUS_USER")
    @Column(name = "CD_USER")
    private Long id;

    @Column(name = "NM_USER", nullable = false)
    private String name;

    @Column(name = "DS_PASSWORD", length = 60, nullable = false)
    private String password;

    @Column(name = "DS_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "DS_PHONE", nullable = false, unique = true)
    private String phone;

    @Column(name = "DS_CPF", nullable = false, unique = true, updatable = false)
    private String document;

    @Column(name = "DT_BIRTH", nullable = false, updatable = false)
    private LocalDate birthDate;

    @Column(name = "NR_INDICATED_FRIENDS", nullable = false)
    private Integer indicatedFriends;

    @Column(name = "NR_SCORE", nullable = false)
    private Integer score;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(
            name = "TB_BUS_USER_ROLE",
            joinColumns = @JoinColumn(name = "CD_USER"),
            inverseJoinColumns = @JoinColumn(name = "CD_ROLE"))
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CreditCard> creditCards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BusTicket> busTickets = new ArrayList<>();

    @OneToMany(mappedBy = "id.user", cascade = CascadeType.ALL)
    private List<Evaluation> evaluations;

    public User() {
    }

    public User(String name, String password, String email, String phone, String document, LocalDate birthDate, Integer indicatedFriends, Integer score) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.birthDate = birthDate;
        this.indicatedFriends = indicatedFriends;
        this.score = score;
    }

    public User(String name, String password, String email, String phone, int indicatedFriends, int score) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.indicatedFriends = indicatedFriends;
        this.score = score;
    }

    public void addCreditCard(CreditCard creditCard) {
        creditCard.setUser(this);
        this.creditCards.add(creditCard);
    }

    public void addBusTicket(BusTicket busTicket) {
        busTicket.setUser(this);
        this.busTickets.add(busTicket);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getIndicatedFriends() {
        return indicatedFriends;
    }

    public void setIndicatedFriends(Integer indicatedFriends) {
        this.indicatedFriends = indicatedFriends;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public List<BusTicket> getBusTickets() {
        return busTickets;
    }

    public void setBusTickets(List<BusTicket> busTickets) {
        this.busTickets = busTickets;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
