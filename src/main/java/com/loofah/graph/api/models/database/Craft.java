package com.loofah.graph.api.models.database;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;


public class Craft implements Comparable<Craft> {

    @Id
    private final String id;
    private final String title;
    private final String description;
    private final List<String> leads;
    private final List<String> slackChannels;
    private final String devServicesPage;

    public Craft(final String id, final String title, final String description, final List<String> leads, final List<String> slackChannels, final String devServicesPage) {
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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getLeads() {
        return leads;
    }

    public List<String> getSlackChannels() {
        return slackChannels;
    }

    public String getDevServicesPage() {
        return devServicesPage;
    }

    @Override
    public String toString() {
        return "Craft{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Craft craft = (Craft) o;
        return Objects.equals(id, craft.id) &&
                Objects.equals(title, craft.title) &&
                Objects.equals(description, craft.description) &&
                Objects.equals(leads, craft.leads) &&
                Objects.equals(slackChannels, craft.slackChannels) &&
                Objects.equals(devServicesPage, craft.devServicesPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, leads, slackChannels, devServicesPage);
    }

    // Compares two Craft objects based on the alphabetical (natural) order of their titles. Crafts with null
    // titles will always be at the end, followed by null Craft references.
    @Override
    public int compareTo(@NotNull final Craft otherCraft) {
        final Comparator<Craft> titleComparator = nullsLast(
                Comparator.comparing(Craft::getTitle, nullsLast(naturalOrder()))
        );
        return titleComparator.compare(this, otherCraft);
    }

    /**
     * This enum contains the string representation
     * of the fields in this class. Therefore it also
     * represents the fields as they are stored in the database.
     */
    public enum CraftFields {

        ID("id"),
        TITLE("title"),
        DESCRIPTION("description"),
        LEADS("leads"),
        SLACK_CHANNELS("slackChannels"),
        DEV_SERVICES_PAGE("devServicesPage");

        String key;

        CraftFields(final String key) {
            this.key = key;
        }

        public String key() {
            return this.key;
        }
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

        public Builder withId(final String val) {
            id = val;
            return this;
        }

        public Builder withTitle(final String val) {
            title = val;
            return this;
        }

        public Builder withDescription(final String val) {
            description = val;
            return this;
        }

        public Builder withLead(final List<String> val) {
            leads = val;
            return this;
        }

        public Builder withSlackChannels(final List<String> val) {
            slackChannels = val;
            return this;
        }

        public Builder withDevServicesPage(final String val) {
            devServicesPage = val;
            return this;
        }

        public Craft build() {
            return new Craft(id, title, description, leads, slackChannels, devServicesPage);
        }
    }
}
