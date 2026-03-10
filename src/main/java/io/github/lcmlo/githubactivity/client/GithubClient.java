package io.github.lcmlo.githubactivity.client;

import io.github.lcmlo.githubactivity.model.GithubEvent;
import io.github.lcmlo.githubactivity.model.GithubEventType;
import io.github.lcmlo.githubactivity.model.GithubResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

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
    private static final String GITHUB_EVENTS_URL = "https://api.github.com/users/%s/events";
    private final String username;
    private final HttpClient client;

    public GithubClient(String username) {
        this.username = username;
        this.client = HttpClient.newHttpClient();
    }

    public GithubResponse sendEventRequest() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(GITHUB_EVENTS_URL, username)))
                .timeout(Duration.of(7, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();

        if (status != 200) return new GithubResponse(status, List.of());

        List<GithubEvent> events = jsonToEvents(response.body());
        return new GithubResponse(status, events);
    }

    private List<GithubEvent> jsonToEvents(String body) {
        List<GithubEvent> events = new ArrayList<>();
        JSONArray json = new JSONArray(body);
        for (int i = 0; i < json.length(); i++) {
            JSONObject jsonObject = json.getJSONObject(i);
            GithubEventType type = GithubEventType.from(jsonObject.getString("type"));
            String date = jsonObject.optString("created_at", "");

            JSONObject repo = jsonObject.getJSONObject("repo");
            String repoName = (repo!=null) ? repo.optString("name","unknown") : "unknown";

            GithubEvent event = new GithubEvent(type, repoName, date);
            events.add(event);
        }
        return events;
    }
}





