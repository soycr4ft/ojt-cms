package com.ojt.cms.skillcode;

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
public class SkillCodeDTO {
    private Long skillCodeId;
    private String skillName;
    private String keywords;
}
