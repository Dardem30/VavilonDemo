package com.vavilon.demo.bo.announcment;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "coordinates")
@Data
public class Coordinate {
    @Id
    @Column(name = "coordinatesid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coordinatesId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "polygonId")
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="polygonId")
    @JsonIdentityReference(alwaysAsId=true)
    private Polygon polygon;
    @Column(name = "lat")
    private double lat;
    @Column(name = "lng")
    private double lng;

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    @JsonProperty("polygon")
    public void setPolygon(String polygonId) {
        if (polygonId != null) {
            final Polygon polygon = new Polygon();
            polygon.setPolygonId(Long.valueOf(polygonId));
            this.polygon = polygon;
        } else {
            this.polygon = null;
        }
    }
}
