package io.github.lcmlo.githubactivity.models;
/**
 * Represents a GitHub activity event returned by the GitHub API.
 *
 * <p>This model encapsulates the minimal set of information required
 * to display activity in the CLI, including:
 *
 * <ul>
 *     <li>Event type (e.g. PushEvent, IssuesEvent, WatchEvent)</li>
 *     <li>Repository name associated with the event</li>
 *     <li>Event-specific payload data (e.g. number of commits)</li>
 * </ul>
 *
 * <p>The structure of this class reflects a simplified representation
 * of the GitHub Events API response.
 *
 * GitHub API documentation:
 * https://docs.github.com/en/rest/activity/events
 */
public class GithubEvent {
}
