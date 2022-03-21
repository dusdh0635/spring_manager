package com.ntels.portal.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Manager {
	private String USER_ID;
	private String USER_NAME;
	private String USER_GROUP_NAME;
	private String TEL_NO;
	private String E_MAIL = "EXAMPLE@email.com";
}
