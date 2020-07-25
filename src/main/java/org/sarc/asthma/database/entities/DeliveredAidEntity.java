package org.sarc.asthma.database.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "DELIVERED_AID", schema = "PUBLIC", catalog = "HEARTATTACK")
@IdClass(DeliveredAidEntityPK.class)
public class DeliveredAidEntity {
    private int familyId;
    private int aidId;
    private LocalDate dateOfDelivered;
    private Integer amount;
    private FamilyEntity familyByFamilyId;
    private AidEntity aidByAidId;

    @Id
    @Column(name = "FAMILY_ID", nullable = false)
    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    @Id
    @Column(name = "AID_ID", nullable = false)
    public int getAidId() {
        return aidId;
    }

    public void setAidId(int aidId) {
        this.aidId = aidId;
    }

    @Id
    @Column(name = "DATE_OF_DELIVERED", nullable = false)
    public LocalDate getDateOfDelivered() {
        return dateOfDelivered;
    }

    public void setDateOfDelivered(LocalDate dateOfDelivered) {
        this.dateOfDelivered = dateOfDelivered;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = false)
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
        DeliveredAidEntity that = (DeliveredAidEntity) o;
        return Objects.equals(familyId, that.familyId) &&
                Objects.equals(aidId, that.aidId) &&
                Objects.equals(dateOfDelivered, that.dateOfDelivered) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, aidId, dateOfDelivered, amount);
    }

    @ManyToOne
    @JoinColumn(name = "FAMILY_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public FamilyEntity getFamilyByFamilyId() {
        return familyByFamilyId;
    }

    public void setFamilyByFamilyId(FamilyEntity familyByFamilyId) {
        this.familyByFamilyId = familyByFamilyId;
    }

    @ManyToOne
    @JoinColumn(name = "AID_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public AidEntity getAidByAidId() {
        return aidByAidId;
    }

    public void setAidByAidId(AidEntity aidByAidId) {
        this.aidByAidId = aidByAidId;
    }
}
