package com.example.springauthapi.roles;

public enum Roles {
    USER("USER"), ADMIN("ADMIN");

    private final String label;

    Roles(String label) {
        this.label = label;
    }

    public String value() {
        return label;
    }
}
