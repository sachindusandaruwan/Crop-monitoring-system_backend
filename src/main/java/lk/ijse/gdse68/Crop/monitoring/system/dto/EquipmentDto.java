package lk.ijse.gdse68.Crop.monitoring.system.dto;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.EquipmentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDto implements SuperDto, EquipmentResponse {
    private String equipmentId;
    private String name;
    private String type;
    private String status;
    private String staffId;
    private String fieldCode;
}
