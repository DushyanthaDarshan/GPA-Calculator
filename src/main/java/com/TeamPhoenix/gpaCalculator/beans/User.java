package com.TeamPhoenix.gpaCalculator.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User extends MetaData implements Serializable {

    private Long userId;
    private String username;
    private String indexNumber;
    private String name;
    private String password;
    private String batch;
    private String stream;
    private String combination;
    private String degree;

    private List<Gpa> gpaList;
    private List<Course> courseList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        User user = (User) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(userId, user.userId).append(username, user.username).append(indexNumber, user.indexNumber).append(name, user.name).append(password, user.password).append(batch, user.batch).append(stream, user.stream).append(combination, user.combination).append(degree, user.degree).append(gpaList, user.gpaList).append(courseList, user.courseList).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(userId).append(username).append(indexNumber).append(name).append(password).append(batch).append(stream).append(combination).append(degree).append(gpaList).append(courseList).toHashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", indexNumber='" + indexNumber + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", batch='" + batch + '\'' +
                ", stream='" + stream + '\'' +
                ", combination='" + combination + '\'' +
                ", degree='" + degree + '\'' +
                ", gpaList=" + gpaList +
                ", subjectList=" + courseList +
                "} " + super.toString();
    }
}
