package com.ojt.cms.user.dto;


import com.ojt.cms.user.enums.ApprovedStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedUserSearchDTO {
    private int page = 0;
    private int size = 10;
    
    private String keyword; //혹시라도 검색어
    private String type; //추후에 타입 enum화 고려하기
    private ApprovedStatus approved;
    //추후에
    //private String tab; 

}
