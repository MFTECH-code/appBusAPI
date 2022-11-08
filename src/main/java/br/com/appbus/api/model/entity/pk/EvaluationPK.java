package br.com.appbus.api.model.entity.pk;

import br.com.appbus.api.model.entity.Bus;
import br.com.appbus.api.model.entity.User;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EvaluationPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 6131114886334794270L;
    @ManyToOne
    @JoinColumn(name = "CD_USER")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CD_BUS")
    private Bus bus;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationPK that = (EvaluationPK) o;
        return Objects.equals(getUser(), that.getUser()) && Objects.equals(getBus(), that.getBus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getBus());
    }
}
