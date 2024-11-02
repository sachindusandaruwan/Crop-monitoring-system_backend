package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.FieldResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.FieldDto;

import java.util.List;

public interface FieldBo {

    void saveField(FieldDto fieldDto);

    FieldResponse getField(String fieldCode);

    List<FieldDto> getAllField();
}
