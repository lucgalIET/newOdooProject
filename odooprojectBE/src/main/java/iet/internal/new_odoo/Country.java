package iet.internal.new_odoo;

import lombok.Getter;

@Getter
public enum Country {
    ITA("ITA"),
    ESP("ESP"),
    GLOBAL("GLOBAL");


    private final String displayName;

    Country(String displayName) {
        this.displayName = displayName;
    }
}

