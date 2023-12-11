package org.brejo.jira.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

    private String key;
    private Fields fields;

    public Issue(String key, Fields fields) {
        this.key = key;
        this.fields = fields;
    }

    public Issue() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Fields {

        private Reporter reporter;
        private Date created;
        private Date resolutiondate;

        public Fields(Reporter reporter, Date created, Date resolutiondate) {
            this.reporter = reporter;
            this.created = created;
            this.resolutiondate = resolutiondate;
        }

        public Fields() {}

        public Reporter getReporter() {
            return reporter;
        }

        public void setReporter(Reporter reporter) {
            this.reporter = reporter;
        }

        public Date getCreated() {
            return created;
        }

        public void setCreated(Date created) {
            this.created = created;
        }

        public Date getResolutiondate() {
            return resolutiondate;
        }

        public void setResolutiondate(Date resolutiondate) {
            this.resolutiondate = resolutiondate;
        }
    }
}