package io.github.lcmlo.githubactivity.formatter;

import io.github.lcmlo.githubactivity.model.GithubEvent;
import io.github.lcmlo.githubactivity.model.GithubEventType;

import java.util.List;

/**
 * Utility class responsible for transforming GitHub event data
 * into human-readable messages suitable for terminal output.
 *
 * <p>This class interprets different GitHub event types such as:
 * <ul>
 *     <li>PushEvent</li>
 *     <li>IssuesEvent</li>
 *     <li>WatchEvent</li>
 * </ul>
 *
 * <p>Each event type is converted into a descriptive message
 * displayed in the CLI.
 *
 * Example output:
 *
 * <pre>
 * - Pushed 3 commits to octocat/Hello-World
 * - Opened a new issue in octocat/Hello-World
 * - Starred octocat/Hello-World
 * </pre>
 *
 * <p>The formatter receives parsed event objects and returns
 * formatted strings.
 */
public class EventFormatter {
    public static String format(List<GithubEvent> events) {
        StringBuilder sb = new StringBuilder();
        String lastPushRepo = null;
        int pushCount = 0;

        for (GithubEvent event : events) {
            GithubEventType type = event.type();
            String repo = event.repoName();

            if (type == GithubEventType.PUSH) {
                if (repo.equals(lastPushRepo))
                    pushCount++;
                else {
                    flush(sb, pushCount, lastPushRepo);
                    lastPushRepo = repo;
                    pushCount = 1;
                }
                continue;
            }
            flush(sb, pushCount, lastPushRepo);
            pushCount = 0;
            lastPushRepo = null;

            sb.append(formatNonPush(type, repo)).append("\n");
        }

        flush(sb, pushCount, lastPushRepo); // in case last event is a push
        return sb.toString();
    }

    private static String formatNonPush(GithubEventType type, String repo) {
        return switch (type) {
            case PUSH -> null;
            case WATCH -> "Starred " + repo;
            case FORK -> "Forked " + repo;
            case CREATE -> "Created " + repo;
            case ISSUES -> "Opened/Closed " + repo;
            case COMMENT -> "Commented on " + repo;
            case UNKNOWN -> "Performed an activity on " + repo;
        };
    }

    /*
        Helper method to help improve readability,
        deals with consecutive pushes to the same repo
     */
    private static void flush(StringBuilder sb,int pushCount,String lastPushRepo) {
        if (pushCount > 0) {
            sb.append("Pushed ")
                    .append(pushCount)
                    .append(pushCount == 1 ? " time to " : " times to ")
                    .append(lastPushRepo)
                    .append("\n");
        }
    }
}
