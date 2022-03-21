package com.ntels.portal.service;

import com.ntels.portal.dao.SampleMapper;
import com.ntels.portal.domain.Group;
import com.ntels.portal.domain.Manager;
import com.ntels.portal.domain.ManagerInfo;
import com.ntels.portal.domain.SearchOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WriteService {
    @Autowired
    private SampleMapper sampleMapper;

    public Boolean check(String inputId) throws Exception{
        List<ManagerInfo> sameId = sampleMapper.getManagerById(inputId);
        if (sameId.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }
    public void insertManager(ManagerInfo managerInfo) throws Exception{
        if (!managerInfo.getUSE_YN().equals(("Active"))){
            managerInfo.setUSE_YNtochar('N');
        }
        else {
            managerInfo.setUSE_YNtochar('Y');
        }
        sampleMapper.insertManager(managerInfo);
    }

    public void updateManager(ManagerInfo managerInfo) throws Exception{
        if (!managerInfo.getUSE_YN().equals(("Active"))){
            managerInfo.setUSE_YNtochar('N');
        }
        else {
            managerInfo.setUSE_YNtochar('Y');
        }
        sampleMapper.updateManager(managerInfo);
    }

    public ManagerInfo findManagerById(String user_id) throws Exception{
        return sampleMapper.getManagerById(user_id).get(0);
    }
}
