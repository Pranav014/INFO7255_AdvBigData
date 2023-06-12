package com.info7255.demoone.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.info7255.demoone.model.DataPayload;

import java.util.Map;

public interface IDataPayloadService {
    Map<String, Object> addData(JsonNode createRequest);

    void deleteData(String id);

    String getPlan(String id, String requestETag);
}
