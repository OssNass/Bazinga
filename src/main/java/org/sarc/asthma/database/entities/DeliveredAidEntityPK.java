package org.sarc.asthma.database.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class DeliveredAidEntityPK implements Serializable {
    private Integer familyId;
    private Integer aidId;
    private LocalDate dateOfDelivered;

    @Column(name = "FAMILY_ID", nullable = false, insertable = false, updatable = false)
    @Id
    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Column(name = "AID_ID", nullable = false, insertable = false, updatable = false)
    @Id
    public Integer getAidId() {
        return aidId;
    }

    public void setAidId(Integer aidId) {
        this.aidId = aidId;
    }

    @Column(name = "DATE_OF_DELIVERED", nullable = false)
    @Id
    public LocalDate getDateOfDelivered() {
        return dateOfDelivered;
    }

    public void setDateOfDelivered(LocalDate dateOfDelivered) {
        this.dateOfDelivered = dateOfDelivered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveredAidEntityPK that = (DeliveredAidEntityPK) o;
        return Objects.equals(familyId, that.familyId) &&
                Objects.equals(aidId, that.aidId) &&
                Objects.equals(dateOfDelivered, that.dateOfDelivered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, aidId, dateOfDelivered);
    }
}
