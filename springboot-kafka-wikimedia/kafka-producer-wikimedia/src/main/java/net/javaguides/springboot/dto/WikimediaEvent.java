package net.javaguides.springboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
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
    public record Meta(
            String uri,
            String request_id,
            String id,
            String domain,
            String stream,
            String dt,
            String topic,
            int partition,
            long offset
    ) {}

    public record Length(
            int old,

            @JsonProperty("new")
            int _new // `_new` to avoid reserved word
    ) {}

    public record Revision(
            long old,

            @JsonProperty("new") long _new
    ) {}
}
