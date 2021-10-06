package com.TeamPhoenix.gpaCalculator.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Timestamp;

public class MetaData {

    private String status;
    private Timestamp createdTs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Timestamp createdTs) {
        this.createdTs = createdTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MetaData metaData = (MetaData) o;

        return new EqualsBuilder().append(status, metaData.status).append(createdTs, metaData.createdTs).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(status).append(createdTs).toHashCode();
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "status='" + status + '\'' +
                ", createdTs=" + createdTs +
                '}';
    }
}
