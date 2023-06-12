package com.info7255.demoone.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info7255.demoone.controller.PayloadController;
import com.info7255.demoone.exceptions.JsonFormatException;
import com.networknt.schema.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class ValidationService implements IValidationService{

    @Override
    public JsonNode validateJSON(String json, String schemaPath){
        JsonNode jsonNode;
        try (InputStream schemasAsStream = PayloadController.class.getClassLoader().getResourceAsStream(schemaPath)) {
            JsonSchema jsonSchema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemasAsStream);

            //READING AND PARSING JSON DATA FROM REQUEST STRING
            ObjectMapper objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(json);

            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
            StringBuilder errorsCombined = new StringBuilder();

            for (ValidationMessage error : errors) {
                log.error("Validation Error: {}", error);
                errorsCombined.append(error.toString()).append("\n");
            }
            if (errors.size() > 0)
                throw new JsonFormatException("Improper json format: \n" + errorsCombined);
        } catch (JsonProcessingException e) {
            throw new JsonFormatException("Failed to parse the body into json");
        }
        catch (IOException e) {
            throw new JsonSchemaException("Error occurred while fetching the schema from resource");
        }
        return jsonNode;
    }
}
