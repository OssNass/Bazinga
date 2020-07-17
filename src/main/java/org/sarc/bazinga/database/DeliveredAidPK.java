package org.sarc.bazinga.database;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class DeliveredAidPK implements Serializable {
    private Integer familyId;
    private Integer aidId;
    private Date dateOfDelievered;

    @Column(name = "family_id", nullable = false)
    @Id
    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Column(name = "aid_id", nullable = false)
    @Id
    public Integer getAidId() {
        return aidId;
    }

    public void setAidId(Integer aidId) {
        this.aidId = aidId;
    }

    @Column(name = "date_of_delievered", nullable = false)
    @Id
    public Date getDateOfDelievered() {
        return dateOfDelievered;
    }

    public void setDateOfDelievered(Date dateOfDelievered) {
        this.dateOfDelievered = dateOfDelievered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveredAidPK that = (DeliveredAidPK) o;
        return Objects.equals(familyId, that.familyId) &&
                Objects.equals(aidId, that.aidId) &&
                Objects.equals(dateOfDelievered, that.dateOfDelievered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, aidId, dateOfDelievered);
    }
}
