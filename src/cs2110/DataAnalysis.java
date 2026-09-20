package cs2110;

import java.time.LocalDateTime;

import static cs2110.DataUtilities.*;
import static cs2110.DataUtilities.DedupPolicy.*;
import static cs2110.DataUtilities.SearchPolicy.*;

/**
 * Methods utilizing tools from `DataUtilities` to enable interesting queries on `View` array data.
 */
public class DataAnalysis {

    /**
     * Returns an array comprising the first view recorded for each video.
     */
    static View[] firstVideoViews(View[] views) {
        View[] byTime = deduplicatingSort(views, BY_TIMESTAMP, KEEP_ALL);
        return deduplicatingSort(byTime, BY_VIDEO_ID, KEEP_FIRST);
    }

    /**
     * Returns the total number of views that the video with the given `videoID` has had.
     */
    static int totalViews(View[] views, String videoID) {
        View[] byVidID = deduplicatingSort(views, BY_VIDEO_ID, KEEP_ALL);
        View key = new View(null, videoID, null); // can't search for videoID directly; stick in "dummy" View object
        int first = binarySearch(byVidID, key, BY_VIDEO_ID, LEFT);
        int last = binarySearch(byVidID, key, BY_VIDEO_ID, RIGHT);
        return last - first;
    }

    /**
     * Returns the number of distinct users who viewed at least one video at a timestamp `t` with
     * `start <= t <= end`.
     * The worst-case overall runtime complexity of this method is O(NlogN).
     */
    @SuppressWarnings("SameParameterValue")
    static int countDistinctUsersInTimeInterval(View[] views, LocalDateTime start, LocalDateTime end) {
        View[] sortedViewsByTimestamp = deduplicatingSort(views, BY_TIMESTAMP, KEEP_ALL); // O(NlogN)
        int leftIndex = binarySearch(sortedViewsByTimestamp, new View(null, null, start), BY_TIMESTAMP, LEFT); // O(log N)
        int rightIndex = binarySearch(sortedViewsByTimestamp, new View(null, null, end), BY_TIMESTAMP, RIGHT); // O(log N)

        /*
        sortedViewsByTimestamp[0..leftIndex): timestamps < start
        sortedViewsByTimestamp[leftIndex..rightIndex): start <= timestamps <= end
        sortedViewsByTimestamp[rightIndex..]: timestamps > end
         */
        return rightIndex - leftIndex; // O(1)
    }

    /**
     * Returns an array of length `k` containing the Views of the last `k` distinct videos that the
     * given `userID` has watched (in any order). If that video has been watched more than once by
     * the user, then the View corresponding to the latest watch is included. More formally (to
     * account for possible ties), this method returns an array of `k` Views such that (1) the user
     * of each View has the given `userID`, (2) the `videoID`s of these Views are distinct, and (3)
     * for each videoID `v1` in this array, if this user viewed `v2` strictly after `v1`, then a
     * view of `v2` will also be present in the array. If `userID` has viewed fewer than `k`
     * distinct videos, then a shorter array containing their latest View of each video is returned.
     * The worst-case overall runtime complexity of this method is O(NlogN).
     */
    @SuppressWarnings("SameParameterValue")
    static View[] lastKViewedByUser(View[] views, String userID, int k) {
        View[] byUserId = deduplicatingSort(views, BY_USER_ID, KEEP_ALL); // O(NlogN + MlogM + UlogU)

        View key = new View(userID, null, null); // O(1)
        int start = binarySearch(byUserId, key, BY_USER_ID, LEFT); // O(logN)
        int end = binarySearch(byUserId, key, BY_USER_ID, RIGHT); // O(logN)
        // All views in byUserId[start, end) have userID `userId`.

        View[] userIDViews = copyOfRange(byUserId, start, end); // O(M)
        View[] userIDViewsByTime = deduplicatingSort(userIDViews, BY_TIMESTAMP, KEEP_ALL); // O(MlogM)
        View[] userIDDistinctVideos = deduplicatingSort(userIDViewsByTime, BY_VIDEO_ID, KEEP_LAST); // O(MlogU)


        int numDistinctVideos = userIDDistinctVideos.length; // O(1)
        View[] userIDDistinctVideosByTime = deduplicatingSort(userIDDistinctVideos, BY_TIMESTAMP, KEEP_ALL); // O(UlogU)

        if (numDistinctVideos < k) { // O(1)
            return userIDDistinctVideosByTime; // O(1)
        }
        else {
            return copyOfRange(userIDDistinctVideosByTime, numDistinctVideos-k, numDistinctVideos); // O(k)
        }
    }

    /**
     * Returns the `userID` of an individual who has the most recorded views of the video with
     * the given `videoID` in the `views` array. Returns `null` if there are no recorded views for
     * that video. The contents of `views` are not modified by this method.
     */
    @SuppressWarnings("SameParameterValue")
    static String mostObsessedViewer(View[] views, String videoID) {
        // TODO 7: Implement this method according to its specifications. Make sure to add a comment
        //  documenting the invariant of each loop that you write. Your definition must have a
        //  worst-case runtime complexity of `O(N + M log M)`, where `N = views.length` and `M` is
        //  the number of entries of `views` with the given `videoID`.
        int M = 0;
        View[] entriesWithVideoId = new View[views.length];
        /* Loop Invariant: M is the number of entries of `views[0..i)` with given `videoID`. */
        for (int i = 0; i < views.length; i++) { // O(N)
            if (views[i].videoID().equals(videoID)) {
                entriesWithVideoId[M] = views[i];
                M++;
            }
        }
        entriesWithVideoId = copyOfRange(entriesWithVideoId, 0, M); // O(M)
        View[] entriesWithVideoIdByUserID = deduplicatingSort(entriesWithVideoId, BY_USER_ID, KEEP_ALL); // O(MlogM)

        int maxViewsBySingleUser = 0;
        String userWithMaxViews = null;

        // M iterations, O(logM) each. Total: O(MlogM)
        for (int i = 0; i < M; i++) {
            String user = entriesWithVideoIdByUserID[i].userID();
            View key = new View(user, null, null);

            int firstViewByUser = binarySearch(entriesWithVideoIdByUserID, key, BY_USER_ID, LEFT);
            int lastViewByUser = binarySearch(entriesWithVideoIdByUserID, key, BY_USER_ID, RIGHT);

            int viewsBySingleUser = lastViewByUser - firstViewByUser;
            if (viewsBySingleUser > maxViewsBySingleUser) {
                maxViewsBySingleUser = viewsBySingleUser;
                userWithMaxViews = user;
            }
        }

        return userWithMaxViews;

    }
}