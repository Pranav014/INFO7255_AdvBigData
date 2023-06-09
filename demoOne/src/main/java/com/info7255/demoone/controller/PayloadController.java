package com.info7255.demoone.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.info7255.demoone.model.DataPayload;
import com.info7255.demoone.services.IDataPayloadService;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/plans")
public class PayloadController {

    @Autowired
    private IDataPayloadService dataPayloadService;


    @PostMapping("/add")
    public ResponseEntity<Object> addData(@RequestBody String requestStr){
        System.out.println("===============================REQUEST STRING=======================");
        System.out.println(requestStr);
        try     (
                InputStream jsonStream = PayloadController.class.getClassLoader().getResourceAsStream("static/schema.json");
                ) {
            JsonSchema jsonSchema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909).getSchema(jsonStream);
            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
            JsonNode jsonNode = objectMapper.readTree(requestStr);

            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
            StringBuilder errorsCombined = new StringBuilder();
            for (ValidationMessage error : errors){
                errorsCombined.append(error.toString() + "\n");
            }

            if (errors.size() > 0){
                throw new RuntimeException(" Please fix your json " + errorsCombined);
            }

            DataPayload dataPayload = objectMapper.readValue(requestStr, DataPayload.class);
            System.out.println(dataPayload);
            boolean result = false;
            String jsonObj = convertObjToJson(dataPayload);
            String md5Hash = calculateMD5(jsonObj);

            result = dataPayloadService.addData(dataPayload);
            dataPayloadService.addEtag(dataPayload.getObjectId(), md5Hash);
            if (!result){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Message","Could not add the element "));
            }
            return ResponseEntity.ok().eTag(md5Hash) //String.valueOf(MessageDigest.getInstance("MD5").digest(dataPayload.toString().getBytes()
                    .body(dataPayload);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//         catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
    }

    private String calculateMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String convertObjToJson(DataPayload dataPayload) {
        Gson gson = new Gson();

        String json = gson.toJson(dataPayload);

        return json;
    }

    @GetMapping("/get")
    public ResponseEntity<List<DataPayload>> getData(){

        return ResponseEntity.ok(dataPayloadService.getData());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getSinglePayload(@PathVariable String id, @RequestHeader(value = "if-Match",required = false) String ifMatch){
        DataPayload dataPayload = dataPayloadService.find(id);
        String currentETag = dataPayloadService.getETag(id);
        if (currentETag == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Message", "Not Found"));
        }
        if(ifMatch != null && currentETag != null && !ifMatch.equals(currentETag)){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(Collections.singletonMap("Message", "Data was Modified"));
        }

        return ResponseEntity.ok(dataPayload);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteData(@PathVariable String id){
        System.out.println("ID used to delete -> " + id);
        boolean result = dataPayloadService.deleteData(id);
        if (!result){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Message", "Could not find required element"));
        }
        return ResponseEntity.ok("Deleted Successfully");
    }

}
