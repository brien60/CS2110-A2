package cs2110;

import static cs2110.DataUtilities.BY_USER_ID;
import static cs2110.DataUtilities.BY_VIDEO_ID;
import static cs2110.DataUtilities.BY_TIMESTAMP;
import static cs2110.DataUtilities.SearchPolicy.*;
import static cs2110.DataUtilities.copyOfRange;
import static cs2110.DataUtilities.binarySearch;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs2110.DataUtilities.View;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BinarySearchTest {

    // Find unique record LEFT
    @DisplayName("WHEN one of the `views` records has the target userID, THEN `binarySearch()` "
            + "with the BY_USER_ID Comparator and LEFT search policy returns the index of that "
            + "view.")
    @Test
    public void testBinarySearchFindsUniqueUserID() {
        View[] views = new View[]{
                new View("A", "G", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("F", "B", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("G", "A", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View[] viewsCopy = copyOfRange(views, 0, views.length);

        View key = new View("A", "V", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(0, binarySearch(views, key, BY_USER_ID, LEFT));

        key = new View("C", "V", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(2, binarySearch(views, key, BY_USER_ID, LEFT));

        key = new View("G", "V", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(6, binarySearch(views, key, BY_USER_ID, LEFT));

        assertArrayEquals(viewsCopy, views);
    }

    @DisplayName("WHEN one of the `views` records has the target videoID, THEN `binarySearch()` "
            + "with the BY_VIDEO_ID Comparator and LEFT search policy returns the index of that "
            + "view.")
    @Test
    public void testBinarySearchFindsUniqueVideoID() {
        View[] views = new View[]{
                new View("G", "A", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("F", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View[] viewsCopy = copyOfRange(views, 0, views.length);

        View key = new View("U", "A", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(0, binarySearch(views, key, BY_VIDEO_ID, LEFT));

        key = new View("U", "D", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(3, binarySearch(views, key, BY_VIDEO_ID, LEFT));

        key = new View("U", "G", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(6, binarySearch(views, key, BY_VIDEO_ID, LEFT));

        assertArrayEquals(viewsCopy, views);
    }

    @DisplayName("WHEN one of the `views` records has the target timestamp, THEN `binarySearch()` "
            + "with the BY_TIMESTAMP Comparator and LEFT search policy returns the index of that "
            + "view.")
    @Test
    public void testBinarySearchFindsUniqueTimestamp() {
        View[] views = new View[]{
                new View("G", "A", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("F", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View[] viewsCopy = copyOfRange(views, 0, views.length);

        View key = new View("U", "V", LocalDateTime.of(2026,1,1,0,0));
        assertEquals(0, binarySearch(views, key, BY_TIMESTAMP, LEFT));

        key = new View("U", "V", LocalDateTime.of(2026,1,2,0,0));
        assertEquals(1, binarySearch(views, key, BY_TIMESTAMP, LEFT));

        key = new View("U", "V", LocalDateTime.of(2026,1,7,0,0));
        assertEquals(6, binarySearch(views, key, BY_TIMESTAMP, LEFT));

        assertArrayEquals(viewsCopy, views);
    }

    // Find unique record RIGHT
    @DisplayName("WHEN one of the `views` records has the target userID, THEN `binarySearch()` "
            + "with the BY_USER_ID Comparator and RIGHT search policy returns the index after the "
            + "index of that view.")
    @Test
    public void testRightBinarySearchReturnsIndexAfterUserIDMatch() {
        View[] views = new View[]{
                new View("A", "G", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("F", "B", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("G", "A", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View[] viewsCopy = copyOfRange(views, 0, views.length);

        View key = new View("A", "V", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(1, binarySearch(views, key, BY_USER_ID, RIGHT));

        key = new View("C", "V", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(3, binarySearch(views, key, BY_USER_ID, RIGHT));

        key = new View("G", "V", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(7, binarySearch(views, key, BY_USER_ID, RIGHT));

        assertArrayEquals(viewsCopy, views);
    }

    @DisplayName("WHEN one of the `views` records has the target videoID, THEN `binarySearch()` "
            + "with the BY_VIDEO_ID Comparator and RIGHT search policy returns the index after the "
            + "index of that view.")
    @Test
    public void testRightBinarySearchReturnsIndexAfterVideoIDMatch() {
        View[] views = new View[]{
                new View("G", "A", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("F", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View[] viewsCopy = copyOfRange(views, 0, views.length);

        View key = new View("U", "A", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(1, binarySearch(views, key, BY_VIDEO_ID, RIGHT));

        key = new View("U", "D", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(4, binarySearch(views, key, BY_VIDEO_ID, RIGHT));

        key = new View("U", "G", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(7, binarySearch(views, key, BY_VIDEO_ID, RIGHT));

        assertArrayEquals(viewsCopy, views);
    }

    @DisplayName("WHEN one of the `views` records has the target timestamp, THEN `binarySearch()` "
            + "with the BY_TIMESTAMP Comparator and RIGHT search policy returns the index after the "
            + "index of that view.")
    @Test
    public void testRightBinarySearchReturnsIndexAfterTimestampMatch() {
        View[] views = new View[]{
                new View("G", "A", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("F", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View[] viewsCopy = copyOfRange(views, 0, views.length);

        View key = new View("U", "V", LocalDateTime.of(2026,1,1,0,0));
        assertEquals(1, binarySearch(views, key, BY_TIMESTAMP, RIGHT));

        key = new View("U", "V", LocalDateTime.of(2026,1,2,0,0));
        assertEquals(2, binarySearch(views, key, BY_TIMESTAMP, RIGHT));

        key = new View("U", "V", LocalDateTime.of(2026,1,7,0,0));
        assertEquals(7, binarySearch(views, key, BY_TIMESTAMP, RIGHT));

        assertArrayEquals(viewsCopy, views);
    }

    // Key after all views
    @DisplayName("WHEN the userID of the `key` is alphabetically after the userIDs of all of the "
            + "`view`s, THEN `binarySearch()` with the BY_USER_ID Comparator returns the length "
            + "of the array.")
    @Test
    public void testBinarySearchUserIDAfterAll() {
        View[] views = new View[]{
                new View("A", "G", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("F", "B", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("G", "A", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View key = new View("H", "A", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(7, binarySearch(views, key, BY_USER_ID, LEFT));
        assertEquals(7, binarySearch(views, key, BY_USER_ID, RIGHT));
    }

    @DisplayName("WHEN the videoID of the `key` is alphabetically after the videoIDs of all of the "
            + "`view`s, THEN `binarySearch()` with the BY_VIDEO_ID Comparator returns the length "
            + "of the array.")
    @Test
    public void testBinarySearchVideoIDAfterAll() {
        View[] views = new View[]{
                new View("G", "A", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("F", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View key = new View("A", "H", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(7, binarySearch(views, key, BY_VIDEO_ID, LEFT));
        assertEquals(7, binarySearch(views, key, BY_VIDEO_ID, RIGHT));
    }

    @DisplayName("WHEN the timestamp of the `key` is later than the timestamps of all of the "
            + "`view`s, THEN `binarySearch()` with the BY_TIMESTAMP Comparator returns the length "
            + "of the array.")
    @Test
    public void testBinarySearchTimestampAfterAll() {
        View[] views = new View[]{
                new View("G", "A", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("F", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View key = new View("A", "A", LocalDateTime.of(2026, 1, 8, 0, 0));
        assertEquals(7, binarySearch(views, key, BY_TIMESTAMP, LEFT));
        assertEquals(7, binarySearch(views, key, BY_TIMESTAMP, RIGHT));
    }

    // Key before all views
    @DisplayName("WHEN the userID of the `key` is alphabetically before the userIDs of all of the "
            + "`view`s, THEN `binarySearch()` with the BY_USER_ID Comparator returns 0.")
    @Test
    public void testBinarySearchUserIDBeforeAll() {
        View[] views = new View[]{
                new View("B", "G", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("C", "F", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "E", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("E", "D", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("F", "C", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("G", "B", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View key = new View("A", "A", LocalDateTime.of(2026, 1, 7, 0, 0));
        assertEquals(0, binarySearch(views, key, BY_USER_ID, LEFT));
        assertEquals(0, binarySearch(views, key, BY_USER_ID, RIGHT));
    }

    @DisplayName("WHEN the videoID of the `key` is alphabetically before the videoIDs of all of the "
            + "`view`s, THEN `binarySearch()` with the BY_VIDEO_ID Comparator returns 0.")
    @Test
    public void testBinarySearchVideoIDBeforeAll() {
        View[] views = new View[]{
                new View("F", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View key = new View("A", "A", LocalDateTime.of(2026, 1, 7, 0, 0));
        assertEquals(0, binarySearch(views, key, BY_VIDEO_ID, LEFT));
        assertEquals(0, binarySearch(views, key, BY_VIDEO_ID, RIGHT));
    }

    @DisplayName("WHEN the timestamp of the `key` is earlier than the timestamps of all of the "
            + "`view`s, THEN `binarySearch()` with the BY_TIMESTAMP Comparator returns 0.")
    @Test
    public void testBinarySearchTimestampBeforeAll() {
        View[] views = new View[]{
                new View("F", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("B", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
        };
        View key = new View("A", "G", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(0, binarySearch(views, key, BY_TIMESTAMP, LEFT));
        assertEquals(0, binarySearch(views, key, BY_TIMESTAMP, RIGHT));
    }

    // Multiple Records match key
    @DisplayName("WHEN multiple records in `views` have the target userID, THEN `binarySearch` "
            + "with the BY_USER_ID Comparator and LEFT search policy returns the correct index.")
    @Test
    public void testLeftBinarySearchMultipleUserIDMatches() {
        View[] views = new View[]{
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
                new View("A", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("C", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("D", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("D", "A", LocalDateTime.of(2026, 1, 1, 0, 0))
        };
        View key = new View("A", "V", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(0, binarySearch(views, key, BY_USER_ID, LEFT));

        key = new View("C", "V", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(2, binarySearch(views, key, BY_USER_ID, LEFT));

        key = new View("D", "V", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(4, binarySearch(views, key, BY_USER_ID, LEFT));
    }

    @DisplayName("WHEN multiple records in `views` have the target userID, THEN `binarySearch` "
            + "with the BY_USER_ID Comparator and RIGHT search policy returns the correct index.")
    @Test
    public void testRightBinarySearchMultipleUserIDMatches() {
        View[] views = new View[]{
                new View("A", "G", LocalDateTime.of(2026, 1, 7, 0, 0)),
                new View("A", "F", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("C", "E", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("C", "D", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("D", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("D", "B", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("D", "A", LocalDateTime.of(2026, 1, 1, 0, 0))
        };
        View key = new View("A", "V", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(2, binarySearch(views, key, BY_USER_ID, RIGHT));

        key = new View("C", "V", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(4, binarySearch(views, key, BY_USER_ID, RIGHT));

        key = new View("D", "V", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(7, binarySearch(views, key, BY_USER_ID, RIGHT));
    }

    @DisplayName("WHEN multiple records in `views` have the target videoID, THEN `binarySearch` "
            + "with the BY_VIDEO_ID Comparator and LEFT search policy returns the correct index.")
    @Test
    public void testLeftBinarySearchMultipleVideoIDMatches() {
        View[] views = new View[]{
                new View("G", "A", LocalDateTime.of(2026, 1, 7, 0, 0)),
                new View("F", "A", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("D", "C", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "D", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("B", "D", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("A", "D", LocalDateTime.of(2026, 1, 1, 0, 0))
        };
        View key = new View("U", "A", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(0, binarySearch(views, key, BY_VIDEO_ID, LEFT));

        key = new View("U", "C", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(2, binarySearch(views, key, BY_VIDEO_ID, LEFT));

        key = new View("U", "D", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(4, binarySearch(views, key, BY_VIDEO_ID, LEFT));
    }

    @DisplayName("WHEN multiple records in `views` have the target videoID, THEN `binarySearch` "
            + "with the BY_VIDEO_ID Comparator and RIGHT search policy returns the correct index.")
    @Test
    public void testRightBinarySearchMultipleVideoIDMatches() {
        View[] views = new View[]{
                new View("G", "A", LocalDateTime.of(2026, 1, 7, 0, 0)),
                new View("F", "A", LocalDateTime.of(2026, 1, 6, 0, 0)),
                new View("E", "C", LocalDateTime.of(2026, 1, 5, 0, 0)),
                new View("D", "C", LocalDateTime.of(2026, 1, 4, 0, 0)),
                new View("C", "D", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("B", "D", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("A", "D", LocalDateTime.of(2026, 1, 1, 0, 0))
        };
        View key = new View("U", "A", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(2, binarySearch(views, key, BY_VIDEO_ID, RIGHT));

        key = new View("U", "C", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(4, binarySearch(views, key, BY_VIDEO_ID, RIGHT));

        key = new View("U", "D", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(7, binarySearch(views, key, BY_VIDEO_ID, RIGHT));
    }

    @DisplayName("WHEN multiple records in `views` have the target timestamp, THEN `binarySearch` "
            + "with the BY_TIMESTAMP Comparator and LEFT search policy returns the correct index.")
    @Test
    public void testLeftBinarySearchMultipleTimestampMatches() {
        View[] views = new View[]{
                new View("G", "G", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("F", "F", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("E", "E", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("C", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("B", "B", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("A", "A", LocalDateTime.of(2026, 1, 3, 0, 0))
        };
        View key = new View("U", "V", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(0, binarySearch(views, key, BY_TIMESTAMP, LEFT));

        key = new View("U", "V", LocalDateTime.of(2026, 1, 2, 0, 0));
        assertEquals(2, binarySearch(views, key, BY_TIMESTAMP, LEFT));

        key = new View("U", "V", LocalDateTime.of(2026, 1, 3, 0, 0));
        assertEquals(4, binarySearch(views, key, BY_TIMESTAMP, LEFT));
    }

    @DisplayName("WHEN multiple records in `views` have the target timestamp, THEN `binarySearch` "
            + "with the BY_TIMESTAMP Comparator and RIGHT search policy returns the correct index.")
    @Test
    public void testRightBinarySearchMultipleTimestampMatches() {
        View[] views = new View[]{
                new View("G", "G", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("F", "F", LocalDateTime.of(2026, 1, 1, 0, 0)),
                new View("E", "E", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("D", "D", LocalDateTime.of(2026, 1, 2, 0, 0)),
                new View("C", "C", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("B", "B", LocalDateTime.of(2026, 1, 3, 0, 0)),
                new View("A", "A", LocalDateTime.of(2026, 1, 3, 0, 0))
        };
        View key = new View("U", "V", LocalDateTime.of(2026, 1, 1, 0, 0));
        assertEquals(2, binarySearch(views, key, BY_TIMESTAMP, RIGHT));

        key = new View("U", "V", LocalDateTime.of(2026, 1, 2, 0, 0));
        assertEquals(4, binarySearch(views, key, BY_TIMESTAMP, RIGHT));

        key = new View("U", "V", LocalDateTime.of(2026, 1, 3, 0, 0));
        assertEquals(7, binarySearch(views, key, BY_TIMESTAMP, RIGHT));
    }

}
