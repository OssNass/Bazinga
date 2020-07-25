package org.sarc.asthma.database.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MEMBER_STATUS", schema = "PUBLIC", catalog = "HEARTATTACK")
public class MemberStatusEntity {
    private int id;
    private String name;
    private List<MemberEntity> membersById;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 25)
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
        MemberStatusEntity that = (MemberStatusEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "memberStatusByStatusId")
    public List<MemberEntity> getMembersById() {
        return membersById;
    }

    public void setMembersById(List<MemberEntity> membersById) {
        this.membersById = membersById;
    }
}
