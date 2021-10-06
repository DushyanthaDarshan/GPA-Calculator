package com.TeamPhoenix.gpaCalculator.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class Gpa extends MetaData implements Serializable {

    private Long gpaId;
    private Double gpa;
    private String gpaType;

    public Long getGpaId() {
        return gpaId;
    }

    public void setGpaId(Long gpaId) {
        this.gpaId = gpaId;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getGpaType() {
        return gpaType;
    }

    public void setGpaType(String gpaType) {
        this.gpaType = gpaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Gpa gpa1 = (Gpa) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(gpaId, gpa1.gpaId).append(gpa, gpa1.gpa).append(gpaType, gpa1.gpaType).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(gpaId).append(gpa).append(gpaType).toHashCode();
    }

    @Override
    public String toString() {
        return "Gpa{" +
                "gpaId=" + gpaId +
                ", gpa=" + gpa +
                ", gpaType='" + gpaType + '\'' +
                "} " + super.toString();
    }
}
