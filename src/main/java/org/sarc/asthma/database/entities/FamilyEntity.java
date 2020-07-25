package org.sarc.asthma.database.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "FAMILY", schema = "PUBLIC", catalog = "HEARTATTACK")
public class FamilyEntity {
    private Integer id;
    private Integer status;
    private Integer familyType;
    private Integer beneficaryId;
    private Integer providerId;
    private Integer origin;
    private LocalDate dateOfRegisteration;
    private Boolean leadByWoman;
    private String address;
    private Integer housingTypeId;
    private Integer houseingStatusId;
    private Boolean married;
    private List<DeliveredAidEntity> deliveredAidsById;
    private FamilyStatusEntity familyStatusByStatus;
    private FamilyTypeEntity familyTypeByFamilyType;
    private RegionEntity regionByOrigin;
    private HousingStatusEntity housingStatusByHousingTypeId;
    private HousingTypeEntity housingTypeByHousingTypeId;
    private List<MemberEntity> membersById;

    @Id
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STATUS", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "FAMILY_TYPE", nullable = false)
    public Integer getFamilyType() {
        return familyType;
    }

    public void setFamilyType(Integer familyType) {
        this.familyType = familyType;
    }

    @Basic
    @Column(name = "BENEFICARY_ID", nullable = true)
    public Integer getBeneficaryId() {
        return beneficaryId;
    }

    public void setBeneficaryId(Integer beneficaryId) {
        this.beneficaryId = beneficaryId;
    }

    @Basic
    @Column(name = "PROVIDER_ID", nullable = true)
    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    @Basic
    @Column(name = "ORIGIN", nullable = true)
    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    @Basic
    @Column(name = "DATE_OF_REGISTERATION", nullable = false)
    public LocalDate getDateOfRegisteration() {
        return dateOfRegisteration;
    }

    public void setDateOfRegisteration(LocalDate dateOfRegisteration) {
        this.dateOfRegisteration = dateOfRegisteration;
    }

    @Basic
    @Column(name = "LEAD_BY_WOMAN", nullable = false)
    public Boolean getLeadByWoman() {
        return leadByWoman;
    }

    public void setLeadByWoman(Boolean leadByWoman) {
        this.leadByWoman = leadByWoman;
    }

    @Basic
    @Column(name = "ADDRESS", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "HOUSING_TYPE_ID", nullable = true)
    public Integer getHousingTypeId() {
        return housingTypeId;
    }

    public void setHousingTypeId(Integer housingTypeId) {
        this.housingTypeId = housingTypeId;
    }

    @Basic
    @Column(name = "HOUSEING_STATUS_ID", nullable = true)
    public Integer getHouseingStatusId() {
        return houseingStatusId;
    }

    public void setHouseingStatusId(Integer houseingStatusId) {
        this.houseingStatusId = houseingStatusId;
    }

    @Basic
    @Column(name = "MARRIED", nullable = false)
    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyEntity that = (FamilyEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(status, that.status) &&
                Objects.equals(familyType, that.familyType) &&
                Objects.equals(beneficaryId, that.beneficaryId) &&
                Objects.equals(providerId, that.providerId) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(dateOfRegisteration, that.dateOfRegisteration) &&
                Objects.equals(leadByWoman, that.leadByWoman) &&
                Objects.equals(address, that.address) &&
                Objects.equals(housingTypeId, that.housingTypeId) &&
                Objects.equals(houseingStatusId, that.houseingStatusId) &&
                Objects.equals(married, that.married);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, familyType, beneficaryId, providerId, origin, dateOfRegisteration, leadByWoman, address, housingTypeId, houseingStatusId, married);
    }

    @OneToMany(mappedBy = "familyByFamilyId")
    public List<DeliveredAidEntity> getDeliveredAidsById() {
        return deliveredAidsById;
    }

    public void setDeliveredAidsById(List<DeliveredAidEntity> deliveredAidsById) {
        this.deliveredAidsById = deliveredAidsById;
    }

    @ManyToOne
    @JoinColumn(name = "STATUS", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public FamilyStatusEntity getFamilyStatusByStatus() {
        return familyStatusByStatus;
    }

    public void setFamilyStatusByStatus(FamilyStatusEntity familyStatusByStatus) {
        this.familyStatusByStatus = familyStatusByStatus;
    }

    @ManyToOne
    @JoinColumn(name = "FAMILY_TYPE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public FamilyTypeEntity getFamilyTypeByFamilyType() {
        return familyTypeByFamilyType;
    }

    public void setFamilyTypeByFamilyType(FamilyTypeEntity familyTypeByFamilyType) {
        this.familyTypeByFamilyType = familyTypeByFamilyType;
    }

    @ManyToOne
    @JoinColumn(name = "ORIGIN", referencedColumnName = "ID", insertable = false, updatable = false)
    public RegionEntity getRegionByOrigin() {
        return regionByOrigin;
    }

    public void setRegionByOrigin(RegionEntity regionByOrigin) {
        this.regionByOrigin = regionByOrigin;
    }

    @ManyToOne
    @JoinColumn(name = "HOUSING_TYPE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    public HousingStatusEntity getHousingStatusByHousingTypeId() {
        return housingStatusByHousingTypeId;
    }

    public void setHousingStatusByHousingTypeId(HousingStatusEntity housingStatusByHousingTypeId) {
        this.housingStatusByHousingTypeId = housingStatusByHousingTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "HOUSING_TYPE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    public HousingTypeEntity getHousingTypeByHousingTypeId() {
        return housingTypeByHousingTypeId;
    }

    public void setHousingTypeByHousingTypeId(HousingTypeEntity housingTypeByHousingTypeId) {
        this.housingTypeByHousingTypeId = housingTypeByHousingTypeId;
    }

    @OneToMany(mappedBy = "familyByFamilyId")
    public List<MemberEntity> getMembersById() {
        return membersById;
    }

    public void setMembersById(List<MemberEntity> membersById) {
        this.membersById = membersById;
    }
}
