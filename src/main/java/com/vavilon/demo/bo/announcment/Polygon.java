package com.vavilon.demo.bo.announcment;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "polygon")
@Getter
@Setter
public class Polygon {
    @Id
    @Column(name = "polygonid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long polygonId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "announcementid")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="announcementId")
    @JsonIdentityReference(alwaysAsId=true)
    private Announcement announcement;
    @OneToMany(mappedBy = "polygon", cascade = CascadeType.ALL)
    private List<Coordinate> coordinates;

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    @JsonProperty("announcement")
    public void setAnnouncement(String announcementId) {
        if (announcementId != null) {
            final Announcement announcement = new Announcement();
            announcement.setAnnouncementId(Long.valueOf(announcementId));
            this.announcement = announcement;
        } else {
            this.announcement = null;
        }
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        if (coordinates != null) {
            for (final Coordinate coordinate: coordinates) {
                coordinate.setPolygon(this);
            }
        }
        this.coordinates = coordinates;
    }
}
