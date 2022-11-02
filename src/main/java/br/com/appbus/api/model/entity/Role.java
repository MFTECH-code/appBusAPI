package br.com.appbus.api.model.entity;

import br.com.appbus.api.model.Roles;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_BUS_ROLE")
@SequenceGenerator(name = "SQ_BUS_ROLE", sequenceName = "SQ_BUS_ROLE", allocationSize = 1)
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BUS_ROLE")
    @Column(name = "CD_ROLE")
    private Long id;

    @Column(name = "NM_ROLE")
    @Enumerated(EnumType.STRING)
    private Roles name;

    @ManyToMany(mappedBy = "roles")
    private List<User> user;

    public Role() {
    }

    public Role(Roles name) {
        this.name = name;
    }

    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name.toString();
    }
}
