package lk.ijse.gdse68.Crop.monitoring.system.dto;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDto implements SuperDto, VehicleResponse {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    private String staffId;
}
