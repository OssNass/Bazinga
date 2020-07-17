package org.sarc.bazinga.database;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Family {
    private Integer id;
    private Integer status;
    private Integer familyType;
    private Integer beneficaryId;
    private Integer providerId;
    private Integer origin;
    private Date dateOfRegisteration;
    private Boolean leadByWoman;
    private Collection<DeliveredAid> deliveredAidsById;
    private FamilyStatus familyStatusByStatus;
    private FamilyType familyTypeByFamilyType;
    private Region regionByOrigin;
    private Collection<Member> membersById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "family_type", nullable = false)
    public Integer getFamilyType() {
        return familyType;
    }

    public void setFamilyType(Integer familyType) {
        this.familyType = familyType;
    }

    @Basic
    @Column(name = "beneficary_id", nullable = true)
    public Integer getBeneficaryId() {
        return beneficaryId;
    }

    public void setBeneficaryId(Integer beneficaryId) {
        this.beneficaryId = beneficaryId;
    }

    @Basic
    @Column(name = "provider_id", nullable = true)
    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    @Basic
    @Column(name = "origin", nullable = true)
    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    @Basic
    @Column(name = "date_of_registeration", nullable = false)
    public Date getDateOfRegisteration() {
        return dateOfRegisteration;
    }

    public void setDateOfRegisteration(Date dateOfRegisteration) {
        this.dateOfRegisteration = dateOfRegisteration;
    }

    @Basic
    @Column(name = "lead_by_woman", nullable = false)
    public Boolean getLeadByWoman() {
        return leadByWoman;
    }

    public void setLeadByWoman(Boolean leadByWoman) {
        this.leadByWoman = leadByWoman;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(id, family.id) &&
                Objects.equals(status, family.status) &&
                Objects.equals(familyType, family.familyType) &&
                Objects.equals(beneficaryId, family.beneficaryId) &&
                Objects.equals(providerId, family.providerId) &&
                Objects.equals(origin, family.origin) &&
                Objects.equals(dateOfRegisteration, family.dateOfRegisteration) &&
                Objects.equals(leadByWoman, family.leadByWoman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, familyType, beneficaryId, providerId, origin, dateOfRegisteration, leadByWoman);
    }

    @OneToMany(mappedBy = "familyByFamilyId")
    public Collection<DeliveredAid> getDeliveredAidsById() {
        return deliveredAidsById;
    }

    public void setDeliveredAidsById(Collection<DeliveredAid> deliveredAidsById) {
        this.deliveredAidsById = deliveredAidsById;
    }

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    public FamilyStatus getFamilyStatusByStatus() {
        return familyStatusByStatus;
    }

    public void setFamilyStatusByStatus(FamilyStatus familyStatusByStatus) {
        this.familyStatusByStatus = familyStatusByStatus;
    }

    @ManyToOne
    @JoinColumn(name = "family_type", referencedColumnName = "id", nullable = false)
    public FamilyType getFamilyTypeByFamilyType() {
        return familyTypeByFamilyType;
    }

    public void setFamilyTypeByFamilyType(FamilyType familyTypeByFamilyType) {
        this.familyTypeByFamilyType = familyTypeByFamilyType;
    }

    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "id")
    public Region getRegionByOrigin() {
        return regionByOrigin;
    }

    public void setRegionByOrigin(Region regionByOrigin) {
        this.regionByOrigin = regionByOrigin;
    }

    @OneToMany(mappedBy = "familyByFamilyId")
    public Collection<Member> getMembersById() {
        return membersById;
    }

    public void setMembersById(Collection<Member> membersById) {
        this.membersById = membersById;
    }
}
