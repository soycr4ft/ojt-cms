package com.ojt.cms.user.enums;

import java.util.Arrays;

public enum ApprovedStatus {
	ALL("all"),
    APPROVED("approval"),
    REJECTED("reject"),
    WAITING("waiting");
	
    private final String code;

    ApprovedStatus(String code) {
        this.code = code;
    }

    public static ApprovedStatus from(String code) {
        return Arrays.stream(values())
                .filter(s -> s.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown code: " + code));
    }
}
