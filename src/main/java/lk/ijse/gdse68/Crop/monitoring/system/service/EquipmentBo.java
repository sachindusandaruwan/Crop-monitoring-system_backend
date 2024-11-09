package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.EquipmentResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.EquipmentDto;

import java.util.List;

public interface EquipmentBo {
    void saveEquipment(EquipmentDto equipmentDto);

    EquipmentResponse getEquipment(String equipmentCode);

    List<EquipmentDto> getAllEquipment();
}
