package org.sarc.asthma.database.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "REGION", schema = "PUBLIC", catalog = "HEARTATTACK")
@NamedQueries(
        @NamedQuery(name = "Regions by List of Full Names",
                query = "SELECT R FROM RegionEntity R WHERE R.fullname IN (:LIST)")
)
public class RegionEntity {
    private Integer id;
    private String shortname;
    private String fullname;
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
    @Column(name = "SHORTNAME", nullable = false, length = 100)
    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Basic
    @Column(name = "FULLNAME", nullable = false, length = 255)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionEntity that = (RegionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(shortname, that.shortname) &&
                Objects.equals(fullname, that.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortname, fullname);
    }

    @OneToMany(mappedBy = "regionByOrigin")
    public List<FamilyEntity> getFamiliesById() {
        return familiesById;
    }

    public void setFamiliesById(List<FamilyEntity> familiesById) {
        this.familiesById = familiesById;
    }
}
