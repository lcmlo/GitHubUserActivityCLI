package io.github.lcmlo.githubactivity;

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

    public static void main(String[] args) {
        if (args.length == 0) System.err.println("Usage: com.lcmlo.githubactivity.Main <command> <args>");
        String username = args[0];
        GithubClient githubClient = new GithubClient(username);
    }

}
