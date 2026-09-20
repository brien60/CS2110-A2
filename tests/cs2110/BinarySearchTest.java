package cs2110;

import static cs2110.DataUtilities.BY_USER_ID;
import static cs2110.DataUtilities.SearchPolicy.*;
import static cs2110.DataUtilities.binarySearch;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs2110.DataUtilities.View;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BinarySearchTest {
    @DisplayName("WHEN multiple records in `views` have the target userID, THEN `binarySearch` "
            + "with the BY_USER_ID Comparator and LEFT search policy returns the correct index.")
    @Test
    public void testLeftBinarySearchMultipleMatches() {
        View[] views = new View[]{
                new View("A", "G", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("C", "D", LocalDateTime.of(2026, 1, 7, 0, 0)),
                new View("D", "C", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("D", "B", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("D", "A", LocalDateTime.of(2026, 1, 2, 0, 0))
        };
        View key = new View("D", "D", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(4, binarySearch(views, key, BY_USER_ID, LEFT));

        key = new View("C", "C", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(2, binarySearch(views, key, BY_USER_ID, LEFT));
    }

    @DisplayName("WHEN multiple records in `views` have the target userID, THEN `binarySearch` "
            + "with the BY_USER_ID Comparator and RIGHT search policy returns the correct index.")
    @Test
    public void testRightBinarySearchMultipleMatches() {
        View[] views = new View[]{
                new View("A", "G", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("C", "D", LocalDateTime.of(2026, 1, 7, 0, 0)),
                new View("D", "C", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("D", "B", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("D", "A", LocalDateTime.of(2026, 1, 2, 0, 0))
        };
        View key = new View("D", "D", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(7, binarySearch(views, key, BY_USER_ID, RIGHT));

        key = new View("C", "C", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(4, binarySearch(views, key, BY_USER_ID, RIGHT));
    }

}
