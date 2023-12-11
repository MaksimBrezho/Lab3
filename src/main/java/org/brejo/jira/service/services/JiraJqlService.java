package org.brejo.jira.service.services;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class JiraJqlService {
    private final String uriApi;

    public JiraJqlService(String domain) {
        this.uriApi = "https://" + domain + "/jira/rest/api/2/search?jql=";
    }

    public String requestJql(String jql, Integer maxResults) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            String request = uriApi + jql + "&maxResults=" + maxResults;
            HttpGet httpGet = new HttpGet(request);
            return httpClient.execute(httpGet,
                    response -> EntityUtils.toString(response.getEntity())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}