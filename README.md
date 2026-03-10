# GitHub User Activity CLI

A simple **Command Line Interface (CLI)** to fetch and display the recent activity of a GitHub user. This project is implemented in **Java 21**, using only standard libraries and a minimal dependency for JSON parsing.

---

## Features

* Fetches recent GitHub events for a given username.
* Displays human-readable messages for event types such as:

    * Pushes
    * Issues opened/closed
    * Stars (WatchEvent)
    * Forks
    * Repository creation
    * Comments on issues
* Graceful handling of errors:

    * Invalid usernames
    * User not found
    * API rate limits
    * Server errors
* Works entirely from the terminal without a GUI.

---

## Requirements

* Java 21+
* Maven (for building the project)
* Internet connection to access the GitHub API

---

## Installation

1. Clone this repository:

```bash
git clone <repo-url>
cd GitHubUserActivity
```

2. Build the project using Maven:

```bash
mvn clean package
```

> ⚠️ **Note:** The **shaded JAR** (executable with dependencies included) only appears after running `mvn package` **twice** due to the Maven Shade Plugin configuration. Its path is:
> `target/GitHubUserActivity-1.0-SNAPSHOT-shaded.jar`

---

## Usage

### Option 1: Shaded JAR (recommended)

Run the CLI by providing a GitHub username:

```bash
java -jar target/GitHubUserActivity-1.0-SNAPSHOT-shaded.jar <username>
```

### Option 2: Windows `.bat` script (alternative)

If you are on Windows, you can use the provided `run.bat` script in the project root. It uses the non-shaded JAR but works fine for most users:

```cmd
run.bat <username>
```

This runs:

```cmd
java -jar "%~dp0\target\GitHubUserActivity-1.0-SNAPSHOT.jar" %*
```

and will output the same CLI results.

---

### Example Output

```text
Pushed 3 times to torvalds/linux
Opened/Closed torvalds/linux
Starred torvalds/subsurface
Forked torvalds/subsurface
Created torvalds/new-repo
Commented on torvalds/linux
```

---

## Error Handling

* **User not found**: `User <username> was not found`
* **Invalid username**: `Invalid username`
* **Rate limit exceeded**: `GitHub API rate limit exceeded`
* **Server error**: `GitHub API returned HTTP 500 Internal Server Error`
* **Other errors**: `Error: <statusCode>`

---

## Project Structure

* `io.github.lcmlo.githubactivity.cli` — Entry point and argument handling (`Main.java`)
* `io.github.lcmlo.githubactivity.client` — Handles API requests and JSON parsing (`GithubClient.java`, `GithubResponse.java`)
* `io.github.lcmlo.githubactivity.formatter` — Converts GitHub events to human-readable CLI output (`EventFormatter.java`)
* `io.github.lcmlo.githubactivity.model` — Domain models representing GitHub events (`GithubEvent.java`, `GithubEventType.java`)

---

## Notes

* Uses [GitHub Events API](https://docs.github.com/en/rest/activity/events) to fetch recent activity.
* Only minimal external dependency: `org.json` for JSON parsing.
* Can be extended to:

    * Filter activity by event type
    * Cache results locally
    * Display in more structured formats
    * Explore additional GitHub API endpoints

---

## Credits

This project is based on the **GitHub Activity CLI** exercise from the [Roadmap.sh Java Backend Path](https://roadmap.sh). It was implemented as part of a learning roadmap to practice Java, APIs, JSON handling, and CLI applications.

