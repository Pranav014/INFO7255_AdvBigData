package com.info7255.demoone.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LinkedService implements Serializable {
    private String _org;
    private String objectId;
    private String objectType;
    private String name;

}
