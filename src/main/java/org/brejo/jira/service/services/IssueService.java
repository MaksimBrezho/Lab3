package org.brejo.jira.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.brejo.jira.service.model.IssueList;

public class IssueService {

    private final ObjectMapper objectMapper;
    private final JiraJqlService jiraJqlService;

    private static final String PROJECT = "project=";
    private static final String STATUS = "status=";
    private static final String AND = "%20AND%20";
    private static final String REPORTER = "reporter=";
    private static final String PRIORITY = "priority=";

    private static final String CREATED_HIGH = "created%3E=";

    private static final String CREATED_LOW = "created%3C=";

    private static final String CLOSED_HIGH = "resolutiondate%3E=";

    private static final String CLOSED_LOW = "resolutiondate%3C=";

    public IssueService(String domain) {
        this.objectMapper = new ObjectMapper();
        this.jiraJqlService = new JiraJqlService(domain);
    }

    public IssueList getIssuesForProject(String project, String status, Integer maxResults)
            throws JsonProcessingException {
        String jql = PROJECT + project + AND + STATUS + status;
        String response = jiraJqlService.requestJql(jql, maxResults);
        return objectMapper.readValue(response, IssueList.class);
    }

    public IssueList getIssuesForProjectAndReporter(String reporter, String project, String status, Integer maxResults)
            throws JsonProcessingException {
        String jql = REPORTER + reporter + AND + PROJECT + project + AND + STATUS + status;
        String response = jiraJqlService.requestJql(jql, maxResults);
        return objectMapper.readValue(response, IssueList.class);
    }

    public IssueList getIssuesForProjectAndPriority(String priority, String project, Integer maxResults)
            throws JsonProcessingException {
        String jql = PRIORITY + priority + AND + PROJECT + project;
        String response = jiraJqlService.requestJql(jql, maxResults);
        return objectMapper.readValue(response, IssueList.class);
    }

    public IssueList getCreatedIssue(String startDate, String endDate, String project, Integer maxResults) throws JsonProcessingException {
        String jql = PROJECT + project + AND + CREATED_HIGH + startDate + AND + CREATED_LOW + endDate;
        String response = jiraJqlService.requestJql(jql, maxResults);

        return objectMapper.readValue(response, IssueList.class);
    }

    public IssueList getClosedIssue(String startDate, String endDate, String project, Integer maxResults) throws JsonProcessingException {
        String jql = PROJECT + project + AND + CLOSED_HIGH + startDate + AND + CLOSED_LOW + endDate;
        String response = jiraJqlService.requestJql(jql, maxResults);

        return objectMapper.readValue(response, IssueList.class);
    }
}