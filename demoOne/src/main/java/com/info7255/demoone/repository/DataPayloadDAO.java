package com.info7255.demoone.repository;

import com.info7255.demoone.model.DataPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataPayloadDAO implements IDAO {
    List<DataPayload> dataPayloadList;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "DataPayload";
    private static final String ETAGKEY = "ETAG";

    public DataPayloadDAO(){
        dataPayloadList = new ArrayList<>();
    }
    @Override
    public boolean addData(DataPayload dataPayload) {
        try{
            redisTemplate.opsForHash().put(KEY, dataPayload.getObjectId(), dataPayload);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
//        dataPayloadList.add(dataPayload);
    }



    @Override
    public List<DataPayload> getData() {
        List<DataPayload> list;
        list = redisTemplate.opsForHash().values(KEY);
        return list;
    }

    @Override
    public boolean deleteData(String id) {
        Long l = redisTemplate.opsForHash().delete(KEY, id);
        System.out.println("LONG L = " + l);
        return l == 1 ? true : false;

    }

    @Override
    public DataPayload find(String id) {
        return (DataPayload) redisTemplate.opsForHash().get(KEY, id);
    }


    @Override
    public boolean addEtag(String id, String etag) {
        try{
            redisTemplate.opsForHash().put(ETAGKEY, id, etag);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getETag(String id) {
        try {
            return redisTemplate.opsForHash().get(ETAGKEY,id).toString();
        }catch (Exception e){
            return null;
        }

    }


}
