package com.ojt.cms.detail;

import com.ojt.cms.detail.enums.UserProjStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DetailDTO {
    private Long detailId;
    private String address;
    private String profile;
    private UserProjStatus status;
    private Long userId;
}
