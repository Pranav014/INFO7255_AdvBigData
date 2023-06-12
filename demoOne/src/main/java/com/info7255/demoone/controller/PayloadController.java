package com.info7255.demoone.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.info7255.demoone.model.DataPayload;
import com.info7255.demoone.services.EncryptionService;
import com.info7255.demoone.services.IDataPayloadService;
import com.info7255.demoone.services.IValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plans")
public class PayloadController {

    public static final String SCHEMA_PATH = "static/schema.json";
    private final IDataPayloadService dataPayloadService;

    private final IValidationService validationService;

    private final EncryptionService encryptionService;

    public PayloadController(IDataPayloadService dataPayloadService, IValidationService validationService, EncryptionService encryptionService) {
        this.dataPayloadService = dataPayloadService;
        this.validationService = validationService;
        this.encryptionService = encryptionService;
    }


    @PostMapping
    public ResponseEntity<Object> addData(@RequestBody String requestStr){
        JsonNode requestJson = validationService.validateJSON(requestStr, SCHEMA_PATH);
        Map<String, Object> response = dataPayloadService.addData(requestJson);
        String eTag = encryptionService.encrypt(requestJson.toString());

        return ResponseEntity.status(HttpStatus.CREATED).eTag(eTag).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlan(@PathVariable String id, @RequestHeader(value = "if-None-Match",required = false) String requestETag){
        String res = dataPayloadService.getPlan(id, requestETag);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteData(@PathVariable String id){
        dataPayloadService.deleteData(id);
    }

}
