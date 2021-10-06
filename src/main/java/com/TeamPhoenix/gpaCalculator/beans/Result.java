package com.TeamPhoenix.gpaCalculator.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class Result extends MetaData implements Serializable {

    private Long resultId;
    private String resultGrade;
    private Double resultMark;

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public String getResultGrade() {
        return resultGrade;
    }

    public void setResultGrade(String resultGrade) {
        this.resultGrade = resultGrade;
    }

    public Double getResultMark() {
        return resultMark;
    }

    public void setResultMark(Double resultMark) {
        this.resultMark = resultMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(resultId, result.resultId).append(resultGrade, result.resultGrade).append(resultMark, result.resultMark).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(resultId).append(resultGrade).append(resultMark).toHashCode();
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultId=" + resultId +
                ", resultGrade='" + resultGrade + '\'' +
                ", resultMark=" + resultMark +
                "} " + super.toString();
    }
}
