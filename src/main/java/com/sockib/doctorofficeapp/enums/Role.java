package com.sockib.doctorofficeapp.enums;

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
