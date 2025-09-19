package com.ojt.cms.license;

import java.time.LocalDate;

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
public class LicenseDTO {
    private Long licenseId;
    private Boolean kosa;
    private String licenseName;
    private LocalDate acDate;
    private Long userId;
}
