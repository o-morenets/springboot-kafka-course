package net.javaguides.springboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

public record WikimediaEvent(

        @JsonProperty("$schema")
        String schema,

        Meta meta,
        long id,
        String type,
        int namespace,
        String title,
        String title_url,
        String comment,
        long timestamp,
        String user,
        boolean bot,
        String notify_url,
        boolean minor,
        boolean patrolled,
        Length length,
        Revision revision,
        String server_url,
        String server_name,
        String server_script_path,
        String wiki,
        String parsedcomment
) {
    @Embeddable
    public record Meta(

            @Column(length = 2000)
            String uri,

            String request_id,
            String meta_id,
            String domain,
            String stream,
            String dt,
            String topic,

            @Column(name = "partition_number")
            int partition,

            long offset
    ) {}

    @Embeddable
    public record Length(
            int old_length,

            @JsonProperty("new")
            int new_length
    ) {}

    @Embeddable
    public record Revision(
            long old_revision,

            @JsonProperty("new")
            long new_revision
    ) {}
}
