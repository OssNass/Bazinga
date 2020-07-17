package org.sarc.bazinga.database;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@IdClass(MemberPK.class)
public class Member {
    private Integer familyId;
    private Integer memeberId;
    private Date birthday;
    private Integer relationship;
    private Boolean specialNeeds;
    private Boolean hasChronicDisease;
    private Integer sex;
    private Integer age;
    private Family familyByFamilyId;

    @Id
    @Column(name = "family_id", nullable = false)
    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    @Id
    @Column(name = "memeber_id", nullable = false)
    public Integer getMemeberId() {
        return memeberId;
    }

    public void setMemeberId(Integer memeberId) {
        this.memeberId = memeberId;
    }

    @Basic
    @Column(name = "birthday", nullable = false)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "relationship", nullable = false)
    public Integer getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        this.relationship = relationship;
    }

    @Basic
    @Column(name = "special_needs", nullable = false)
    public Boolean getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(Boolean specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    @Basic
    @Column(name = "has_chronic_disease", nullable = false)
    public Boolean getHasChronicDisease() {
        return hasChronicDisease;
    }

    public void setHasChronicDisease(Boolean hasChronicDisease) {
        this.hasChronicDisease = hasChronicDisease;
    }

    @Basic
    @Column(name = "sex", nullable = false)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(familyId, member.familyId) &&
                Objects.equals(memeberId, member.memeberId) &&
                Objects.equals(birthday, member.birthday) &&
                Objects.equals(relationship, member.relationship) &&
                Objects.equals(specialNeeds, member.specialNeeds) &&
                Objects.equals(hasChronicDisease, member.hasChronicDisease) &&
                Objects.equals(sex, member.sex) &&
                Objects.equals(age, member.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(familyId, memeberId, birthday, relationship, specialNeeds, hasChronicDisease, sex, age);
    }

    @ManyToOne
    @JoinColumn(name = "family_id", referencedColumnName = "id", nullable = false)
    public Family getFamilyByFamilyId() {
        return familyByFamilyId;
    }

    public void setFamilyByFamilyId(Family familyByFamilyId) {
        this.familyByFamilyId = familyByFamilyId;
    }
}
