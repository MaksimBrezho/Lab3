package org.brejo.jira.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reporter {
    private String name;

    public Reporter(String name) {
        this.name = name;
    }

    public Reporter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}