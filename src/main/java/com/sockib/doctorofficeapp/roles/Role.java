package com.sockib.doctorofficeapp.roles;

public enum Role {
    CLIENT("CLIENT"), DOCTOR("DOCTOR");

    private final String label;

    Role(String label) {
        this.label = label;
    }

    public String value() {
        return label;
    }
}
