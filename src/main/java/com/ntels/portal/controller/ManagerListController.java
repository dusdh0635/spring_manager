package com.ntels.portal.controller;

import javax.servlet.http.HttpServletRequest;

import com.ntels.portal.domain.Group;
import com.ntels.portal.domain.Manager;
import com.ntels.portal.domain.PageOption;
import com.ntels.portal.domain.SearchOption;
import com.ntels.portal.service.GroupService;
import com.ntels.portal.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/portal/manage/managerList")
public class ManagerListController {

	@Autowired
	private ListService listService;
	@Autowired
	private GroupService groupService;
	private List<Manager> currentList;
	private List<String> groupList;
	@Autowired
	private PageOption pageOption;
	@Autowired
	private SearchOption searchOption;

	
	@GetMapping(value = "")
	public ModelAndView mainList() {
		searchOption = new SearchOption();
		try {
			currentList = listService.getSearchList(searchOption);
			groupList =  groupService.groupAll();
			pageOption.setPageOption(currentList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("searchOption", searchOption);
		mv.addObject("list", currentList);
		mv.addObject("total", currentList.size());
		mv.addObject("groupList", groupList);
		mv.addObject("pageOption", pageOption);
		mv.setViewName("portal/manage/managerList");
		mv.addObject("nowPage", 1);
		return mv;
	}

	@PostMapping(value = "search")
	public ModelAndView searchList(SearchOption searchOption) {
		this.searchOption = new SearchOption();
		this.searchOption.setManagerGroup(searchOption.getManagerGroup());
		this.searchOption.setFactorName(searchOption.getFactorName());
		this.searchOption.setStatus(searchOption.getStatus());
		this.searchOption.setKeyword(searchOption.getKeyword());
		System.out.println(this.searchOption.toString());

		try {
			if(!searchOption.getManagerGroup().equals("Select Item")) {
				List<String> tmp = groupService.groupByName(searchOption.getManagerGroup());
				if (!tmp.isEmpty()) {
					searchOption.setManagerGroup(tmp.get(0));
				} else {
					searchOption.setManagerGroup(null);
				}
			}
			currentList = listService.getSearchList(searchOption);
			pageOption.setPageOption(currentList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("searchOption", this.searchOption);
		mv.addObject("list", currentList);
		mv.addObject("total", currentList.size());
		mv.addObject("groupList", groupList);
		mv.addObject("pageOption", pageOption);
		mv.setViewName("portal/manage/managerList");
		return mv;
	}

	@GetMapping(value = "{pagenum}")
	public ModelAndView indexList(@PathVariable String pagenum) {
		try {
			pageOption.setPageNumber(Integer.parseInt(pagenum));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("searchOption", this.searchOption);
		mv.addObject("list", currentList);
		mv.addObject("total", currentList.size());
		mv.addObject("groupList", groupList);
		mv.addObject("pageOption", pageOption);
		mv.setViewName("portal/manage/managerList");
		return mv;
	}

}
