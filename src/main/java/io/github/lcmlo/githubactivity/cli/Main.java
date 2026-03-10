package io.github.lcmlo.githubactivity.cli;

import io.github.lcmlo.githubactivity.client.GithubClient;
import io.github.lcmlo.githubactivity.formatter.EventFormatter;
import io.github.lcmlo.githubactivity.model.GithubResponse;


import java.io.IOException;

/**
 * Entry point for the GitHub Activity CLI application.
 *
 * <p>This class is responsible for:
 * <ul>
 *     <li>Parsing command line arguments</li>
 *     <li>Validating the provided GitHub username</li>
 *     <li>Coordinating calls to the GitHub API client</li>
 *     <li>Displaying formatted activity output in the terminal</li>
 * </ul>
 *
 * <p>The application expects a GitHub username as the first argument:
 *
 * <pre>
 * github-activity &lt;username&gt;
 * </pre>
 *
 * Example:
 * <pre>
 * github-activity torvalds
 * </pre>
 *
 * @author lcmlo
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 0) {
            System.err.println("Usage: com.lcmlo.githubactivity.Main <username>");
            return;
        }

        String username = args[0];
        GithubClient client = new GithubClient(username);
        GithubResponse result = client.sendEventRequest();

        switch (result.statusCode()) {
            case 200 -> {
                if (result.events().isEmpty())
                    System.out.println("No recent activity");
                else
                    System.out.println(EventFormatter.format(result.events()));
            }
            case 403 -> System.out.println("GitHub API rate limit exceeded");
            case 404 -> System.out.println("User " + username + " was not found");
            case 422 -> System.out.println("Invalid username");
            case 500 -> System.out.println("GitHub API returned HTTP 500 Internal Server Error");
            default -> System.out.println("Error: " + result.statusCode());
        }
    }

}
