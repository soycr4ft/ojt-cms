package com.ojt.cms.user.enums;

import java.util.Arrays;

public enum AuthRole {
	MEMBER("member"), 
	ADMIN("admin"), 
	MASTER("master"), 
	SUSPENDED("suspended"), 
	REJECTED("rejected");
	
    private final String code;
    AuthRole(String code) {
    	this.code=code;
    }
    
    public static AuthRole from(String code) {
        return Arrays.stream(values())
                .filter(s -> s.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown code: " + code));
    }
}
