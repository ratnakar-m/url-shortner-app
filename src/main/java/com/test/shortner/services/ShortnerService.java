package com.test.shortner.services;

import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ShortnerService {

    @Autowired
    RedissonClient client;
    public ShortnerService(){}

    public String shortify(String longURL) throws NoSuchAlgorithmException, IOException {
        String shortURL = "";
        //connect to redis and check if exists

        /*Config config = Config.fromYAML(new File("redisson.yaml"));
        RedissonClient client = Redisson.create(config);*/

        /*Config config = new Config();
        config.useSingleServer()
                .setAddress("192.168.99.100:6379").setDatabase(0);
        RedissonClient client = Redisson.create(config);*/
        RMap<String, String> urlMap = client.getMap("urlMap");
        shortURL = getMD5Hash(longURL);
        String count = "";
        int countInt = 0;
        boolean keyExists = false;
            while(urlMap.containsKey(shortURL+count)){
            String redislongURL = urlMap.get(shortURL+count);
            if(!longURL.equals(redislongURL)){

                countInt++;
                count = String.valueOf(countInt);
            }
            else break;
        }

        urlMap.put(shortURL+count, longURL);

        return shortURL+count;
    }

    public String longify(String shortURL){
        String longURL = "";
        RMap<String, String> urlMap = client.getMap("urlMap");
        longURL = urlMap.get(shortURL);
        return longURL;
    }

     private String getMD5Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
       byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
       String output = DatatypeConverter.printHexBinary(digest);
        return output;
        //return "FB9874569BEBF9DF1D80FA19279F08E6";

    }
}
