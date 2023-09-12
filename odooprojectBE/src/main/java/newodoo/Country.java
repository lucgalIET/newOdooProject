package newodoo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum Country {
    ITA("ITA"),
    ESP("ESP"),
    GLOBAL("GLOBAL");


    private final String displayName;

    Country(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}

