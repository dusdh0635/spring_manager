package com.ntels.portal.controller;

import com.ntels.portal.domain.Group;
import com.ntels.portal.domain.Manager;
import com.ntels.portal.domain.ManagerInfo;
import com.ntels.portal.service.GroupService;
import com.ntels.portal.service.ListService;
import com.ntels.portal.service.WriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/portal/manage/managerWrite")
public class ManagerWriterController {

    @Autowired
    private WriteService writeService;
    @Autowired
    private GroupService groupService;

    boolean checkIdfin = false;

    @GetMapping(value = "")
    public ModelAndView showWriteForm(){
        ModelAndView mv = new ModelAndView();
        List<String> group_list =  groupService.groupAll();
        mv.addObject("group_list", group_list);
        mv.setViewName("portal/manage/managerWrite");
        return mv;
    }

    public String checkId(String inputId){
        try{
            checkIdfin = writeService.check(inputId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return inputId;
    }

    @PostMapping(value = "new")
    public String insert(ManagerInfo managerInfo, Model model, HttpServletRequest request, HttpServletResponse response) {
        try{
            checkIdfin = writeService.check(managerInfo.getUSER_ID());
            if (checkIdfin){
                if(managerInfo.getPASSWORD().equals(managerInfo.getPASSWORD2())) {
                    try {
                        List<Group> tmp = groupService.groupByName(managerInfo.getUSER_GROUP_ID());
                        if (!tmp.isEmpty()) {
                            managerInfo.setUSER_GROUP_ID(tmp.get(0).getUSER_GROUP_ID());
                        } else {
                            managerInfo.setUSER_GROUP_ID(null);
                        }
                        writeService.insertManager(managerInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    response.setContentType("text/html; charset=utf-8");
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('패스워드가 일치하지 않습니다.');history.go(-1);</script>");
                    out.flush();
                }
            }
            else {
                response.setContentType("text/html; charset=utf-8");
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('중복 아이디 입니다.');history.go(-1);</script>");
                out.flush();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/portal/manage/managerList";
    }
}
