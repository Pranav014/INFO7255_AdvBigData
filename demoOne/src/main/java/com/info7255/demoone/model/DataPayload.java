package com.info7255.demoone.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPayload implements Serializable {
    @JsonProperty
    private PlanCostShares planCostShares;
    @JsonProperty
    private List<LinkedPlanService> linkedPlanServices;
    @JsonProperty
    private String _org;
    @JsonProperty
    private String objectId;
    @JsonProperty
    private String objectType;
    @JsonProperty
    private String planType;
    @JsonProperty
    private String creationDate;

    @Override
    public String toString() {
        return "DataPayload{" +
                "Plan Cost Shares = " + planCostShares +
                ", _org='" + _org + '\'' +
                ", objectId='" + objectId + '\'' +
                ", objectType='" + objectType + '\'' +
                ", planType='" + planType + '\'' +
                ", creationDate=" + creationDate +
                ", linkedPlanServices = " + linkedPlanServices +
                '}';
    }
}
