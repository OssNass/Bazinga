package org.sarc.asthma.database.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "MEMBER", schema = "PUBLIC", catalog = "HEARTATTACK")
@IdClass(MemberEntityPK.class)
public class MemberEntity {
    private int familyId;
    private int memeberId;
    private LocalDate birthday;
    private Integer relationship;
    private Boolean specialNeeds;
    private Boolean hasChronicDisease;
    private Integer sex;
    private Integer age;
    private String work;
    private Integer income;
    private String name;
    private String father;
    private String mother;
    private Integer statusId;
    private FamilyEntity familyByFamilyId;
    private RelationshipEntity relationshipByRelationship;
    private MemberStatusEntity memberStatusByStatusId;

    @Id
    @Column(name = "FAMILY_ID", nullable = false)
    public int getFamilyId() {
        return familyId;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    @Id
    @Column(name = "MEMEBER_ID", nullable = false)
    public int getMemeberId() {
        return memeberId;
    }

    public void setMemeberId(int memeberId) {
        this.memeberId = memeberId;
    }

    @Basic
    @Column(name = "BIRTHDAY", nullable = false)
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "RELATIONSHIP", nullable = false)
    public Integer getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        this.relationship = relationship;
    }

    @Basic
    @Column(name = "SPECIAL_NEEDS", nullable = false)
    public Boolean getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(Boolean specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    @Basic
    @Column(name = "HAS_CHRONIC_DISEASE", nullable = false)
    public Boolean getHasChronicDisease() {
        return hasChronicDisease;
    }

    public void setHasChronicDisease(Boolean hasChronicDisease) {
        this.hasChronicDisease = hasChronicDisease;
    }

    @Basic
    @Column(name = "SEX", nullable = false)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "AGE", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
    }

    @Basic
    @Column(name = "WORK", nullable = true, length = 100)
    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    @Basic
    @Column(name = "INCOME", nullable = true)
    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "FATHER", nullable = true, length = 100)
    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    @Basic
    @Column(name = "MOTHER", nullable = true, length = 100)
    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    @Basic
    @Column(name = "STATUS_ID", nullable = false)
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity that = (MemberEntity) o;
        return Objects.equals(familyId, that.familyId) &&
                Objects.equals(memeberId, that.memeberId) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(relationship, that.relationship) &&
                Objects.equals(specialNeeds, that.specialNeeds) &&
                Objects.equals(hasChronicDisease, that.hasChronicDisease) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(age, that.age) &&
                Objects.equals(work, that.work) &&
                Objects.equals(income, that.income) &&
                Objects.equals(name, that.name) &&
                Objects.equals(father, that.father) &&
                Objects.equals(mother, that.mother) &&
                Objects.equals(statusId, that.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, memeberId, birthday, relationship, specialNeeds, hasChronicDisease, sex, age, work, income, name, father, mother, statusId);
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
    @JoinColumn(name = "RELATIONSHIP", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public RelationshipEntity getRelationshipByRelationship() {
        return relationshipByRelationship;
    }

    public void setRelationshipByRelationship(RelationshipEntity relationshipByRelationship) {
        this.relationshipByRelationship = relationshipByRelationship;
    }

    @ManyToOne
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public MemberStatusEntity getMemberStatusByStatusId() {
        return memberStatusByStatusId;
    }

    public void setMemberStatusByStatusId(MemberStatusEntity memberStatusByStatusId) {
        this.memberStatusByStatusId = memberStatusByStatusId;
    }
}
