package br.com.appbus.api.model.entity;

import br.com.appbus.api.model.BusState;
import br.com.appbus.api.model.BusType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TB_BUS_BUS")
@SequenceGenerator(name = "SQ_BUS_BUS", sequenceName = "SQ_BUS_BUS", allocationSize = 1)
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BUS_BUS")
    @Column(name = "CD_BUS")
    private Long id;

    @Column(name = "NR_BUS")
    private String number;

    @Column(name = "NR_LINE")
    private String line;

    @Column(name = "DS_REGION")
    private String region;

    @Column(name = "VL_RATE")
    private Double rate;

    @Column(name = "ST_ACCESSIBLE")
    private Boolean accessible;

    @Column(name = "ST_BUS")
    @Enumerated(EnumType.STRING)
    private BusState state;

    @Column(name = "VL_AVERAGE_POINTS")
    private Double averagePoints;

    @Column(name = "TP_BUS")
    private BusType type;

    @OneToMany(mappedBy = "id.bus", cascade = CascadeType.ALL)
    private List<Evaluation> evaluations = new ArrayList<>();

    public Bus() {
    }

    public Bus(String number, String line, String region, Double rate, Boolean accessible, BusState state, Double averagePoints, BusType type) {
        this.number = number;
        this.line = line;
        this.region = region;
        this.rate = rate;
        this.accessible = accessible;
        this.state = state;
        this.averagePoints = averagePoints;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Boolean getAccessible() {
        return accessible;
    }

    public void setAccessible(Boolean accessible) {
        this.accessible = accessible;
    }

    public BusState getState() {
        return state;
    }

    public void setState(BusState state) {
        this.state = state;
    }

    public Double getAveragePoints() {
        return averagePoints;
    }

    public void setAveragePoints(Double averagePoints) {
        this.averagePoints = averagePoints;
    }

    public BusType getType() {
        return type;
    }

    public void setType(BusType type) {
        this.type = type;
    }
}
