package org.sarc.bazinga.database;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "HOUSING_STATUS", schema = "PUBLIC", catalog = "HEARTATTACK")
public class HousingStatus {
    private Integer id;
    private String name;
    private Collection<Family> familiesById;

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
        HousingStatus that = (HousingStatus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "housingStatusByHousingTypeId")
    public Collection<Family> getFamiliesById() {
        return familiesById;
    }

    public void setFamiliesById(Collection<Family> familiesById) {
        this.familiesById = familiesById;
    }
}
