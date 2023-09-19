package newodoo;

public enum ProjectType {
    Task("Task"),
    TandM("TandM"),
    TaskAndTandM("TaskAndTandM"),

    Internal("Internal");


    private final String displayName;

    ProjectType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}

