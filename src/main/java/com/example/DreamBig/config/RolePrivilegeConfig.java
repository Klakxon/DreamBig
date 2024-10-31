package com.example.DreamBig.config;

import java.util.Set;

public class RolePrivilegeConfig {

    public static Set<String> getUserPrivileges() {
        return Set.of("visitSession");
    }

    public static Set<String> getTrainerPrivileges() {
        return Set.of("visitSession", "arrangeSession");
    }

    public static Set<String> getAdminPrivileges() {
        return Set.of("visitSession", "arrangeSession", "accessSecretData");
    }
}