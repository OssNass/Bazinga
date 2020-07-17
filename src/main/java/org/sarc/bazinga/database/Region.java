package org.sarc.bazinga.database;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Region {
    private Integer id;
    private String shortname;
    private String fullname;
    private Collection<Family> familiesById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "shortname", nullable = false, length = 100)
    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Basic
    @Column(name = "fullname", nullable = false, length = 255)
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
        Region region = (Region) o;
        return Objects.equals(id, region.id) &&
                Objects.equals(shortname, region.shortname) &&
                Objects.equals(fullname, region.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortname, fullname);
    }

    @OneToMany(mappedBy = "regionByOrigin")
    public Collection<Family> getFamiliesById() {
        return familiesById;
    }

    public void setFamiliesById(Collection<Family> familiesById) {
        this.familiesById = familiesById;
    }
}
