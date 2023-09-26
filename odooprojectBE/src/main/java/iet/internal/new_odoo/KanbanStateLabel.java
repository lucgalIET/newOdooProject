package iet.internal.new_odoo;

public enum KanbanStateLabel {
    todo("todo"),
    in_progress("in_progress"),
    completed("completed"),
    canceled("canceled"),
    blocked("blocked");
    private final String displayName;

    KanbanStateLabel(String displayName) {
        this.displayName = displayName;
    }
}
