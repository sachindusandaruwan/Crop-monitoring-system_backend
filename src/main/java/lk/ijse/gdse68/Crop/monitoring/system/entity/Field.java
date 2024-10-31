package lk.ijse.gdse68.Crop.monitoring.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "fields")
public class Field {
    @Id
    @Column(name = "field_code")
    private String fieldCode;
    @Column(name = "field_name")
    private String fieldName;
    @Column(name = "field_location")
    private Point fieldLocation;
    @Column(name = "field_size")
    private double fieldSize;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage1;
    @Column(columnDefinition = "LONGTEXT")
    private String fieldImage2;

}

