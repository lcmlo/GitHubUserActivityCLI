package io.github.lcmlo.githubactivity.model;

public enum GithubEventType {
    PUSH,
    WATCH,
    FORK,
    CREATE,
    ISSUES,
    UNKNOWN,
    COMMENT;

    public static GithubEventType from(String type) {
        return switch (type) {
            case "PushEvent" -> PUSH;
            case "WatchEvent" -> WATCH;
            case "ForkEvent" -> FORK;
            case "CreateEvent" -> CREATE;
            case "IssuesEvent" -> ISSUES;
            case "IssueCommentEvent" -> COMMENT;
            default -> UNKNOWN;
        };
    }
}