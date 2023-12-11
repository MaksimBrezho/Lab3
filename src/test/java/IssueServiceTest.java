import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.brejo.jira.service.model.Issue;
import org.brejo.jira.service.model.IssueList;
import org.brejo.jira.service.model.Reporter;
import org.brejo.jira.service.services.IssueService;

import static org.junit.jupiter.api.Assertions.*;


public class IssueServiceTest {
    IssueService issueService = new IssueService("issues.apache.org");

    @Test
    public void getIssuesForProjectReturnTest() throws JsonProcessingException {
        IssueList issueList = issueService.getIssuesForProject("KAFKA", "Closed", 1000);
        checkClosedIssueList(issueList);
    }

    @Test
    public void getIssuesForProjectAndReporterReturnTest() throws JsonProcessingException {
        IssueList issueList = issueService.getIssuesForProjectAndReporter("lihaosky","KAFKA",
                "Closed", 1000);
        checkReporterIssueList(issueList);
    }

    @Test
    public void getIssuesForProjectAndPriorityReturnTest() throws JsonProcessingException {
        IssueList issueList = issueService.getIssuesForProjectAndPriority("Blocker","KAFKA",
                1000);
        checkIssueList(issueList);
    }

    @Test
    public void getCreatedIssueReturnTest() throws JsonProcessingException {
        IssueList issueList = issueService.getCreatedIssue("2023-11-01", "2023-11-30","KAFKA",
                1000);
        checkIssueList(issueList);
    }

    @Test
    public void getClosedIssueReturnTest() throws JsonProcessingException {
        IssueList issueList = issueService.getCreatedIssue("2023-11-01", "2023-11-30","KAFKA",
                1000);
        checkIssueList(issueList);
    }

    private void checkReporterIssueList(IssueList issueList) {
        checkClosedIssueList(issueList);
        for (Issue issue: issueList.getIssues()) {
            Issue.Fields fields = issue.getFields();
            Reporter reporter = fields.getReporter();
            assertNotNull(reporter);
            assertNotNull(reporter.getName());
        }
    }

    private void checkClosedIssueList(IssueList issueList) {
        checkIssueList(issueList);
        for (Issue issue: issueList.getIssues()) {
            Issue.Fields fields = issue.getFields();
            assertNotNull(fields.getResolutiondate());
        }
    }

    private void checkIssueList(IssueList issueList) {
        assertNotNull(issueList);
        assertNotNull(issueList.getTotal());
        assertNotNull(issueList.getIssues());
        for (Issue issue: issueList.getIssues()) {
            assertNotNull(issue);
            assertNotNull(issue.getKey());
            Issue.Fields fields = issue.getFields();
            assertNotNull(fields);
            assertNotNull(fields.getCreated());
        }
    }
}
