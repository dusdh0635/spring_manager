package com.ntels.portal.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchOption {
	private String managerGroup;
	private String status;
	private String factorName;
	private String keyword;

	public SearchOption(){
		this.managerGroup = null;
		this.status = "";
		this.factorName ="";
		this.keyword = "";
	}

	public SearchOption(String managerGroup, String status, String factorName, String keyword){
		this.managerGroup = managerGroup;
		this.status = status;
		this.factorName =factorName;
		this.keyword = keyword;
	}
}
