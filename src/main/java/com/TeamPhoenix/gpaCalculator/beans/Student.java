package com.TeamPhoenix.gpaCalculator.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Serializable {

    private String indexNumber;
    private String batch;
    private String stream;
    private String combination;
    private String degree;

    private List<Gpa> gpaList;
    private List<Course> courseList;

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<Gpa> getGpaList() {
        if (gpaList == null) {
            gpaList = new ArrayList<>();
        }
        return gpaList;
    }

    public void setGpaList(List<Gpa> gpaList) {
        this.gpaList = gpaList;
    }

    public List<Course> getSubjectList() {
        if (courseList == null) {
            courseList = new ArrayList<>();
        }
        return courseList;
    }

    public void setSubjectList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(indexNumber, student.indexNumber).append(batch, student.batch).append(stream, student.stream).append(combination, student.combination).append(degree, student.degree).append(gpaList, student.gpaList).append(courseList, student.courseList).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(indexNumber).append(batch).append(stream).append(combination).append(degree).append(gpaList).append(courseList).toHashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "indexNumber='" + indexNumber + '\'' +
                ", batch='" + batch + '\'' +
                ", stream='" + stream + '\'' +
                ", combination='" + combination + '\'' +
                ", degree='" + degree + '\'' +
                ", gpaList=" + gpaList +
                ", courseList=" + courseList +
                "} " + super.toString();
    }
}
