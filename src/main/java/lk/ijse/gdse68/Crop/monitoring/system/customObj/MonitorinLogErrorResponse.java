package lk.ijse.gdse68.Crop.monitoring.system.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitorinLogErrorResponse  implements MonitoringLogResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}