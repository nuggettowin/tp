package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the tutorial-week range shared by weekly student records.
 */
public class TutorialWeekTest {

    @Test
    public void isValid_weekWithinValidRange_returnsTrue() {
        for (int week = TutorialWeek.MIN_WEEK; week <= TutorialWeek.MAX_WEEK; week++) {
            assertTrue(TutorialWeek.isValid(week));
        }
    }

    @Test
    public void isValid_weekOutsideValidRange_returnsFalse() {
        int[] invalidWeeks = {Integer.MIN_VALUE, 0, 1, 2, 14, Integer.MAX_VALUE};

        for (int week : invalidWeeks) {
            assertFalse(TutorialWeek.isValid(week));
        }
    }
}
