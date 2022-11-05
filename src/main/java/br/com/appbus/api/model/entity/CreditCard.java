package br.com.appbus.api.model.entity;

import br.com.appbus.api.model.CreditCardFlag;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_BUS_CREDIT_CARD")
@SequenceGenerator(name = "SQ_BUS_CREDIT_CARD", sequenceName = "SQ_BUS_CREDIT_CARD", allocationSize = 1)
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BUS_CREDIT_CARD")
    @Column(name = "CD_CREDIT_CARD")
    private Long id;

    @Column(name = "NR_CREDIT_CARD")
    private String cardNumber;

    @Column(name = "NM_TITULAR")
    private String titularName;

    @Column(name = "NR_CPF_TITULAR")
    private String titularDocument;

    @Column(name = "NR_CVV")
    private String cvv;

    @Column(name = "DS_CREDIT_CARD_FLAG")
    @Enumerated(EnumType.STRING)
    private CreditCardFlag flag;

    @Column(name = "DT_EXPIRATION")
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "CD_USER")
    private User user;

    public CreditCard() {
    }

    public CreditCard(String cardNumber, String titularName, String titularDocument, String cvv, CreditCardFlag flag, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.titularName = titularName;
        this.titularDocument = titularDocument;
        this.cvv = cvv;
        this.flag = flag;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getTitularName() {
        return titularName;
    }

    public void setTitularName(String titularName) {
        this.titularName = titularName;
    }

    public String getTitularDocument() {
        return titularDocument;
    }

    public void setTitularDocument(String titularDocument) {
        this.titularDocument = titularDocument;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public CreditCardFlag getFlag() {
        return flag;
    }

    public void setFlag(CreditCardFlag flag) {
        this.flag = flag;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
