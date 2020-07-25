package org.sarc.asthma.database.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "AID", schema = "PUBLIC", catalog = "HEARTATTACK")
public class AidEntity {
    private int id;
    private String name;
    private List<DeliveredAidEntity> deliveredAidsById;

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
        AidEntity aidEntity = (AidEntity) o;
        return Objects.equals(id, aidEntity.id) &&
                Objects.equals(name, aidEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "aidByAidId")
    public List<DeliveredAidEntity> getDeliveredAidsById() {
        return deliveredAidsById;
    }

    public void setDeliveredAidsById(List<DeliveredAidEntity> deliveredAidsById) {
        this.deliveredAidsById = deliveredAidsById;
    }
}
