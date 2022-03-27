package com.ntels.portal.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserCountParam {
    private String user_group_name;
    private String user_group_id;
    private String user_count;
}
