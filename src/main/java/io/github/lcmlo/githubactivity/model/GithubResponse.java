package io.github.lcmlo.githubactivity.model;

import java.util.List;

/**
 * Encapsulates the response of GithubClient.
 */
public record GithubResponse(int statusCode, List<GithubEvent> events) {}
