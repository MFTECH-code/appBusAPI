package br.com.appbus.api.model.entity;

import br.com.appbus.api.model.BusTicketType;

import javax.persistence.*;

@Entity
@Table(name = "TB_BUS_TICKET")
@SequenceGenerator(name = "SQ_BUS_TICKET", sequenceName = "SQ_BUS_TICKET", allocationSize = 1)
public class BusTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BUS_TICKET")
    @Column(name = "CD_TICKET")
    private Long id;

    @Column(name = "NR_TICKET", unique = true)
    private String ticketNumber;

    @Column(name = "DS_TICKET_TYPE")
    @Enumerated(EnumType.STRING)
    private BusTicketType ticketType;

    @Column(name = "VL_TICKET")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "CD_USER")
    private User user;

    public BusTicket() {
    }

    public BusTicket(String ticketNumber, BusTicketType ticketType, Double balance) {
        this.ticketNumber = ticketNumber;
        this.ticketType = ticketType;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public BusTicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(BusTicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
