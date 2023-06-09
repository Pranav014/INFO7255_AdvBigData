package com.info7255.demoone.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanCostShares implements Serializable {
    @JsonProperty
    private int deductible;
    @JsonProperty
    private String _org;
    @JsonProperty
    private int copay;
    @JsonProperty
    private String objectId;
    @JsonProperty
    private String objectType;

    @Override
    public String toString() {
        return " PlanCostShares{" +
                "deductibles=" + deductible +
                ", _org='" + _org + '\'' +
                ", copay=" + copay +
                ", objectId='" + objectId + '\'' +
                ", objectType='" + objectType + '\'' +
                '}';
    }
}
