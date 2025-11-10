package net.javaguides.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import net.javaguides.springboot.dto.WikimediaEvent;

@Entity
@Table(name = "wikimedia_recentchange")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WikimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schema_name")
    private String schema;

    private WikimediaEvent.Meta meta;

    @Column(name = "event_type")
    private String type;

    private int namespace;
    private String title;

    @Column(length = 2000)
    private String title_url;

    @Column(name = "user_comment", length= 5000)
    private String comment;

    @Column(name = "event_timestamp")
    private long timestamp;

    @Column(name = "username")
    private String user;

    private boolean bot;
    private String notify_url;
    private boolean minor;
    private boolean patrolled;
    private WikimediaEvent.Length length;
    private WikimediaEvent.Revision revision;
    private String server_url;
    private String server_name;
    private String server_script_path;
    private String wiki;

    @Column(length = 5000)
    private String parsedcomment;
}
