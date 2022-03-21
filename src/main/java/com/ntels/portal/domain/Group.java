package com.ntels.portal.domain;

import lombok.Data;

@Data
public class Group {
    private String USER_GROUP_ID;
    private String USER_GROUP_NAME;
    private String USER_GROUP_LEVEL;
    private String DESCRIPTION;
    private long DISPLAY_ORDER;
}
