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
@CrossOrigin(origins = "*")
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

    @PostMapping(value = "checkId")
    @ResponseBody
    public Boolean checkId(String inputId){
        String message;
        try{
            checkIdfin = writeService.check(inputId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(checkIdfin);
        return checkIdfin;
    }

    @PostMapping(value = "new")
    public String insert(ManagerInfo managerInfo, HttpServletRequest request, HttpServletResponse response) {
        try{
            checkIdfin = writeService.check(managerInfo.getUSER_ID());
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();

            if(checkIdfin && !(managerInfo.getUSER_ID().equals(""))) {
                if(managerInfo.getPASSWORD()!="" && managerInfo.getPASSWORD().equals(managerInfo.getPASSWORD2())) {
                    if(!(managerInfo.getUSER_GROUP_ID().equals("ManagerGroup"))) {
                        List<String> tmp = groupService.groupByName(managerInfo.getUSER_GROUP_ID());
                        if (!tmp.isEmpty()) {
                            managerInfo.setUSER_GROUP_ID(tmp.get(0));
                        } else {
                            managerInfo.setUSER_GROUP_ID(null);
                        }
                        writeService.insertManager(managerInfo);
                    }
                    else {
                        out.println("<script>alert('그룹을 설정해주세요.');history.go(-1);</script>");
                        out.flush();
                    }
                }
                else{
                    out.println("<script>alert('패스워드가 입력되지 않았거나 일치하지 않습니다.');history.go(-1);</script>");
                    out.flush();
                }
            }
            else {
                out.println("<script>alert('아이디가 입력되지 않았거나 중복 아이디 입니다.');history.go(-1);</script>");
                out.flush();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/portal/manage/managerList";
    }
}
