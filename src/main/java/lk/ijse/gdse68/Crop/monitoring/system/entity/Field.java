package lk.ijse.gdse68.Crop.monitoring.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Point;


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
    @Column(name = "image_1", columnDefinition = "LONGTEXT")
    private String image1;
    @Column(name = "image_2", columnDefinition = "LONGTEXT")
    private String image2;

    @JsonIgnore
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Crop> crop;

    @JsonIgnore
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Equipment> equipment;

    @ManyToMany
    @JoinTable(
            name = "field_staff",
            joinColumns = @JoinColumn(name = "field_code"),
            inverseJoinColumns = @JoinColumn(name = "staff_member_id")
    )
    private List<Staff> staff;

    @JsonIgnore
    @ManyToMany(mappedBy = "field")
    private List<MonitoringLog> monitoringLogs;
}

