package com.ntels.portal.controller;

import com.ntels.portal.domain.Group;
import com.ntels.portal.domain.Manager;
import com.ntels.portal.domain.ManagerInfo;
import com.ntels.portal.service.GroupService;
import com.ntels.portal.service.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/portal/manage/managerUpdate")
public class ManagerUpdateController {

    @Autowired
    private WriteService writeService;
    @Autowired
    private GroupService groupService;
    boolean checkIdfin = false;
    ManagerInfo this_manager;
    List<String> group_list;
    String this_group;

    @GetMapping(value = "{id}")
    public ModelAndView showEdit(@PathVariable String id) {
        try {
            this_manager = writeService.findManagerById(id);
            group_list =  groupService.groupAll();
            this_group = groupService.groupById(this_manager.getUSER_GROUP_ID()).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("portal/manage/managerUpdate");
        mv.addObject("this_manager", this_manager);
        mv.addObject("this_group", this_group);
        mv.addObject("group_list", group_list);
        return mv;
    }
    @PostMapping(value = "edit")
    public String edit(ManagerInfo managerInfo) {
        try{
            List<String> tmp = groupService.groupByName(managerInfo.getUSER_GROUP_ID());
            if (!tmp.isEmpty()){
                managerInfo.setUSER_GROUP_ID(tmp.get(0));
            }
            else{
                managerInfo.setUSER_GROUP_ID(null);
            }
            managerInfo.setUSER_ID(this_manager.getUSER_ID());
            writeService.updateManager(managerInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/portal/manage/managerList";
    }
}
