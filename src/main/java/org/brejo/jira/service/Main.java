package org.brejo.jira.service;

import org.brejo.jira.service.services.IssueAnalyzerService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String project;
        String domain = args[1];
        IssueAnalyzerService issueAnalyzerService = new IssueAnalyzerService(domain);
        String title;
        if (args[0].equals("time-spent")) {
            project = args[2];
            title = args[3];
            issueAnalyzerService.drawHistogramTimeSpent(title, project,
                    "Closed", 1000);
        }
        if (args[0].equals("quantity-issue")) {
            project = args[2];
            title = args[3];
            String startData = args[4];
            String endData = args[5];
            issueAnalyzerService.drawHistogramForDaysIssue(startData, endData,
                    title, project,1000);
        }
        if (args[0].equals("top-users")) {
            project = args[2];
            title = args[3];
            String quantityUsers = args[4];
            issueAnalyzerService.drawHistogramTopUsers(Integer.parseInt(quantityUsers), title, project,
                    "Closed", 1000);
        }
        if (args[0].equals("user-time-spent")) {
            project = args[2];
            title = args[3];
            String user = args[4];
            issueAnalyzerService.drawHistogramIssueReporter(title, user, project,
                    "Closed", 1000);
        }
        if (args[0].equals("issue-priorities")) {
            project = args[2];
            title = args[3];
            issueAnalyzerService.drawHistogramPriority(title, project,
                    List.of("Blocker", "Critical", "Major", "Minor", "Trivial"), 1);
        }
    }
}