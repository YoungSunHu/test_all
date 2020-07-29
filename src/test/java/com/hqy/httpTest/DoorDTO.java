package com.hqy.httpTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoorDTO {
    private String startTime;
    private String endTime;
    private Integer eventType;
    private String personName;
    private String[] personIds;
    private String doorName;
    private String doorIndexCodes;
    private String doorRegionIndexCode;
    private String sort;
    private String order;
    private int pageNo;
    private int pageSize;
}
