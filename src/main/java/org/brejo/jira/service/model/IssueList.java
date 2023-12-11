package org.brejo.jira.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueList {
    private List<Issue> issues;

    private Integer total;

    public IssueList(List<Issue> issues, Integer total) {
        this.issues = issues;
        this.total = total;
    }

    public IssueList() {}

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}