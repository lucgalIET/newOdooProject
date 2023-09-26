package iet.internal.new_odoo;
public enum ProjectPhase {

    //'analysis', 'specifications', 'design', 'development', 'testing'
    analysis("analysis"),
    specifications("specifications"),
    design("design"),
    development("development"),
    testing("testing");


    private final String displayName;

    ProjectPhase(String displayName) {
        this.displayName = displayName;
    }
}