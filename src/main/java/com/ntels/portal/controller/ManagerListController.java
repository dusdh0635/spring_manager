package com.ntels.portal.controller;

import javax.servlet.http.HttpServletRequest;

import com.ntels.portal.domain.Group;
import com.ntels.portal.domain.Manager;
import com.ntels.portal.domain.SearchOption;
import com.ntels.portal.service.GroupService;
import com.ntels.portal.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/portal/manage/managerList")
public class ManagerListController {

	@Autowired
	private ListService listService;
	@Autowired
	private GroupService groupService;

	List<Manager> currentList = null;
	List<String> group_list =  null;
	int contentCount;
	int firstPage;
	int lastPage;
	int start;
	int end;
	
	@GetMapping(value = "")
	public ModelAndView mainList() {
		try {
			SearchOption option = new SearchOption();
			start = 0;
			firstPage = 1;
			currentList = listService.getSearchList(option);
			group_list =  groupService.groupAll();
			contentCount = currentList.size();
			end = start + 9;
			end = (end <= contentCount ? end : contentCount);
			lastPage = (contentCount == 0 ? 1 : ((contentCount+9)/10));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", currentList);
		mv.addObject("total", contentCount);
		mv.addObject("group_list", group_list);
		mv.addObject("start", start);
		mv.addObject("end", end);
		mv.addObject("firstPage", firstPage);
		mv.addObject("lastPage", lastPage);
		mv.setViewName("portal/manage/managerList");
		mv.addObject("nowPage", 1);
		return mv;
	}

	@PostMapping(value = "")
	public ModelAndView List(SearchOption searchOption) {
		try {
			List<Group> tmp = groupService.groupByName(searchOption.getManagerGroup());
			if (!tmp.isEmpty()){
				searchOption.setManagerGroup(tmp.get(0).getUSER_GROUP_ID());
			}
			else{
				searchOption.setManagerGroup(null);
			}
			currentList = listService.getSearchList(searchOption);
			start = 0 ;
			firstPage = 1;
			contentCount = currentList.size();
			end = start + 9;
			end = (end <= contentCount ? end : contentCount);
			lastPage = (contentCount == 0 ? 1 : ((contentCount+9)/10));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", currentList);
		mv.addObject("total", contentCount);
		mv.addObject("group_list", group_list);
		mv.addObject("start", start);
		mv.addObject("end", end);
		mv.addObject("firstPage", firstPage);
		mv.addObject("lastPage", lastPage);
		mv.setViewName("portal/manage/managerList");
		mv.addObject("nowPage", 1);
		return mv;
	}

	@RequestMapping(value = "{pagenum}")
	public ModelAndView indexList(@PathVariable String pagenum) {
		try {
			start = (Integer.parseInt(pagenum)-1) * 10;
			end = start + 9;
			end = (end <= contentCount ? end : contentCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", currentList);
		mv.addObject("total", contentCount);
		mv.addObject("group_list", group_list);
		mv.addObject("start", start);
		mv.addObject("end", end);
		mv.addObject("firstPage", firstPage);
		mv.addObject("lastPage", lastPage);
		mv.addObject("nowPage", pagenum);
		mv.setViewName("portal/manage/managerList");
		return mv;
	}

}
