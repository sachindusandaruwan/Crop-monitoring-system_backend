package lk.ijse.gdse68.Crop.monitoring.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "monitoring_log")
public class MonitoringLog {
    @Id
    @Column(name = "log_code")
    private String logCode;
    private Date logDate;
    private String logDetails;
    @Column(columnDefinition = "LONGTEXT")
    private String observedImage;

    @ManyToMany
    @JoinTable(
            name="monitoring_log_field",
            joinColumns = @JoinColumn(name = "field_code"),
            inverseJoinColumns = @JoinColumn(name="log_code")
    )

    private List<Field> field;

    @ManyToMany
    @JoinTable(
            name="monitoring_log_crop",
            joinColumns = @JoinColumn(name="log_code"),
            inverseJoinColumns = @JoinColumn(name="crop_code")
    )
    private List<Crop> crop;

    @ManyToMany
    @JoinTable(
            name="monitoring_log_staff",
            joinColumns = @JoinColumn(name="log_code"),
            inverseJoinColumns = @JoinColumn(name = "staff_member_id")
    )
    private List<Staff> staff;


}
