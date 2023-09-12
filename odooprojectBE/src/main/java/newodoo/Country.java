package newodoo;

public enum Country {
    ITA("Italy"),
    SPA("Spain"),
    GLOBAL("Global");

    private final String displayName;

    Country(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

