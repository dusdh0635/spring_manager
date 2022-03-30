package com.ntels.portal.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
public class SearchOption {
	private String managerGroup;
	private String status;
	private String factorName;
	private String keyword;

	public SearchOption(){
		this.managerGroup = "Select Item";
		this.status = "Select Item";
		this.factorName ="Login ID";
		this.keyword = "";
	}

	public SearchOption(String managerGroup, String status, String factorName, String keyword){
		this.managerGroup = managerGroup;
		this.status = status;
		this.factorName =factorName;
		this.keyword = keyword;
	}
}
