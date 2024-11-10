package lk.ijse.gdse68.Crop.monitoring.system.dto;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.MonitoringLogResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDto implements MonitoringLogResponse,SuperDto{
    private String logCode;
    private Date logDate;
    private String observation;
    private String observedImage;
    private List<String> fieldCodes;
    private List<String> cropCodes;
    private List<String> staffIds;
}
