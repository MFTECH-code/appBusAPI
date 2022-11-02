package br.com.appbus.api.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "TB_BUS_USER")
@SequenceGenerator(name = "SQ_BUS_USER", sequenceName = "SQ_BUS_USER", allocationSize = 1, initialValue = 1)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BUS_USER")
    @Column(name = "CD_USER")
    private Long id;

    @Column(name = "NM_USER", nullable = false)
    private String name;

    @Column(name = "DS_PASSWORD", length = 12, nullable = false)
    private String password;

    @Column(name = "DS_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "DS_PHONE", nullable = false, unique = true)
    private String phone;

    @Column(name = "DS_CPF", nullable = false, unique = true, updatable = false)
    private String document;

    @Column(name = "DT_BIRTH", nullable = false, updatable = false)
    private LocalDate birthDay;

    @Column(name = "NR_INDICATED_FRIENDS", nullable = false)
    private int indicatedFriends;

    @Column(name = "NR_SCORE", nullable = false)
    private int score;

    public User(String name, String password, String email, String phone, String document, LocalDate birthDay, int indicatedFriends, int score) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.birthDay = birthDay;
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public int getIndicatedFriends() {
        return indicatedFriends;
    }

    public void setIndicatedFriends(int indicatedFriends) {
        this.indicatedFriends = indicatedFriends;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
