package br.com.appbus.api.model.entity;

import br.com.appbus.api.model.entity.pk.EvaluationPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "TB_BUS_EVALUATION")
public class Evaluation {
    @EmbeddedId
    private EvaluationPK id = new EvaluationPK();
    private String comment;
    private Integer evaluationNote;

    public Evaluation() {
    }

    public Evaluation(User user, Bus bus, String comment, Integer evaluationNote) {
        this.comment = comment;
        this.evaluationNote = evaluationNote;
        this.id.setUser(user);
        this.id.setBus(bus);
    }

    public Bus getBus() {
        return id.getBus();
    }

    public void setBus(Bus bus) {
        id.setBus(bus);
    }

    public User getUser() {
        return id.getUser();
    }

    public void setUser(User user) {
        id.setUser(user);
    }

    public EvaluationPK getId() {
        return id;
    }

    public void setId(EvaluationPK id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getEvaluationNote() {
        return evaluationNote;
    }

    public void setEvaluationNote(Integer evaluationNote) {
        this.evaluationNote = evaluationNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluation that = (Evaluation) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
