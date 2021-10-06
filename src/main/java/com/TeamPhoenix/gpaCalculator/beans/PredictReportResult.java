package com.TeamPhoenix.gpaCalculator.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class PredictReportResult implements Serializable {

    private String subjectCode;
    private String subjectName;
    private Integer subjectCredit;
    private String resultGrade;
    private Double gpv;
    private boolean isNewSem;

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getResultGrade() {
        return resultGrade;
    }

    public void setResultGrade(String resultGrade) {
        this.resultGrade = resultGrade;
    }

    public Double getGpv() {
        return gpv;
    }

    public void setGpv(Double gpv) {
        this.gpv = gpv;
    }

    public Integer getSubjectCredit() {
        return subjectCredit;
    }

    public void setSubjectCredit(Integer subjectCredit) {
        this.subjectCredit = subjectCredit;
    }

    public boolean isNewSem() {
        return isNewSem;
    }

    public void setNewSem(boolean newSem) {
        isNewSem = newSem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PredictReportResult that = (PredictReportResult) o;

        return new EqualsBuilder().append(isNewSem, that.isNewSem).append(subjectCode, that.subjectCode).append(subjectName, that.subjectName).append(subjectCredit, that.subjectCredit).append(resultGrade, that.resultGrade).append(gpv, that.gpv).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(subjectCode).append(subjectName).append(subjectCredit).append(resultGrade).append(gpv).append(isNewSem).toHashCode();
    }

    @Override
    public String toString() {
        return "PredictReportResult{" +
                "subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectCredit=" + subjectCredit +
                ", resultGrade='" + resultGrade + '\'' +
                ", gpv=" + gpv +
                ", isNewSem=" + isNewSem +
                '}';
    }
}
