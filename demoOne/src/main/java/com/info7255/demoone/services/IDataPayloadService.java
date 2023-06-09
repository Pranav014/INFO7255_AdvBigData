package com.info7255.demoone.services;

import com.info7255.demoone.model.DataPayload;

import java.util.List;

public interface IDataPayloadService {
    boolean addData(DataPayload dataPayload);

    List<DataPayload> getData();

    boolean deleteData(String id);

    DataPayload find(String id);

    void addEtag(String objectId, String md5Hash);

    String getETag(String id);
}
