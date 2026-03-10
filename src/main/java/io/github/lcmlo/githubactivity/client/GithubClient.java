package io.github.lcmlo.githubactivity;

/**
 * Client responsible for interacting with the GitHub REST API.
 *
 * <p>This class performs HTTP requests to retrieve recent activity
 * events for a given GitHub user using the endpoint:
 *
 * <pre>
 * https://api.github.com/users/&lt;username&gt;/events
 * </pre>
 *
 * <p>Responsibilities include:
 * <ul>
 *     <li>Building HTTP requests</li>
 *     <li>Sending requests using Java's HttpClient</li>
 *     <li>Handling HTTP response codes</li>
 *     <li>Returning the raw JSON response body</li>
 * </ul>
 *
 * <p>No external libraries are used; the implementation relies on
 * the standard {@code java.net.http.HttpClient}.
 */
public class GithubClient {
    private final String username;

    public GithubClient(String username) {
        this.username = username;
    }








}





