package com.info7255.demoone.repository;

import com.info7255.demoone.model.DataPayload;

import java.util.List;


public interface IDAO {
    boolean addData(DataPayload dataPayload);
    List<DataPayload> getData();
    boolean deleteData(String id);
    DataPayload find(String id);
    boolean addEtag(String id, String etag);
    String getETag(String id);
}
