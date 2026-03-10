package io.github.lcmlo.githubactivity;

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

}
