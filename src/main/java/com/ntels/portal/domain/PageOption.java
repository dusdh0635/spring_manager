package com.ntels.portal.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PageOption {
    private int contentCount;
    private int firstPage;
    private int lastPage;
    private int start;
    private int end;
    private int nowPage;


    public void setPageOption(int contentCount){
        this.contentCount = contentCount;
        start = 0;
        firstPage = 1;
        end = start + 9;
        nowPage = 1;
        end = (end <= contentCount ? end : contentCount);
        lastPage = (contentCount == 0 ? 1 : ((contentCount+9)/10));
        nowPage = 1;
    }

    public void setPageNumber(int pagenum){
        start = (pagenum -1) * 10;
        end = start + 9;
        end = (end <= contentCount ? end : contentCount);
        nowPage = pagenum;
    }
}
