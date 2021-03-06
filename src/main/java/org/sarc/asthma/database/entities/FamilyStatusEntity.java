package org.sarc.asthma.database.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "FAMILY_STATUS", schema = "PUBLIC", catalog = "HEARTATTACK")
public class FamilyStatusEntity {
    private Integer id;
    private String name;
    private List<FamilyEntity> familiesById;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        FamilyStatusEntity that = (FamilyStatusEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "familyStatusByStatus")
    public List<FamilyEntity> getFamiliesById() {
        return familiesById;
    }

    public void setFamiliesById(List<FamilyEntity> familiesById) {
        this.familiesById = familiesById;
    }
}
