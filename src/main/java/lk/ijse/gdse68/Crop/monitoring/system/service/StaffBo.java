package lk.ijse.gdse68.Crop.monitoring.system.service;

import jakarta.validation.Valid;
import lk.ijse.gdse68.Crop.monitoring.system.customObj.StaffResponse;
import lk.ijse.gdse68.Crop.monitoring.system.dto.StaffDto;

import java.util.List;

public interface StaffBo {
    void saveStaff(StaffDto staffDto);

    StaffResponse getStaff(String id);

    List<StaffDto> getAllStaff();


    void updateStaff(@Valid StaffDto staffDto);
}
