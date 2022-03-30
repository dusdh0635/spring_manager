package com.ntels.portal.service;

import com.ntels.portal.domain.Manager;
import com.ntels.portal.domain.SearchOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntels.portal.dao.SampleMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ListService {
	@Autowired
	private SampleMapper sampleMapper;

	public List<Manager> getSearchList(SearchOption searchOption) throws Exception {
		int status = -1;
		String factor = null;
		String keyword = searchOption.getKeyword();
		String group = searchOption.getManagerGroup();

		if (searchOption.getStatus().equals("Active")) {
			status = 1;
		}
		else if (searchOption.getStatus().equals("Inactive")){
			status = 0;
		}

		if (searchOption.getManagerGroup().equals("Select Item")) {
			group = null;
		}

		if (!keyword.equals("")) {
			switch (searchOption.getFactorName()) {
				case "Login ID":
					factor = "USER_ID";
					break;
				case "User Name":
					factor = "USER_NAME";
					break;
				case "Employee No.":
					factor = "EMP_NO";
					break;
				default:
					break;
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("factor", factor);
		map.put("keyword", keyword);
		map.put("group", group);

		return sampleMapper.getSearchList(map);
	}
}
