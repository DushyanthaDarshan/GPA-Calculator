package com.TeamPhoenix.gpaCalculator.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class Course extends MetaData implements Serializable {

    private Integer courseId;
    private String courseName;
    private Integer subjectBaseCategoryId;
    private String courseCode;
    private String courseType;
    private Integer courseCredits;
    private Integer semesterNumber;
    private Result result;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getSubjectBaseCategoryId() {
        return subjectBaseCategoryId;
    }

    public void setSubjectBaseCategoryId(Integer subjectBaseCategoryId) {
        this.subjectBaseCategoryId = subjectBaseCategoryId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(Integer courseCredits) {
        this.courseCredits = courseCredits;
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

        Course course = (Course) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(courseId, course.courseId).append(courseName, course.courseName).append(subjectBaseCategoryId, course.subjectBaseCategoryId).append(courseCode, course.courseCode).append(courseType, course.courseType).append(courseCredits, course.courseCredits).append(semesterNumber, course.semesterNumber).append(result, course.result).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(courseId).append(courseName).append(subjectBaseCategoryId).append(courseCode).append(courseType).append(courseCredits).append(semesterNumber).append(result).toHashCode();
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", subjectBaseCategoryId=" + subjectBaseCategoryId +
                ", courseCode='" + courseCode + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseCredits=" + courseCredits +
                ", semesterNumber=" + semesterNumber +
                ", result=" + result +
                "} " + super.toString();
    }
}
