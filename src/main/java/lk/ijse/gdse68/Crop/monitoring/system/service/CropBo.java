package lk.ijse.gdse68.Crop.monitoring.system.service;

import lk.ijse.gdse68.Crop.monitoring.system.customObj.CropResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.CropDto;

import java.util.List;

public interface CropBo {
    void saveCrop(CropDto cropDto, String fieldCode);

    CropResponse findCrop(String cropCode);

    void deleteCrop(String cropCode);


    List<CropDto> getAllCrop();

    void updateCrop(CropDto cropDto, String fieldCode, String cropCode);
}
