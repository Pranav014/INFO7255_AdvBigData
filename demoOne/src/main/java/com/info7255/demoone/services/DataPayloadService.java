package com.info7255.demoone.services;

import com.info7255.demoone.repository.IDAO;
import com.info7255.demoone.model.DataPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DataPayloadService implements IDataPayloadService{
    @Autowired
    private IDAO dataPayloadDao;
    @Override
    @Transactional
    public boolean addData(DataPayload dataPayload) {
        return dataPayloadDao.addData(dataPayload);
    }

    @Override
    public List<DataPayload> getData() {
        return dataPayloadDao.getData();
    }

    @Override
    public boolean deleteData(String id) {
        return dataPayloadDao.deleteData(id);
    }

    @Override
    public DataPayload find(String id) {
        return dataPayloadDao.find(id);
    }

    @Override
    public void addEtag(String objectId, String md5Hash) {
        try{
            boolean res =  dataPayloadDao.addEtag(objectId, md5Hash);
            return;
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    @Override
    public String getETag(String id) {
        return dataPayloadDao.getETag(id);
    }


}
