package com.info7255.demoone.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlanServiceCostShares implements Serializable {
    private int deductible;
    private String _org;
    private String objectId;
    private String objectType;
    private String name;
    private int copay;

}
