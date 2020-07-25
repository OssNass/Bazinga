package org.sarc.asthma.database.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "RELATIONSHIP", schema = "PUBLIC", catalog = "HEARTATTACK")
public class RelationshipEntity {
    private Integer id;
    private String name;
    private Integer sex;
    private List<MemberEntity> membersById;

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

    @Basic
    @Column(name = "SEX", nullable = false)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationshipEntity that = (RelationshipEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sex, that.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex);
    }

    @OneToMany(mappedBy = "relationshipByRelationship")
    public List<MemberEntity> getMembersById() {
        return membersById;
    }

    public void setMembersById(List<MemberEntity> membersById) {
        this.membersById = membersById;
    }
}
