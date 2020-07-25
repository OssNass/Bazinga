package org.sarc.asthma.database.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MemberEntityPK implements Serializable {
    private Integer familyId;
    private Integer memeberId;

    @Column(name = "FAMILY_ID", nullable = false, insertable = false, updatable = false)
    @Id
    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Column(name = "MEMEBER_ID", nullable = false, insertable = false, updatable = false)
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
        MemberEntityPK that = (MemberEntityPK) o;
        return Objects.equals(familyId, that.familyId) &&
                Objects.equals(memeberId, that.memeberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, memeberId);
    }
}
