package com.ntels.portal.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ManagerInfo {
    private String USER_ID;
    private String USER_NAME;
    private String USER_GROUP_ID;
    private String PASSWORD;
    private String PASSWORD2;
    private String DEPT_NAME;
    private String EMP_NO;
    private String TEL_NO;
    private String E_MAIL;
    private String USE_YN;
    private char USE_YNtochar;
}
