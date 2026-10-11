package seedu.address.model.student;

/**
 * Defines the valid tutorial-week range shared by weekly student records.
 */
public final class TutorialWeek {

    public static final int MIN_WEEK = 3;
    public static final int MAX_WEEK = 13;
    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial week must be between " + MIN_WEEK + " and " + MAX_WEEK + " inclusive.";

    private TutorialWeek() {
    }

    /**
     * Returns whether the given week is within the valid tutorial-week range.
     *
     * @param week Tutorial week to check.
     * @return True if the week is between 3 and 13, inclusive.
     */
    public static boolean isValid(int week) {
        return week >= MIN_WEEK && week <= MAX_WEEK;
    }
}
