package com.info7255.demoone.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LinkedPlanService implements Serializable {

    private LinkedService linkedService;
    @JsonProperty("planserviceCostShares")
    private PlanServiceCostShares planServiceCostShares;
    private String _org;
    private String objectId;
    private String objectType;
}
