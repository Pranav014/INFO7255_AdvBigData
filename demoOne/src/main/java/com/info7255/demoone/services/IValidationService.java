package com.info7255.demoone.services;

import com.fasterxml.jackson.databind.JsonNode;

public interface IValidationService {
    JsonNode validateJSON(String json, String schemaPath);
}
