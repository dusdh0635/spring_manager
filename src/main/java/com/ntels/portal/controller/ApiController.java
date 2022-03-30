package com.ntels.portal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ntels.portal.domain.Group;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
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

    @ResponseBody
    @GetMapping(value = "")
    public String userCount() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/user-count/impl")
                .build()
                .toUri();

        ResponseEntity<String> countResult = null;
        String jsonList = null;

        try {
            List<Group> groups = groupService.groupList();
            ArrayList<UserCountParam> ParamList =  new ArrayList<>();
            Gson gson = new Gson();
            for(Group g :groups){
                UserCountParam tmp = new UserCountParam();
                tmp.setUser_group_name(g.getUSER_GROUP_NAME());
                tmp.setUser_group_id(g.getUSER_GROUP_ID());
                tmp.setUser_count(Integer.toString(groupService.groupCount(g.getUSER_GROUP_ID())));
                ParamList.add(tmp);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", ParamList);
            jsonList = gson.toJson(map);
            HttpEntity<String> request = new HttpEntity<String>(jsonList, headers);
            countResult = restTemplate.exchange( uri, HttpMethod.POST, request, String.class );

        }
        catch (Exception e) {
            System.out.println(e);
        }
        return countResult.getBody();
    }
}
