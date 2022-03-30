package com.ntels.portal.dao;

import com.ntels.portal.domain.Group;
import com.ntels.portal.domain.Manager;
import com.ntels.portal.domain.ManagerInfo;
import com.ntels.portal.domain.SearchOption;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface SampleMapper {
	public List<Manager> getSearchList(Map map);
	public List<ManagerInfo> getManagerById(String user_id);
	public List<String> getGroupAll();
	public List<Group> getGroupList();
	public List<String> getGroupById(String group_id);
	public List<String> getGroupByName(String group_name);
	public void insertManager(ManagerInfo managerInfo);
	public void updateManager(ManagerInfo managerInfo);
	public int groupUserCount(String group_id);
}
