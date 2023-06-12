package com.info7255.demoone.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@Slf4j
public class EncryptionServiceImp implements EncryptionService {

    @Override
    public String encrypt(String data) {
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }
}
