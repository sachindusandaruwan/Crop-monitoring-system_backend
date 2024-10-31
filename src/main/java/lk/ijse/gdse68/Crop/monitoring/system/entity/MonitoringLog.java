package lk.ijse.gdse68.Crop.monitoring.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "crop_details")
public class MonitoringLog {
    @Id
    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;


}
