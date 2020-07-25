package org.sarc.asthma.database.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MemberEntityPK implements Serializable {
    private int familyId;
    private int memeberId;

    @Column(name = "FAMILY_ID", nullable = false, insertable = false, updatable = false)
    @Id
    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    @Column(name = "MEMEBER_ID", nullable = false, insertable = false, updatable = false)
    @Id
    public int getMemeberId() {
        return memeberId;
    }

    public void setMemeberId(int memeberId) {
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
