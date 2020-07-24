package org.sarc.asthma.database.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "FAMILY_TYPE", schema = "PUBLIC", catalog = "HEARTATTACK")
public class FamilyTypeEntity {
    private Integer id;
    private String name;
    private List<FamilyEntity> familiesById;

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
        FamilyTypeEntity that = (FamilyTypeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "familyTypeByFamilyType")
    public List<FamilyEntity> getFamiliesById() {
        return familiesById;
    }

    public void setFamiliesById(List<FamilyEntity> familiesById) {
        this.familiesById = familiesById;
    }
}
