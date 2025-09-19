package com.ojt.cms.userskill;

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
public class UserSkillDTO {
    private Long userSkillId;
    private Long skillCodeId;
    private Long userId;
}
