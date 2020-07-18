package org.sarc.bazinga.database;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Relationship {
    private Integer id;
    private String name;
    private Collection<Member> membersById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship that = (Relationship) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "relationshipByRelationship")
    public Collection<Member> getMembersById() {
        return membersById;
    }

    public void setMembersById(Collection<Member> membersById) {
        this.membersById = membersById;
    }
}
