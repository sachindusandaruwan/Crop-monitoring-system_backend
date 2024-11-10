package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.CropResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.CropDto;

public interface CropBo {
    void saveCrop(CropDto cropDto, String fieldCode);

    CropResponse findCrop(String cropCode);

    void deleteCrop(String cropCode);
}
