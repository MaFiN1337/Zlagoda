package com.example.entity;

import com.example.locale.Message;

public enum Role {
    CASHIER("cashier", Message.ROLE_CASHIER),
    MANAGER("manager", Message.ROLE_MANAGER);

    private String value;
    private String localizedValue;

    Role(String value) {
        this.value = value;
    }

    Role(String value, String localizedValue) {
        this.value = value;
        this.localizedValue = localizedValue;
    }

    public String getValue() {
        return value;
    }

    public String getLocalizedValue() {
        return localizedValue;
    }

    public static Role forValue(String value) {
        for (Role role : Role.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new RuntimeException("Role with such string value doesn't exist");
    }
}
