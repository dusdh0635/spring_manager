package com.ntels.portal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ntels.portal.domain.UserCountParam;
import com.ntels.portal.domain.UserCountRespon;
import com.ntels.portal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    @Autowired GroupService groupService;

    @PostMapping(value = "/api/user-count/impl")
    public String userCountImpl(@RequestBody Map<String, List<UserCountParam>> param){
        System.out.println("in impl");
        String rightId;
        int rightCount;
        String result = "Success";

        try{
            for(UserCountParam i : param.get("data")) {
                rightId = groupService.groupByName(i.getUser_group_name()).get(0);
                if (!(rightId.equals(i.getUser_group_id()))){
                    result = "Error";
                    break;
                }
                rightCount = groupService.groupCount(rightId);
                if (!(rightCount == Integer.parseInt(i.getUser_count()))) {
                    result = "Error";
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @CrossOrigin("*")
    @PostMapping(value = "/api/user-count")
    public ResponseEntity<UserCountRespon> userCount(@RequestBody Map<String, List<UserCountParam>> param) {
        RestTemplate restTemplate = new RestTemplate();
        UserCountRespon userCountRespon = new UserCountRespon();

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/user-count/impl")
                .build()
                .toUri();

        ResponseEntity<String> countResult = null;
        UserCountRespon response = new UserCountRespon();

        try {
            countResult = restTemplate.postForEntity(uri, param, String.class);
            response.setMessage(countResult.getBody());
            if (response.getMessage().equals("Success")){
                response.setCode("0");
            }
            else{
                response.setCode("1");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok().body(response);
    }
}
