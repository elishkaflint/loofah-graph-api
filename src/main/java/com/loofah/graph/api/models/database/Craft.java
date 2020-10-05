package com.loofah.graph.api.models.database;
import org.springframework.data.annotation.Id;

import java.util.List;


public class Craft {

    @Id
    private String id;
    private String title;
    private String description;
    private List<String> leads;
    private List<String> slackChannels;
    private String devServicesPage;

    public Craft(String id, String title, String description, final List<String> leads, final List<String> slackChannels, final String devServicesPage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.leads = leads;
        this.slackChannels = slackChannels;
        this.devServicesPage = devServicesPage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLeads() {
        return leads;
    }

    public void setLeads(final List<String> leads) {
        this.leads = leads;
    }

    public List<String> getSlackChannels() {
        return slackChannels;
    }

    public void setSlackChannels(final List<String> slackChannels) {
        this.slackChannels = slackChannels;
    }

    public String getDevServicesPage() {
        return devServicesPage;
    }

    public void setDevServicesPage(final String devServicesPage) {
        this.devServicesPage = devServicesPage;
    }

    public static final class Builder {
        private String id;
        private String title;
        private String description;
        private List<String> leads;
        private List<String> slackChannels;
        private String devServicesPage;

        private Builder() {
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public Builder withDescription(String val) {
            description = val;
            return this;
        }

        public Builder withLead(List<String> val) {
            leads = val;
            return this;
        }

        public Builder withSlackChannels(List<String> val) {
            slackChannels = val;
            return this;
        }

        public Builder withDevServicesPage(String val) {
            devServicesPage = val;
            return this;
        }

        public Craft build() {
            return new Craft(id, title, description, leads, slackChannels, devServicesPage );
        }
    }
}
