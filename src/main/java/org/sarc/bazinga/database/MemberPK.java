package org.sarc.bazinga.database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MemberPK implements Serializable {
    private Integer familyId;
    private Integer memeberId;

    @Column(name = "family_id", nullable = false)
    @Id
    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Column(name = "memeber_id", nullable = false)
    @Id
    public Integer getMemeberId() {
        return memeberId;
    }

    public void setMemeberId(Integer memeberId) {
        this.memeberId = memeberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberPK memberPK = (MemberPK) o;
        return Objects.equals(familyId, memberPK.familyId) &&
                Objects.equals(memeberId, memberPK.memeberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, memeberId);
    }
}
