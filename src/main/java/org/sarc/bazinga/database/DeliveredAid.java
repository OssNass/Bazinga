package org.sarc.bazinga.database;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "delivered_aid", schema = "PUBLIC", catalog = "HEARTATTACK")
@IdClass(DeliveredAidPK.class)
public class DeliveredAid {
    private Integer familyId;
    private Integer aidId;
    private Date dateOfDelievered;
    private Integer amount;
    private Family familyByFamilyId;
    private Aid aidByAidId;

    @Id
    @Column(name = "family_id", nullable = false)
    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Id
    @Column(name = "aid_id", nullable = false)
    public Integer getAidId() {
        return aidId;
    }

    public void setAidId(Integer aidId) {
        this.aidId = aidId;
    }

    @Id
    @Column(name = "date_of_delievered", nullable = false)
    public Date getDateOfDelievered() {
        return dateOfDelievered;
    }

    public void setDateOfDelievered(Date dateOfDelievered) {
        this.dateOfDelievered = dateOfDelievered;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveredAid that = (DeliveredAid) o;
        return Objects.equals(familyId, that.familyId) &&
                Objects.equals(aidId, that.aidId) &&
                Objects.equals(dateOfDelievered, that.dateOfDelievered) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, aidId, dateOfDelievered, amount);
    }

    @ManyToOne
    @JoinColumn(name = "family_id", referencedColumnName = "id", nullable = false)
    public Family getFamilyByFamilyId() {
        return familyByFamilyId;
    }

    public void setFamilyByFamilyId(Family familyByFamilyId) {
        this.familyByFamilyId = familyByFamilyId;
    }

    @ManyToOne
    @JoinColumn(name = "aid_id", referencedColumnName = "id", nullable = false)
    public Aid getAidByAidId() {
        return aidByAidId;
    }

    public void setAidByAidId(Aid aidByAidId) {
        this.aidByAidId = aidByAidId;
    }
}
