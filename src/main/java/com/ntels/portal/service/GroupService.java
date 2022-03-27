package com.ntels.portal.service;

import com.ntels.portal.dao.SampleMapper;
import com.ntels.portal.domain.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private SampleMapper sampleMapper;

    public List<String> groupAll(){
        return sampleMapper.getGroupAll();
    }
    public List<String> groupById(String group_id){ return sampleMapper.getGroupById(group_id);}
    public List<String> groupByName(String group_name){
        return sampleMapper.getGroupByName(group_name);
    }
    public int groupCount (String group_id){
        return sampleMapper.groupUserCount(group_id);
    }
}
