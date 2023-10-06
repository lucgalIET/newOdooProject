package newodoo;

public enum ProjectType {
    T("T"),
    M("M"),
    N("N"),

    I("I");


    private final String displayName;

    ProjectType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}

