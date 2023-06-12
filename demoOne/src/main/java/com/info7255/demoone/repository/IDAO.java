package com.info7255.demoone.repository;

import com.info7255.demoone.model.DataPayload;

import java.util.List;


public interface IDAO {
    boolean addData(DataPayload dataPayload);
    List<DataPayload> getData();
    void deleteData(String id);
    Object find(String id);
    boolean addEtag(String id, String etag);
    String getETag(String id);

    void save(String objectId, String toString);
}
