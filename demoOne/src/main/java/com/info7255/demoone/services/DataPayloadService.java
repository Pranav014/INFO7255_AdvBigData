package com.info7255.demoone.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info7255.demoone.exceptions.PlanNotUpdatedException;
import com.info7255.demoone.repository.IDAO;
import com.info7255.demoone.model.DataPayload;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataPayloadService implements IDataPayloadService{
    private final IDAO dataPayloadDao;

    private final EncryptionService encryptionService;

    public DataPayloadService(IDAO dataPayloadDao, EncryptionService encryptionService) {
        this.dataPayloadDao = dataPayloadDao;
        this.encryptionService = encryptionService;
    }

    @Override
    public Map<String, Object> addData(JsonNode createRequest) {
        String objectId = createRequest.get("objectId").textValue();
        dataPayloadDao.save(objectId, createRequest.toString());
        Map<String, Object> response = new HashMap<>();
        response.put("objectId", objectId);
        return response;
    }


    @Override
    public void deleteData(String id) {
        dataPayloadDao.deleteData(id);
    }

    @Override
    public String getPlan(String objectId, String requestETag) {
        Object plan =  dataPayloadDao.find(objectId);

        JsonNode jsonNode;
        ObjectMapper om = new ObjectMapper();
        try {
            jsonNode = om.readTree(plan.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if(encryptionService.encrypt(jsonNode.toString()).equals(requestETag))
            throw new PlanNotUpdatedException(objectId);
        return plan.toString();
    }


}
