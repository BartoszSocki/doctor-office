package com.example.springauthapi.authorities;

public enum UserAuthorities {
    READ("READ"), WRITE("WRITE");

    private final String label;

    UserAuthorities(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
