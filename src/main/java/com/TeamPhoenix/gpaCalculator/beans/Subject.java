package com.TeamPhoenix.gpaCalculator.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class Subject extends MetaData implements Serializable {

    private Integer subjectId;
    private String subjectName;
    private Integer subjectBaseCategoryId;
    private String subjectCode;
    private String subjectType;
    private Integer subjectCredits;
    private Integer semesterNumber;
    private Result result;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSubjectBaseCategoryId() {
        return subjectBaseCategoryId;
    }

    public void setSubjectBaseCategoryId(Integer subjectBaseCategoryId) {
        this.subjectBaseCategoryId = subjectBaseCategoryId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public Integer getSubjectCredits() {
        return subjectCredits;
    }

    public void setSubjectCredits(Integer subjectCredits) {
        this.subjectCredits = subjectCredits;
    }

    public Integer getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(subjectId, subject.subjectId).append(subjectName, subject.subjectName).append(subjectBaseCategoryId, subject.subjectBaseCategoryId).append(subjectCode, subject.subjectCode).append(subjectType, subject.subjectType).append(subjectCredits, subject.subjectCredits).append(semesterNumber, subject.semesterNumber).append(result, subject.result).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(subjectId).append(subjectName).append(subjectBaseCategoryId).append(subjectCode).append(subjectType).append(subjectCredits).append(semesterNumber).append(result).toHashCode();
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", subjectBaseCategoryId=" + subjectBaseCategoryId +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectType='" + subjectType + '\'' +
                ", subjectCredits=" + subjectCredits +
                ", semesterNumber=" + semesterNumber +
                ", result=" + result +
                "} " + super.toString();
    }
}
